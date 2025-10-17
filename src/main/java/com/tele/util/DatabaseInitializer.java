package com.tele.util;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import com.tele.dao.UserDAO;
import com.tele.dao.impl.UserDAOImpl;
import com.tele.model.Admin;
import com.tele.model.enums.UserRole;
import org.mindrot.jbcrypt.BCrypt;

@WebListener
public class DatabaseInitializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Application starting - checking for default admin...");

        try {
            UserDAO userDAO = new UserDAOImpl();

            boolean adminExists = userDAO.findAll().stream()
                    .anyMatch(user -> user.getRole() == UserRole.ADMIN);

            if (!adminExists) {
                System.out.println("No admin found. Creating default admin account...");

                String hashedPassword = BCrypt.hashpw("admin123", BCrypt.gensalt());
                Admin defaultAdmin = new Admin(
                    "admin@teleexpertise.com",
                    hashedPassword,
                    "Admin",
                    "User",
                    "+1234567890"
                );

                userDAO.save(defaultAdmin);
                System.out.println("Default admin created:");
                System.out.println("Email: admin@teleexpertise.com");
                System.out.println("Password: admin123");
                System.out.println("Please change this password after first login!");
            } else {
                System.out.println("Admin account already exists.");
            }

        } catch (Exception e) {
            System.err.println("Error creating default admin: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Application shutting down...");
    }
}
