package com.tele.servlet.Admin.Specialist;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.tele.dto.SpecialistDTO;
import com.tele.service.SpecialistService;
import com.tele.service.impl.SpecialistServiceImpl;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/specialists")
public class AdminSpecialistsServlet extends HttpServlet {

    private SpecialistService specialistService;

    @Override
    public void init() throws ServletException {
        this.specialistService = new SpecialistServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<SpecialistDTO> specialistsDTOs = specialistService.findAll();

        request.setAttribute("specialists", specialistsDTOs);

        request.getRequestDispatcher("/WEB-INF/views/admin/specialists.jsp").forward(request, response);
    }

    @Override
    public void destroy() {
        specialistService = null;
    }
}
