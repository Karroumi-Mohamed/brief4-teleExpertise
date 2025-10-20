package com.tele.servlet.Admin.Nurse;

import com.tele.dto.NurseDTO;
import com.tele.service.NurseService;
import com.tele.service.impl.NurseServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/admin/nurses/edit")
public class AdminEditNurseServlet extends HttpServlet {
    private NurseService nurseService;

    @Override
    public void init() {
        nurseService = new NurseServiceImpl();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");

        if (idStr == null || idStr.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/admin/nurses");
            return;
        }

        try {
            Long id = Long.parseLong(idStr);
            Optional<NurseDTO> nurseOpt = nurseService.findById(id);

            if (nurseOpt.isPresent()) {
                request.setAttribute("nurse", nurseOpt.get());
                request.getRequestDispatcher("/WEB-INF/views/admin/edit_nurse.jsp").forward(request, response);
            } else {
                response.sendRedirect(request.getContextPath() + "/admin/nurses");
            }
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/admin/nurses");
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

            if (idStr == null || email == null || fname == null || lname == null ||
                    phone == null || idStr.isEmpty() || email.isEmpty() || fname.isEmpty() ||
                    lname.isEmpty() || phone.isEmpty()) {
                request.setAttribute("error", "All fields are required.");
                doGet(request, response);
                return;
            }

            Long id = Long.parseLong(idStr);

            nurseService.updateNurse(id, fname, lname, phone, email);

            response.sendRedirect(request.getContextPath() + "/admin/nurses");

        } catch (Exception e) {
            request.setAttribute("error", "An error occurred while updating the nurse: " + e.getMessage());
            doGet(request, response);
        }
    }

    @Override
    public void destroy() {
        nurseService = null;
    }
}
