package com.tele.dao.impl;

import jakarta.persistence.EntityManager;
import com.tele.dao.TimeSlotDAO;
import com.tele.model.TimeSlot;
import com.tele.model.enums.DaySlot;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class TimeSlotDAOImpl extends GenericDAOImpl<TimeSlot, Long> implements TimeSlotDAO {

    public TimeSlotDAOImpl() {
        super(TimeSlot.class);
    }

    @Override
    public List<TimeSlot> findBySpecialistIdAndDate(Long specialistId, LocalDate date) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery(
                "SELECT ts FROM TimeSlot ts WHERE ts.specialist.id = :specialistId AND ts.date = :date",
                TimeSlot.class)
                .setParameter("specialistId", specialistId)
                .setParameter("date", date)
                .getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error finding time slots by specialist and date", e);
        }
    }

    @Override
    public List<TimeSlot> findBySpecialistIdAndDateBetween(Long specialistId, LocalDate startDate, LocalDate endDate) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery(
                "SELECT ts FROM TimeSlot ts WHERE ts.specialist.id = :specialistId AND ts.date BETWEEN :startDate AND :endDate",
                TimeSlot.class)
                .setParameter("specialistId", specialistId)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error finding time slots by specialist and date range", e);
        }
    }

    @Override
    public Optional<TimeSlot> findBySpecialistIdAndDateAndDaySlot(Long specialistId, LocalDate date, DaySlot daySlot) {
        EntityManager em = getEntityManager();
        try {
            List<TimeSlot> results = em.createQuery(
                "SELECT ts FROM TimeSlot ts WHERE ts.specialist.id = :specialistId AND ts.date = :date AND ts.daySlot = :daySlot",
                TimeSlot.class)
                .setParameter("specialistId", specialistId)
                .setParameter("date", date)
                .setParameter("daySlot", daySlot)
                .getResultList();

            return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
        } catch (Exception e) {
            throw new RuntimeException("Error finding time slot by specialist, date and day slot", e);
        }
    }

    @Override
    public List<TimeSlot> findAvailableSlotsBySpecialistAndDate(Long specialistId, LocalDate date) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery(
                "SELECT ts FROM TimeSlot ts WHERE ts.specialist.id = :specialistId AND ts.date = :date AND ts.consultation IS NULL",
                TimeSlot.class)
                .setParameter("specialistId", specialistId)
                .setParameter("date", date)
                .getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error finding available time slots", e);
        }
    }

    @Override
    public List<TimeSlot> findBookedSlotsBySpecialistAndDate(Long specialistId, LocalDate date) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery(
                "SELECT ts FROM TimeSlot ts WHERE ts.specialist.id = :specialistId AND ts.date = :date AND ts.consultation IS NOT NULL",
                TimeSlot.class)
                .setParameter("specialistId", specialistId)
                .setParameter("date", date)
                .getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error finding booked time slots", e);
        }
    }

    @Override
    public boolean isSlotBooked(Long specialistId, LocalDate date, DaySlot daySlot) {
        EntityManager em = getEntityManager();
        try {
            Long count = em.createQuery(
                "SELECT COUNT(ts) FROM TimeSlot ts WHERE ts.specialist.id = :specialistId AND ts.date = :date AND ts.daySlot = :daySlot",
                Long.class)
                .setParameter("specialistId", specialistId)
                .setParameter("date", date)
                .setParameter("daySlot", daySlot)
                .getSingleResult();

            return count > 0;
        } catch (Exception e) {
            throw new RuntimeException("Error checking if slot is booked", e);
        }
    }

    @Override
    public List<TimeSlot> findByConsultationId(Long consultationId) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery(
                "SELECT ts FROM TimeSlot ts WHERE ts.consultation.id = :consultationId",
                TimeSlot.class)
                .setParameter("consultationId", consultationId)
                .getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error finding time slots by consultation", e);
        }
    }

    @Override
    public void deleteBySpecialistIdAndDate(Long specialistId, LocalDate date) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.createQuery(
                "DELETE FROM TimeSlot ts WHERE ts.specialist.id = :specialistId AND ts.date = :date")
                .setParameter("specialistId", specialistId)
                .setParameter("date", date)
                .executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException("Error deleting time slots by specialist and date", e);
        }
    }
}