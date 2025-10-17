package com.tele.dao.impl;

import jakarta.persistence.EntityManager;
import com.tele.dao.ConsultationDAO;
import com.tele.model.Consultation;
import com.tele.model.enums.ConsultationStatus;

import java.time.LocalDateTime;
import java.util.List;

public class ConsultationDAOImpl extends GenericDAOImpl<Consultation, Long> implements ConsultationDAO {

    public ConsultationDAOImpl() {
        super(Consultation.class);
    }

    @Override
    public List<Consultation> findByPatientId(Long patientId) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery(
                "SELECT c FROM Consultation c WHERE c.patient.id = :patientId ORDER BY c.consultationDate DESC",
                Consultation.class)
                .setParameter("patientId", patientId)
                .getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error finding consultations by patient ID", e);
        }
    }

    @Override
    public List<Consultation> findByGeneralPractitionerId(Long gpId) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery(
                "SELECT c FROM Consultation c WHERE c.generalPractitioner.id = :gpId ORDER BY c.consultationDate DESC",
                Consultation.class)
                .setParameter("gpId", gpId)
                .getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error finding consultations by GP ID", e);
        }
    }

    @Override
    public List<Consultation> findByStatus(ConsultationStatus status) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery(
                "SELECT c FROM Consultation c WHERE c.status = :status ORDER BY c.consultationDate DESC",
                Consultation.class)
                .setParameter("status", status)
                .getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error finding consultations by status", e);
        }
    }

    @Override
    public List<Consultation> findByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery(
                "SELECT c FROM Consultation c WHERE c.consultationDate BETWEEN :start AND :end ORDER BY c.consultationDate DESC",
                Consultation.class)
                .setParameter("start", startDate)
                .setParameter("end", endDate)
                .getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error finding consultations by date range", e);
        }
    }
}
