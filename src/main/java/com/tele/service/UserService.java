package com.tele.service;

import com.tele.model.User;

import java.util.Optional;

public interface UserService {
    boolean verifyPassword(User user, String password);
    Optional<User> findByEmail(String email);
    Optional<User> findById(Long id);

    long count();
}
