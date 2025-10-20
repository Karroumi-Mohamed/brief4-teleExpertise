package com.tele.servlet.GP;

import com.tele.dto.ExpertiseRequestDTO;
import com.tele.service.AuthService;
import com.tele.service.ExpertiseRequestService;
import com.tele.service.impl.AuthServiceImpl;
import com.tele.service.impl.ExpertiseRequestServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/gp/expertise-requests")
public class ExpertiseRequestsServlet extends HttpServlet {
    private ExpertiseRequestService expertiseRequestService;

    @Override
    public void init() {
        expertiseRequestService = new ExpertiseRequestServiceImpl();
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

        List<ExpertiseRequestDTO> expertiseRequests = expertiseRequestService.findByGP(gpId);

        request.setAttribute("expertiseRequests", expertiseRequests);
        request.getRequestDispatcher("/WEB-INF/views/gp/expertise_requests.jsp").forward(request, response);
    }

    @Override
    public void destroy() {
        expertiseRequestService = null;
    }
}
