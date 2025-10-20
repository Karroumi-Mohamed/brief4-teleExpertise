package com.tele.util;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import com.tele.dao.UserDAO;
import com.tele.dao.impl.UserDAOImpl;
import com.tele.model.Admin;
import com.tele.model.enums.Specialty;
import com.tele.model.enums.UserRole;
import com.tele.service.GeneralPractitionerService;
import com.tele.service.NurseService;
import com.tele.service.SpecialistService;
import com.tele.service.impl.GeneralPractitionerServiceImpl;
import com.tele.service.impl.NurseServiceImpl;
import com.tele.service.impl.SpecialistServiceImpl;

import org.mindrot.jbcrypt.BCrypt;

@WebListener
public class DatabaseInitializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Application starting - checking for default admin...");

        try {
            UserDAO userDAO = new UserDAOImpl();
            SpecialistService specialistService = new SpecialistServiceImpl();
            GeneralPractitionerService generalPractitionerService = new GeneralPractitionerServiceImpl();
            NurseService nurseService = new NurseServiceImpl();

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
                        "+1234567890");

                // nurseService.createNurse(fname, lname, phone, email, passwordHash);
                // example of creating a nurse, if needed
                nurseService.createNurse(
                        "Default",
                        "Nurse",
                        "+1234567890",
                        "nurse@tele.com",
                        BCrypt.hashpw("nurse@tele.com", BCrypt.gensalt()));

                // specialistService.createSpecialist(fname, lname, specialty, expertiseFee, phone, email, passwordHash, avgDuration)
                // example of creating a specialist, if needed
                specialistService.createSpecialist(
                        "Default",
                        "Specialist",
                        Specialty.PSYCHIATRY,
                        100.0,
                        "+1234567890",
                        "specialist@tele.com",
                        BCrypt.hashpw("specialist@tele.com", BCrypt.gensalt()),
                        30);
                // generalPractitionerService.createGeneralPractitioner(fname, lname, phone, email, passwordHash)
                // example of creating a general practitioner, if needed
                generalPractitionerService.createGeneralPractitioner(
                        "Default",
                        "General Practitioner",
                        "+1234567890",
                        "gp@tele.com",
                        BCrypt.hashpw("gp@tele.com", BCrypt.gensalt()));

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
