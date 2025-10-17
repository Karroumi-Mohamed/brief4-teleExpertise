package com.tele.service.impl;

import com.tele.dao.UserDAO;
import com.tele.dao.impl.UserDAOImpl;
import com.tele.model.User;
import com.tele.service.UserService;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Optional;

public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    public UserServiceImpl() {
        this.userDAO = new UserDAOImpl();
    }

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public boolean verifyPassword(User user, String password) {
        if (user == null || password == null) {
            return false;
        }
        return BCrypt.checkpw(password, user.getPasswordHash());
    }

    @Override
    public Optional<User> findByEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return Optional.empty();
        }
        return userDAO.findByEmail(email.trim().toLowerCase());
    }

    @Override
    public Optional<User> findById(Long id) {
        if (id == null) {
            return Optional.empty();
        }
        return userDAO.findById(id);
    }

     @Override
    public long count(){
        return userDAO.count();
     }
}