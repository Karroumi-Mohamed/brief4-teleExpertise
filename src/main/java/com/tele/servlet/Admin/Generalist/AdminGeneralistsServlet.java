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
import java.util.List;

@WebServlet("/admin/generalists")
public class AdminGeneralistsServlet extends HttpServlet {
    private GeneralPractitionerService gpService;

    @Override
    public void init() {
        gpService = new GeneralPractitionerServiceImpl();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<GeneralPractitionerDTO> generalists = gpService.findAll();
        request.setAttribute("generalists", generalists);
        request.getRequestDispatcher("/WEB-INF/views/admin/generalists.jsp").forward(request, response);
    }

    @Override
    public void destroy() {
        gpService = null;
    }
}
