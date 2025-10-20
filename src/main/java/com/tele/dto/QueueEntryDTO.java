package com.tele.dto;

import com.tele.model.enums.QueueStatus;
import java.time.LocalDateTime;

public class QueueEntryDTO {
    private Long id;
    private Long patientId;
    private String patientName;
    private LocalDateTime arrivalTime;
    private QueueStatus status;

    public QueueEntryDTO() {
    }

    public QueueEntryDTO(Long id, Long patientId, String patientName, LocalDateTime arrivalTime, QueueStatus status) {
        this.id = id;
        this.patientId = patientId;
        this.patientName = patientName;
        this.arrivalTime = arrivalTime;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public QueueStatus getStatus() {
        return status;
    }

    public void setStatus(QueueStatus status) {
        this.status = status;
    }
}
