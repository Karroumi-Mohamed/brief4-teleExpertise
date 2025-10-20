package com.tele.servlet.Admin.Generalist;

import com.tele.dto.GeneralPractitionerDTO;
import com.tele.service.GeneralPractitionerService;
import com.tele.service.impl.GeneralPractitionerServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/admin/generalists/edit")
public class AdminEditGeneralistServlet extends HttpServlet {
    private GeneralPractitionerService gpService;

    @Override
    public void init() {
        gpService = new GeneralPractitionerServiceImpl();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");

        if (idStr == null || idStr.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/admin/generalists");
            return;
        }

        try {
            Long id = Long.parseLong(idStr);
            Optional<GeneralPractitionerDTO> gpOpt = gpService.findById(id);

            if (gpOpt.isPresent()) {
                request.setAttribute("generalist", gpOpt.get());
                request.getRequestDispatcher("/WEB-INF/views/admin/edit_generalist.jsp").forward(request, response);
            } else {
                response.sendRedirect(request.getContextPath() + "/admin/generalists");
            }
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/admin/generalists");
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

            gpService.updateGeneralPractitioner(id, fname, lname, phone, email);

            response.sendRedirect(request.getContextPath() + "/admin/generalists");

        } catch (Exception e) {
            request.setAttribute("error", "An error occurred while updating the generalist: " + e.getMessage());
            doGet(request, response);
        }
    }

    @Override
    public void destroy() {
        gpService = null;
    }
}
