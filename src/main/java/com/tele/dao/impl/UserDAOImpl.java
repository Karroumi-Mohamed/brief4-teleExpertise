package com.tele.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import com.tele.dao.UserDAO;
import com.tele.model.User;
import com.tele.model.enums.UserRole;

import java.util.List;
import java.util.Optional;

public class UserDAOImpl extends GenericDAOImpl<User, Long> implements UserDAO {
    public UserDAOImpl() {
        super(User.class);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        EntityManager em = getEntityManager();
        try {
            User user = em.createQuery(
                "SELECT u FROM User u WHERE u.email = :email", User.class)
                .setParameter("email", email)
                .getSingleResult();
            return Optional.of(user);
        } catch (NoResultException e) {
            return Optional.empty();
        } catch (Exception e) {
            throw new RuntimeException("Error finding user by email", e);
        }
    }

    @Override
    public List<User> findByRole(UserRole role) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery(
                "SELECT u FROM User u WHERE u.role = :role", User.class)
                .setParameter("role", role)
                .getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error finding users by role", e);
        }
    }

    @Override
    public boolean existsByEmail(String email) {
        EntityManager em = getEntityManager();
        try {
            Long count = em.createQuery(
                "SELECT COUNT(u) FROM User u WHERE u.email = :email", Long.class)
                .setParameter("email", email)
                .getSingleResult();
            return count > 0;
        } catch (Exception e) {
            throw new RuntimeException("Error checking if user exists by email", e);
        }
    }
}
