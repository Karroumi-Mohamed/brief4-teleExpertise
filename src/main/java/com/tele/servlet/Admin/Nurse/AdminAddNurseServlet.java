package com.tele.servlet.Admin.Nurse;

import org.mindrot.jbcrypt.BCrypt;

import com.tele.service.NurseService;
import com.tele.service.impl.NurseServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/admin/nurses/add")
public class AdminAddNurseServlet extends HttpServlet {
    private NurseService nurseService;

    @Override
    public void init() {
        nurseService = new NurseServiceImpl();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/admin/add_nurse.jsp").forward(request, response);
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
                request.getRequestDispatcher("/WEB-INF/views/admin/add_nurse.jsp").forward(request, response);
                return;
            }

            String passwordHash = BCrypt.hashpw(password, BCrypt.gensalt());

            nurseService.createNurse(fname, lname, phone, email, passwordHash);

            response.sendRedirect(request.getContextPath() + "/admin/nurses");

        } catch (Exception e) {
            request.setAttribute("error", "An error occurred while adding the nurse: " + e.getMessage());
            request.getRequestDispatcher("/WEB-INF/views/admin/add_nurse.jsp").forward(request, response);
        }
    }

    @Override
    public void destroy() {
        nurseService = null;
    }
}
