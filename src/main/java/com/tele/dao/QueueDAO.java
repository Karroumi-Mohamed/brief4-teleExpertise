package com.tele.dao;

import java.util.List;

import com.tele.model.QueueEntry;
import com.tele.model.enums.QueueStatus;

public interface QueueDAO extends GenericDAO<QueueEntry, Long> {
    List<QueueEntry> findByStatus(QueueStatus status);
}
