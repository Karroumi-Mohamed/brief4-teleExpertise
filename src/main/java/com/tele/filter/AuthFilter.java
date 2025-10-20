package com.tele.filter;

import com.tele.model.User;
import com.tele.model.enums.UserRole;
import com.tele.service.AuthService;
import com.tele.service.UserService;
import com.tele.service.impl.AuthServiceImpl;
import com.tele.service.impl.UserServiceImpl;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Optional;

@WebFilter(urlPatterns = {"/admin/*", "/nurse/*", "/gp/*", "/specialist/*"})
public class AuthFilter implements Filter {

    private AuthService authService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        UserService userService = new UserServiceImpl();
        authService = new AuthServiceImpl(userService);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);

        String servletPath = httpRequest.getServletPath();

        if (!authService.isAuthenticated(session)){
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
            return;
        }

        Optional<User> userOpt = authService.user(session);
        if (userOpt.isEmpty()){
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
            return;
        }

        User user = userOpt.get();

        if(!isAuthorized(user.getRole(), servletPath)){
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/");
            return;
        }

        chain.doFilter(request, response);
    }

    private boolean isAuthorized(UserRole role, String servletPath){
        if (servletPath.startsWith("/admin/") && role == UserRole.ADMIN){
            return true;
        }
        if (servletPath.startsWith("/nurse/") && role == UserRole.NURSE){
            return true;
        }
        if (servletPath.startsWith("/gp/") && role == UserRole.GENERAL_PRACTITIONER){
            return true;
        }
        if (servletPath.startsWith("/specialist/") && role == UserRole.SPECIALIST){
            return true;
        }
        return false;
    }

    @Override
    public void destroy() {
        authService = null;
    }
}
