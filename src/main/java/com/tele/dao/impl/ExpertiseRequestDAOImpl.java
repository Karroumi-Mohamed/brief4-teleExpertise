package com.tele.dao.impl;

import jakarta.persistence.EntityManager;
import com.tele.dao.ExpertiseRequestDAO;
import com.tele.model.ExpertiseRequest;
import com.tele.model.enums.ExpertiseStatus;
import com.tele.model.enums.Priority;

import java.util.List;

public class ExpertiseRequestDAOImpl extends GenericDAOImpl<ExpertiseRequest, Long> implements ExpertiseRequestDAO {

    public ExpertiseRequestDAOImpl() {
        super(ExpertiseRequest.class);
    }

    @Override
    public List<ExpertiseRequest> findBySpecialistId(Long specialistId) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery(
                "SELECT e FROM ExpertiseRequest e WHERE e.specialist.id = :specialistId ORDER BY e.createdAt DESC",
                ExpertiseRequest.class)
                .setParameter("specialistId", specialistId)
                .getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error finding expertise requests by specialist ID", e);
        }
    }

    @Override
    public List<ExpertiseRequest> findByStatus(ExpertiseStatus status) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery(
                "SELECT e FROM ExpertiseRequest e WHERE e.status = :status ORDER BY e.createdAt DESC",
                ExpertiseRequest.class)
                .setParameter("status", status)
                .getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error finding expertise requests by status", e);
        }
    }

    @Override
    public List<ExpertiseRequest> findByPriority(Priority priority) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery(
                "SELECT e FROM ExpertiseRequest e WHERE e.priority = :priority ORDER BY e.createdAt DESC",
                ExpertiseRequest.class)
                .setParameter("priority", priority)
                .getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error finding expertise requests by priority", e);
        }
    }

    @Override
    public List<ExpertiseRequest> findBySpecialistIdAndStatus(Long specialistId, ExpertiseStatus status) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery(
                "SELECT e FROM ExpertiseRequest e WHERE e.specialist.id = :specialistId AND e.status = :status ORDER BY e.priority, e.createdAt DESC",
                ExpertiseRequest.class)
                .setParameter("specialistId", specialistId)
                .setParameter("status", status)
                .getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error finding expertise requests by specialist and status", e);
        }
    }

    @Override
    public List<ExpertiseRequest> findByGPId(Long gpId) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery(
                "SELECT e FROM ExpertiseRequest e WHERE e.requestingDoctor.id = :gpId ORDER BY e.createdAt DESC",
                ExpertiseRequest.class)
                .setParameter("gpId", gpId)
                .getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error finding expertise requests by GP ID", e);
        }
    }
}
