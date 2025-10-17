package com.tele.dto;

import com.tele.model.enums.ExpertiseStatus;
import com.tele.model.enums.Priority;

import java.time.LocalDateTime;

public class ExpertiseRequestDTO {
    private Long id;
    private String question;
    private String medicalData;
    private Priority priority;
    private String advice;
    private String recommendations;
    private ExpertiseStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime respondedAt;
    private Long consultationId;
    private Long requestingDoctorId;
    private String requestingDoctorName;
    private Long specialistId;
    private String specialistName;
    private String specialistSpecialty;

    public ExpertiseRequestDTO() {
    }

    public ExpertiseRequestDTO(Long id, String question, String medicalData, Priority priority,
                              String advice, String recommendations, ExpertiseStatus status,
                              LocalDateTime createdAt, LocalDateTime respondedAt,
                              Long consultationId, Long requestingDoctorId,
                              String requestingDoctorName, Long specialistId,
                              String specialistName, String specialistSpecialty) {
        this.id = id;
        this.question = question;
        this.medicalData = medicalData;
        this.priority = priority;
        this.advice = advice;
        this.recommendations = recommendations;
        this.status = status;
        this.createdAt = createdAt;
        this.respondedAt = respondedAt;
        this.consultationId = consultationId;
        this.requestingDoctorId = requestingDoctorId;
        this.requestingDoctorName = requestingDoctorName;
        this.specialistId = specialistId;
        this.specialistName = specialistName;
        this.specialistSpecialty = specialistSpecialty;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getMedicalData() {
        return medicalData;
    }

    public void setMedicalData(String medicalData) {
        this.medicalData = medicalData;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public String getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(String recommendations) {
        this.recommendations = recommendations;
    }

    public ExpertiseStatus getStatus() {
        return status;
    }

    public void setStatus(ExpertiseStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getRespondedAt() {
        return respondedAt;
    }

    public void setRespondedAt(LocalDateTime respondedAt) {
        this.respondedAt = respondedAt;
    }

    public Long getConsultationId() {
        return consultationId;
    }

    public void setConsultationId(Long consultationId) {
        this.consultationId = consultationId;
    }

    public Long getRequestingDoctorId() {
        return requestingDoctorId;
    }

    public void setRequestingDoctorId(Long requestingDoctorId) {
        this.requestingDoctorId = requestingDoctorId;
    }

    public String getRequestingDoctorName() {
        return requestingDoctorName;
    }

    public void setRequestingDoctorName(String requestingDoctorName) {
        this.requestingDoctorName = requestingDoctorName;
    }

    public Long getSpecialistId() {
        return specialistId;
    }

    public void setSpecialistId(Long specialistId) {
        this.specialistId = specialistId;
    }

    public String getSpecialistName() {
        return specialistName;
    }

    public void setSpecialistName(String specialistName) {
        this.specialistName = specialistName;
    }

    public String getSpecialistSpecialty() {
        return specialistSpecialty;
    }

    public void setSpecialistSpecialty(String specialistSpecialty) {
        this.specialistSpecialty = specialistSpecialty;
    }
 }
