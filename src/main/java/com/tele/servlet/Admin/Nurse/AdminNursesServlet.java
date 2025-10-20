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
import java.util.List;

@WebServlet("/admin/nurses")
public class AdminNursesServlet extends HttpServlet {
    private NurseService nurseService;

    @Override
    public void init() {
        nurseService = new NurseServiceImpl();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<NurseDTO> nurses = nurseService.findAll();
        request.setAttribute("nurses", nurses);
        request.getRequestDispatcher("/WEB-INF/views/admin/nurses.jsp").forward(request, response);
    }

    @Override
    public void destroy() {
        nurseService = null;
    }
}
