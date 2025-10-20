package com.tele.servlet.Admin.Generalist;

import com.tele.service.GeneralPractitionerService;
import com.tele.service.impl.GeneralPractitionerServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/admin/generalists/delete")
public class AdminDeleteGeneralistServlet extends HttpServlet {
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
            gpService.deleteGeneralPractitioner(id);
            response.sendRedirect(request.getContextPath() + "/admin/generalists");
        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/admin/generalists");
        }
    }

    @Override
    public void destroy() {
        gpService = null;
    }
}
