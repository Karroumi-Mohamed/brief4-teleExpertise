package com.tele.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.tele.model.User;
import com.tele.service.AuthService;
import com.tele.service.impl.AuthServiceImpl;
import com.tele.service.UserService;
import com.tele.service.impl.UserServiceImpl;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private AuthService authService;

    @Override
    public void init() throws ServletException {
        UserService userService = new UserServiceImpl();
        this.authService = new AuthServiceImpl(userService);
    }


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        HttpSession session = request.getSession();

        try {
            Optional<User> userOpt = authService.login(session, email, password);

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
                        request.setAttribute("error", "Unknown user role");
                        request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("error", "Invalid email or password");
                request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
            }

        } catch (Exception e) {
            request.setAttribute("error", "Login error occurred");
            request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
        }
    }
}