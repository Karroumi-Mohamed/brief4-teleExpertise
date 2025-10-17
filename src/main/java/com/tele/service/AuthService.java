package com.tele.service;

import com.tele.model.User;
import com.tele.model.enums.UserRole;
import jakarta.servlet.http.HttpSession;

import java.util.Optional;

public interface AuthService {
    Optional<User> login(HttpSession session, String email, String password) throws Exception;

    Optional<User> user(HttpSession session);

    void logout(HttpSession session);

    boolean isAuthenticated(HttpSession session);

    boolean hasRole(HttpSession session, UserRole role);
}
