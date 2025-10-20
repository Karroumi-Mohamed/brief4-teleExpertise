package com.tele.service.impl;

import com.tele.dao.PatientDAO;
import com.tele.dao.QueueDAO;
import com.tele.dao.impl.PatientDAOImpl;
import com.tele.dao.impl.QueueDAOImpl;
import com.tele.dto.QueueEntryDTO;
import com.tele.model.Patient;
import com.tele.model.QueueEntry;
import com.tele.model.enums.QueueStatus;
import com.tele.service.QueueService;
import com.tele.util.DTOMapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
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
    public QueueEntryDTO addPatientToQueue(Long patientId) {
        Patient patient = patientDAO.findById(patientId).orElse(null);
        if (patient == null) {
            return null;
        }

        QueueEntry queueEntry = new QueueEntry(patient, LocalDateTime.now(), QueueStatus.WAITING);
        QueueEntry savedEntry = queueDAO.save(queueEntry);
        return DTOMapper.toDTO(savedEntry);
    }

    @Override
    public void updateQueueEntryStatus(Long queueEntryId, QueueStatus status) {
        QueueEntry queueEntry = queueDAO.findById(queueEntryId).orElse(null);
        if (queueEntry != null) {
            queueEntry.setStatus(status);
            queueDAO.update(queueEntry);
        }
    }

    @Override
    public void removeFromQueue(Long queueEntryId) {
        queueDAO.deleteById(queueEntryId);
    }

    @Override
    public Optional<QueueEntryDTO> getActiveQueueEntryByPatientId(Long patientId) {
        Optional<QueueEntry> queueEntryOpt = queueDAO.findAll().stream()
                .filter(q -> q.getPatient().getId().equals(patientId) && q.getStatus() == QueueStatus.WAITING)
                .findFirst();
        return queueEntryOpt.map(DTOMapper::toDTO);
    }

    @Override
    public List<QueueEntryDTO> getQueue() {
        return queueDAO.findAll()
                .stream()
                .sorted((q1, q2) -> q1.getArrivalTime().compareTo(q2.getArrivalTime()))
                .map(DTOMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<QueueEntryDTO> getQueueEntryById(Long queueEntryId) {
        Optional<QueueEntry> queueEntryOpt = queueDAO.findById(queueEntryId);
        return queueEntryOpt.map(DTOMapper::toDTO);
    }

    @Override
    public List<QueueEntryDTO> getQueueByStatus(QueueStatus status) {
        return queueDAO.findByStatus(status)
                .stream()
                .map(DTOMapper::toDTO)
                .collect(Collectors.toList());
    }
}
