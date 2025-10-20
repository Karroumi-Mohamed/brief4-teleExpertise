package com.tele.servlet.Specialist;

import com.tele.dto.ExpertiseRequestDTO;
import com.tele.model.Specialist;
import com.tele.model.User;
import com.tele.model.enums.ExpertiseStatus;
import com.tele.model.enums.Priority;
import com.tele.service.ExpertiseRequestService;
import com.tele.service.UserService;
import com.tele.service.impl.AuthServiceImpl;
import com.tele.service.impl.ExpertiseRequestServiceImpl;
import com.tele.service.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@WebServlet("/specialist/expertise-requests")
public class ExpertiseRequestsServlet extends HttpServlet {
    private ExpertiseRequestService expertiseRequestService;
    private UserService userService;

    @Override
    public void init() {
        expertiseRequestService = new ExpertiseRequestServiceImpl();
        userService = new UserServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // AuthFilter already verified authentication and authorization
        // Just get the user ID from session
        HttpSession session = request.getSession(false);
        Long userId = (Long) session.getAttribute(AuthServiceImpl.USER_ID);

        Optional<User> userOpt = userService.findById(userId);
        if (userOpt.isEmpty() || !(userOpt.get() instanceof Specialist)) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        Specialist specialist = (Specialist) userOpt.get();

        // Get all requests for this specialist
        List<ExpertiseRequestDTO> allRequests = expertiseRequestService.findBySpecialist(specialist.getId());

        // Get filter parameters
        String statusFilter = request.getParameter("status");
        String priorityFilter = request.getParameter("priority");

        // Apply filters using Stream API (US7 requirement)
        List<ExpertiseRequestDTO> filteredRequests = allRequests.stream()
                .filter(req -> {
                    // Filter by status if provided
                    if (statusFilter != null && !statusFilter.isEmpty()) {
                        try {
                            ExpertiseStatus status = ExpertiseStatus.valueOf(statusFilter);
                            return req.getStatus() == status;
                        } catch (IllegalArgumentException e) {
                            return true; // Invalid status, show all
                        }
                    }
                    return true;
                })
                .filter(req -> {
                    // Filter by priority if provided
                    if (priorityFilter != null && !priorityFilter.isEmpty()) {
                        try {
                            Priority priority = Priority.valueOf(priorityFilter);
                            return req.getPriority() == priority;
                        } catch (IllegalArgumentException e) {
                            return true; // Invalid priority, show all
                        }
                    }
                    return true;
                })
                .collect(Collectors.toList());

        request.setAttribute("requests", filteredRequests);
        request.setAttribute("selectedStatus", statusFilter);
        request.setAttribute("selectedPriority", priorityFilter);
        request.setAttribute("statuses", ExpertiseStatus.values());
        request.setAttribute("priorities", Priority.values());

        request.getRequestDispatcher("/WEB-INF/views/specialist/expertise_requests.jsp").forward(request, response);
    }

    @Override
    public void destroy() {
        expertiseRequestService = null;
        userService = null;
    }
}
