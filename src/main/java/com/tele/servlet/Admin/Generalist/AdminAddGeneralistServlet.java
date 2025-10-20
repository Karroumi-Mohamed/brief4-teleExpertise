package com.tele.servlet.Admin.Generalist;

import org.mindrot.jbcrypt.BCrypt;

import com.tele.service.GeneralPractitionerService;
import com.tele.service.impl.GeneralPractitionerServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/admin/generalists/add")
public class AdminAddGeneralistServlet extends HttpServlet {
    private GeneralPractitionerService gpService;

    @Override
    public void init() {
        gpService = new GeneralPractitionerServiceImpl();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/admin/add_generalist.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String fname = request.getParameter("firstName");
            String lname = request.getParameter("lastName");
            String phone = request.getParameter("phone");

            if (email == null || password == null || fname == null || lname == null ||
                    phone == null || email.isEmpty() || password.isEmpty() || fname.isEmpty() ||
                    lname.isEmpty() || phone.isEmpty()) {
                request.setAttribute("error", "All fields are required.");
                request.getRequestDispatcher("/WEB-INF/views/admin/add_generalist.jsp").forward(request, response);
                return;
            }

            String passwordHash = BCrypt.hashpw(password, BCrypt.gensalt());

            gpService.createGeneralPractitioner(fname, lname, phone, email, passwordHash);

            response.sendRedirect(request.getContextPath() + "/admin/generalists");

        } catch (Exception e) {
            request.setAttribute("error", "An error occurred while adding the generalist: " + e.getMessage());
            request.getRequestDispatcher("/WEB-INF/views/admin/add_generalist.jsp").forward(request, response);
        }
    }

    @Override
    public void destroy() {
        gpService = null;
    }
}
