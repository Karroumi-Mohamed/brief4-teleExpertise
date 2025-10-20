package com.tele.servlet;

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
import java.io.PrintWriter;
import java.util.Optional;

@WebServlet("/debug")
public class DebugServlet extends HttpServlet {

    private AuthService authService;

    @Override
    public void init() throws ServletException {
        UserService userService = new UserServiceImpl();
        this.authService = new AuthServiceImpl(userService);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession(false);

        out.println("<html><body>");
        out.println("<h1>Debug Info</h1>");
        out.println("<p>Context Path: " + request.getContextPath() + "</p>");
        out.println("<p>Servlet Path: " + request.getServletPath() + "</p>");
        out.println("<p>Request URI: " + request.getRequestURI() + "</p>");
        out.println("<p>Session exists: " + (session != null) + "</p>");

        if (session != null) {
            out.println("<p>Is Authenticated: " + authService.isAuthenticated(session) + "</p>");

            Optional<User> userOpt = authService.user(session);
            if (userOpt.isPresent()) {
                User user = userOpt.get();
                out.println("<p>User: " + user.getFname() + " " + user.getLname() + "</p>");
                out.println("<p>Email: " + user.getEmail() + "</p>");
                out.println("<p>Role: " + user.getRole() + "</p>");
                out.println("<p>User Class: " + user.getClass().getName() + "</p>");
            } else {
                out.println("<p>No user in session</p>");
            }
        }

        out.println("</body></html>");
    }

    @Override
    public void destroy() {
        authService = null;
    }
}
