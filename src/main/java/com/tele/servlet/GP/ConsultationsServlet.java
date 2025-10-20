package com.tele.servlet.GP;

import com.tele.dto.ConsultationDTO;
import com.tele.model.GeneralPractitioner;
import com.tele.service.AuthService;
import com.tele.service.ConsultationService;
import com.tele.service.impl.AuthServiceImpl;
import com.tele.service.impl.ConsultationServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/gp/consultations")
public class ConsultationsServlet extends HttpServlet {
    private ConsultationService consultationService;

    @Override
    public void init() {
        consultationService = new ConsultationServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        Long gpId = (Long) session.getAttribute(AuthServiceImpl.USER_ID);

        if (gpId == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // Get all consultations for this GP
        List<ConsultationDTO> consultations = consultationService.findByGeneralPractitioner(gpId);

        request.setAttribute("consultations", consultations);
        request.getRequestDispatcher("/WEB-INF/views/gp/consultations.jsp").forward(request, response);
    }

    @Override
    public void destroy() {
        consultationService = null;
    }
}
