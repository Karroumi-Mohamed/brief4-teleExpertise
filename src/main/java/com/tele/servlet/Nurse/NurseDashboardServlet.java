package com.tele.servlet.Nurse;

import com.tele.model.User;
import com.tele.service.AuthService;
import com.tele.service.UserService;
import com.tele.service.impl.AuthServiceImpl;
import com.tele.service.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/nurse/dashboard")
public class NurseDashboardServlet extends HttpServlet {

    private AuthService authService;

    @Override
    public void init() throws ServletException {
        UserService userService = new UserServiceImpl();
        this.authService = new AuthServiceImpl(userService);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        Optional<User> userOpt = authService.user(session);

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            request.setAttribute("user", user);
        }

        request.getRequestDispatcher("/WEB-INF/views/nurse/dashboard.jsp").forward(request, response);
    }

    @Override
    public void destroy() {
        authService = null;
    }
}
