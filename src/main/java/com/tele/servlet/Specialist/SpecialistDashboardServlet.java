package com.tele.servlet.Specialist;

import com.tele.dto.ExpertiseRequestDTO;
import com.tele.model.Specialist;
import com.tele.model.User;
import com.tele.model.enums.ExpertiseStatus;
import com.tele.service.AuthService;
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

@WebServlet("/specialist/dashboard")
public class SpecialistDashboardServlet extends HttpServlet {

    private AuthService authService;
    private ExpertiseRequestService expertiseRequestService;

    @Override
    public void init() throws ServletException {
        UserService userService = new UserServiceImpl();
        this.authService = new AuthServiceImpl(userService);
        this.expertiseRequestService = new ExpertiseRequestServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        Optional<User> userOpt = authService.user(session);

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            request.setAttribute("user", user);

            // Get statistics for specialist
            if (user instanceof Specialist) {
                Specialist specialist = (Specialist) user;
                List<ExpertiseRequestDTO> allRequests = expertiseRequestService.findBySpecialist(specialist.getId());

                long pendingCount = allRequests.stream()
                        .filter(req -> req.getStatus() == ExpertiseStatus.PENDING)
                        .count();
                long completedCount = allRequests.stream()
                        .filter(req -> req.getStatus() == ExpertiseStatus.COMPLETED)
                        .count();

                request.setAttribute("pendingCount", pendingCount);
                request.setAttribute("completedCount", completedCount);
                request.setAttribute("totalRequests", allRequests.size());
            }
        }

        request.getRequestDispatcher("/WEB-INF/views/specialist/dashboard.jsp").forward(request, response);
    }

    @Override
    public void destroy() {
        authService = null;
        expertiseRequestService = null;
    }
}
