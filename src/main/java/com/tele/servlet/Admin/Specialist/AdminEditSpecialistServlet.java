package com.tele.servlet.Admin.Specialist;

import com.tele.dto.SpecialistDTO;
import com.tele.model.enums.Specialty;
import com.tele.service.SpecialistService;
import com.tele.service.impl.SpecialistServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/admin/specialists/edit")
public class AdminEditSpecialistServlet extends HttpServlet {
    private SpecialistService specialistService;

    @Override
    public void init() {
        specialistService = new SpecialistServiceImpl();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");

        if (idStr == null || idStr.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/admin/specialists");
            return;
        }

        try {
            Long id = Long.parseLong(idStr);
            Optional<SpecialistDTO> specialistOpt = specialistService.findById(id);

            if (specialistOpt.isPresent()) {
                request.setAttribute("specialist", specialistOpt.get());
                request.getRequestDispatcher("/WEB-INF/views/admin/edit_specialist.jsp").forward(request, response);
            } else {
                response.sendRedirect(request.getContextPath() + "/admin/specialists");
            }
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/admin/specialists");
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String idStr = request.getParameter("id");
            String email = request.getParameter("email");
            String fname = request.getParameter("firstName");
            String lname = request.getParameter("lastName");
            String phone = request.getParameter("phone");
            String specialityStr = request.getParameter("speciality");
            String expertiseFeeStr = request.getParameter("expertiseFee");
            String avgDurationStr = request.getParameter("avgDuration");

            if (idStr == null || email == null || fname == null || lname == null ||
                    phone == null || specialityStr == null || expertiseFeeStr == null ||
                    idStr.isEmpty() || email.isEmpty() || fname.isEmpty() || lname.isEmpty() ||
                    phone.isEmpty() || specialityStr.isEmpty() || expertiseFeeStr.isEmpty()) {
                request.setAttribute("error", "All fields are required.");
                doGet(request, response);
                return;
            }

            Long id = Long.parseLong(idStr);
            Specialty specialty = Specialty.valueOf(specialityStr);
            Double expertiseFee = Double.parseDouble(expertiseFeeStr);
            Integer avgDuration = avgDurationStr != null && !avgDurationStr.trim().isEmpty()
                    ? Integer.parseInt(avgDurationStr)
                    : 30;

            specialistService.updateSpecialist(id, fname, lname, specialty, expertiseFee, phone, email, avgDuration);

            response.sendRedirect(request.getContextPath() + "/admin/specialists");

        } catch (Exception e) {
            request.setAttribute("error", "An error occurred while updating the specialist: " + e.getMessage());
            doGet(request, response);
        }
    }

    @Override
    public void destroy() {
        specialistService = null;
    }
}
