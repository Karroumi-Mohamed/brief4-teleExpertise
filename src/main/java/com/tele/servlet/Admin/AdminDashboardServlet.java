package com.tele.servlet.Admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.tele.model.User;
import com.tele.service.AuthService;
import com.tele.service.UserService;
import com.tele.service.PatientService;
import com.tele.service.ConsultationService;
import com.tele.service.impl.AuthServiceImpl;
import com.tele.service.impl.UserServiceImpl;
import com.tele.service.impl.PatientServiceImpl;
import com.tele.service.impl.ConsultationServiceImpl;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/admin/dashboard")
public class AdminDashboardServlet extends HttpServlet {

    private AuthService authService;
    private UserService userService;
    private PatientService patientService;
    private ConsultationService consultationService;

    @Override
    public void init() throws ServletException {
        this.userService = new UserServiceImpl();
        this.authService = new AuthServiceImpl(userService);
        this.patientService = new PatientServiceImpl();
        this.consultationService = new ConsultationServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        // Check authentication
        if (!authService.isAuthenticated(session)) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // Check if user is admin
        Optional<User> userOpt = authService.user(session);
        if (userOpt.isEmpty() || !userOpt.get().getRole().name().equals("ADMIN")) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // Get statistics
        long totalUsers = userService.count();
        long totalPatients = patientService.count();
        long totalConsultations = consultationService.count();

        // Set attributes
        request.setAttribute("user", userOpt.get());
        request.setAttribute("totalUsers", totalUsers);
        request.setAttribute("totalPatients", totalPatients);
        request.setAttribute("totalConsultations", totalConsultations);

        request.getRequestDispatcher("/WEB-INF/views/admin/dashboard.jsp").forward(request, response);
    }

    @Override
    public void destroy() {
        authService = null;
        userService = null;
        patientService = null;
        consultationService = null;
    }
}
