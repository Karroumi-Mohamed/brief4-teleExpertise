package com.tele.servlet.Admin.Specialist;

import java.io.IOException;

import org.mindrot.jbcrypt.BCrypt;

import com.tele.model.enums.Specialty;
import com.tele.service.SpecialistService;
import com.tele.service.impl.SpecialistServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/admin/specialists/add")
public class AdminAddSpecialistServlet extends HttpServlet {
    private SpecialistService specialistService;

    @Override
    public void init() {
        specialistService = new SpecialistServiceImpl();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/admin/add_specialist.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String fname = request.getParameter("firstName");
            String lname = request.getParameter("lastName");
            String phone = request.getParameter("phone");
            String specialityStr = request.getParameter("speciality");
            String expertiseFeeStr = request.getParameter("expertiseFee");
            String avgDurationStr = request.getParameter("avgDuration");

            if (email == null || password == null || fname == null || lname == null ||
                    phone == null || specialityStr == null || expertiseFeeStr == null || avgDurationStr == null ||
                    email.isEmpty() || password.isEmpty() || fname.isEmpty() || lname.isEmpty() ||
                    phone.isEmpty() || specialityStr.isEmpty() || expertiseFeeStr.isEmpty()) {
                request.setAttribute("error", "All fields are required.");
                request.getRequestDispatcher("/WEB-INF/views/admin/add_specialist.jsp").forward(request, response);
                return;
            }

            String passwordHash = BCrypt.hashpw(password, BCrypt.gensalt());

            Specialty specialty = Specialty.valueOf(specialityStr);
            Double expertiseFee = Double.parseDouble(expertiseFeeStr);
            Integer avgDuration = avgDurationStr != null && !avgDurationStr.trim().isEmpty()
                    ? Integer.parseInt(avgDurationStr)
                    : 30;

            specialistService.createSpecialist(fname,
                lname,
                specialty,
                expertiseFee,
                phone,
                email,
                passwordHash,
                avgDuration);

            response.sendRedirect(request.getContextPath() + "/admin/specialists");

        } catch (Exception e) {
            request.setAttribute("error", "An error occurred while adding the specialist: " + e.getMessage());
            request.getRequestDispatcher("/WEB-INF/views/admin/add_specialist.jsp").forward(request, response);
        }
    }

    @Override
    public void destroy() {
        specialistService = null;
    }
}
