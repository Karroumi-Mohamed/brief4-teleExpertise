package com.tele.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import com.tele.dao.PatientDAO;
import com.tele.model.Patient;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class PatientDAOImpl extends GenericDAOImpl<Patient, Long> implements PatientDAO {

    public PatientDAOImpl() {
        super(Patient.class);
    }

    @Override
    public Optional<Patient> findBySocialSecurityNumber(String socialSecurityNumber) {
        EntityManager em = getEntityManager();
        try {
            Patient patient = em.createQuery(
                "SELECT p FROM Patient p WHERE p.SocialSecurityNumber = :ssn", Patient.class)
                .setParameter("ssn", socialSecurityNumber)
                .getSingleResult();
            return Optional.of(patient);
        } catch (NoResultException e) {
            return Optional.empty();
        } catch (Exception e) {
            throw new RuntimeException("Error finding patient by SSN", e);
        }
    }

    @Override
    public List<Patient> findByCreatedAt(LocalDate date) {
        EntityManager em = getEntityManager();
        try {
            LocalDateTime startOfDay = date.atStartOfDay();
            LocalDateTime endOfDay = date.atTime(23, 59, 59);

            return em.createQuery(
                "SELECT p FROM Patient p WHERE p.createdAt BETWEEN :start AND :end ORDER BY p.createdAt",
                Patient.class)
                .setParameter("start", startOfDay)
                .setParameter("end", endOfDay)
                .getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error finding patients by created date", e);
        }
    }

    @Override
    public List<Patient> findByName(String name) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery(
                "SELECT p FROM Patient p WHERE LOWER(p.fname) LIKE LOWER(:name) OR LOWER(p.lname) LIKE LOWER(:name)",
                Patient.class)
                .setParameter("name", "%" + name + "%")
                .getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error finding patients by name", e);
        }
    }

    @Override
    public boolean existsBySocialSecurityNumber(String socialSecurityNumber) {
        EntityManager em = getEntityManager();
        try {
            Long count = em.createQuery(
                "SELECT COUNT(p) FROM Patient p WHERE p.SocialSecurityNumber = :ssn", Long.class)
                .setParameter("ssn", socialSecurityNumber)
                .getSingleResult();
            return count > 0;
        } catch (Exception e) {
            throw new RuntimeException("Error checking if patient exists by SSN", e);
        }
    }
}
