package com.tele.dao.impl;

import java.util.List;

import com.tele.dao.QueueDAO;
import com.tele.model.QueueEntry;
import com.tele.model.enums.QueueStatus;

import jakarta.persistence.EntityManager;

public class QueueDAOImpl extends GenericDAOImpl<QueueEntry, Long> implements QueueDAO {
    public QueueDAOImpl() {
        super(QueueEntry.class);
    }

    public List<QueueEntry> findByStatus(QueueStatus status) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery(
                "SELECT q FROM QueueEntry q JOIN FETCH q.patient WHERE q.status = :status ORDER BY q.arrivalTime ASC",
                QueueEntry.class)
                .setParameter("status", status)
                .getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error finding queue entries by status", e);
        } finally {
            em.close();
        }
    }
}
