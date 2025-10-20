package com.tele.servlet.Admin.Specialist;

import com.tele.service.SpecialistService;
import com.tele.service.impl.SpecialistServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/admin/specialists/delete")
public class AdminDeleteSpecialistServlet extends HttpServlet {
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
            specialistService.deleteSpecialist(id);
            response.sendRedirect(request.getContextPath() + "/admin/specialists");
        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/admin/specialists");
        }
    }

    @Override
    public void destroy() {
        specialistService = null;
    }
}
