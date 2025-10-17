package com.tele.dao;

import com.tele.model.User;
import com.tele.model.enums.UserRole;

import java.util.List;
import java.util.Optional;

public interface UserDAO extends GenericDAO<User, Long> {

    Optional<User> findByEmail(String email);

    List<User> findByRole(UserRole role);

    boolean existsByEmail(String email);
}
