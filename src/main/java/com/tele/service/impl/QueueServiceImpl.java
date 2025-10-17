package com.tele.service.impl;

import com.tele.dao.PatientDAO;
import com.tele.dao.QueueDAO;
import com.tele.dao.impl.PatientDAOImpl;
import com.tele.dao.impl.QueueDAOImpl;
import com.tele.model.Patient;
import com.tele.model.QueueEntry;
import com.tele.model.enums.QueueStatus;
import com.tele.service.QueueService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class QueueServiceImpl implements QueueService {

    private final QueueDAO queueDAO;
    private final PatientDAO patientDAO;

    public QueueServiceImpl() {
        this.queueDAO = new QueueDAOImpl();
        this.patientDAO = new PatientDAOImpl();
    }

    public QueueServiceImpl(QueueDAO queueDAO, PatientDAO patientDAO) {
        this.queueDAO = queueDAO;
        this.patientDAO = patientDAO;
    }

    @Override
    public QueueEntry addPatientToQueue(Long patientId) {
        Patient patient = patientDAO.findById(patientId).orElse(null);
        if (patient == null) {
            return null;
        }

        QueueEntry queueEntry = new QueueEntry(patient, LocalDateTime.now(), QueueStatus.WAITING);
        return queueDAO.save(queueEntry);
    }

    @Override
    public void removeFromQueue(Long queueEntryId) {
        queueDAO.deleteById(queueEntryId);
    }

    @Override
    public List<QueueEntry> getQueue() {
        return queueDAO.findAll()
                .stream()
                .sorted((q1, q2) -> q1.getArrivalTime().compareTo(q2.getArrivalTime()))
                .collect(Collectors.toList());
    }

    @Override
    public List<QueueEntry> getQueueByStatus(QueueStatus status) {
        return queueDAO.findByStatus(status);
    }
}