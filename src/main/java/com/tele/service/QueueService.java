package com.tele.service;

import com.tele.model.QueueEntry;
import com.tele.model.enums.QueueStatus;

import java.util.List;

public interface QueueService {

    QueueEntry addPatientToQueue(Long patientId);

    void removeFromQueue(Long queueEntryId);

    List<QueueEntry> getQueue();

    List<QueueEntry> getQueueByStatus(QueueStatus status);
}