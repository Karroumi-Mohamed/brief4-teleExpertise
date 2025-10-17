package com.tele.service.impl;

import com.tele.model.User;
import com.tele.model.enums.UserRole;
import com.tele.service.AuthService;
import com.tele.service.UserService;
import jakarta.servlet.http.HttpSession;

import java.util.Optional;

public class AuthServiceImpl implements AuthService {
    private final static String USER_ID = "user_id";
    private final UserService userService;

    public AuthServiceImpl(UserService userService){
        this.userService = userService;
    }

    @Override
    public Optional<User> login(HttpSession session, String email, String password) throws Exception {
        if (session == null || email == null || password == null || email.isBlank() || password.isBlank()) {
            return Optional.empty();
        }
        String normalizedEmail = email.trim().toLowerCase();
        Optional<User> userOpt = userService.findByEmail(normalizedEmail);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (userService.verifyPassword(user, password)) {
                session.setAttribute(USER_ID, user.getId());
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> user(HttpSession session){
        if (session == null) return Optional.empty();
        Object id = session.getAttribute(USER_ID);
        if (id == null) return Optional.empty();
        try {
            return  userService.findById((Long) id);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public void logout(HttpSession session) {
        if (session != null) {
            session.invalidate();
        }
    }

    @Override
    public boolean isAuthenticated(HttpSession session) {
        return user(session).isPresent();
    }

    @Override
    public boolean hasRole(HttpSession session, UserRole role) {
        Optional<User> userOpt = user(session);
        return userOpt.isPresent() && userOpt.get().getRole() == role;
    }
}
