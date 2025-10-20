package com.tele.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.tele.model.User;
import com.tele.service.AuthService;
import com.tele.service.UserService;
import com.tele.service.impl.AuthServiceImpl;
import com.tele.service.impl.UserServiceImpl;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/")
public class HomeServlet extends HttpServlet {

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

        if (authService.isAuthenticated(session)) {
            Optional<User> userOpt = authService.user(session);

            if (userOpt.isPresent()) {
                User user = userOpt.get();

                switch (user.getRole()) {
                    case ADMIN:
                        response.sendRedirect(request.getContextPath() + "/admin/dashboard");
                        break;
                    case NURSE:
                        response.sendRedirect(request.getContextPath() + "/nurse/dashboard");
                        break;
                    case GENERAL_PRACTITIONER:
                        response.sendRedirect(request.getContextPath() + "/gp/dashboard");
                        break;
                    case SPECIALIST:
                        response.sendRedirect(request.getContextPath() + "/specialist/dashboard");
                        break;
                    default:
                        response.sendRedirect(request.getContextPath() + "/login");
                }
                return;
            }
        }

        response.sendRedirect(request.getContextPath() + "/login");
    }
}
