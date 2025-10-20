package com.tele.servlet.Admin.Nurse;

import com.tele.service.NurseService;
import com.tele.service.impl.NurseServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/admin/nurses/delete")
public class AdminDeleteNurseServlet extends HttpServlet {
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
            nurseService.deleteNurse(id);
            response.sendRedirect(request.getContextPath() + "/admin/nurses");
        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/admin/nurses");
        }
    }

    @Override
    public void destroy() {
        nurseService = null;
    }
}
