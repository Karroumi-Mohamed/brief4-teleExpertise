package com.tele.service;

import com.tele.dto.QueueEntryDTO;
import com.tele.model.enums.QueueStatus;

import java.util.List;
import java.util.Optional;

public interface QueueService {

    QueueEntryDTO addPatientToQueue(Long patientId);

    void removeFromQueue(Long queueEntryId);

    void updateQueueEntryStatus(Long queueEntryId, QueueStatus status);

    Optional<QueueEntryDTO> getQueueEntryById(Long queueEntryId);

    Optional<QueueEntryDTO> getActiveQueueEntryByPatientId(Long patientId);

    List<QueueEntryDTO> getQueue();

    List<QueueEntryDTO> getQueueByStatus(QueueStatus status);
}
