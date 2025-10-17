package com.tele.dto;

import com.tele.model.enums.ConsultationStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ConsultationDTO {
    private Long id;
    private String reason;
    private String observations;
    private String symptoms;
    private String diagnosis;
    private String treatment;
    private LocalDateTime consultationDate;
    private ConsultationStatus status;
    private Double cost;
    private Long patientId;
    private String patientName;
    private Long generalPractitionerId;
    private String generalPractitionerName;
    private List<Long> medicalProcedureIds = new ArrayList<>();
    private Double totalCost;

    public ConsultationDTO() {
    }

    public ConsultationDTO(Long id, String reason, String observations, String symptoms,
                          String diagnosis, String treatment, LocalDateTime consultationDate,
                          ConsultationStatus status, Double cost, Long patientId,
                          String patientName, Long generalPractitionerId,
                          String generalPractitionerName) {
        this.id = id;
        this.reason = reason;
        this.observations = observations;
        this.symptoms = symptoms;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.consultationDate = consultationDate;
        this.status = status;
        this.cost = cost;
        this.patientId = patientId;
        this.patientName = patientName;
        this.generalPractitionerId = generalPractitionerId;
        this.generalPractitionerName = generalPractitionerName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public LocalDateTime getConsultationDate() {
        return consultationDate;
    }

    public void setConsultationDate(LocalDateTime consultationDate) {
        this.consultationDate = consultationDate;
    }

    public ConsultationStatus getStatus() {
        return status;
    }

    public void setStatus(ConsultationStatus status) {
        this.status = status;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
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

    public Long getGeneralPractitionerId() {
        return generalPractitionerId;
    }

    public void setGeneralPractitionerId(Long generalPractitionerId) {
        this.generalPractitionerId = generalPractitionerId;
    }

    public String getGeneralPractitionerName() {
        return generalPractitionerName;
    }

    public void setGeneralPractitionerName(String generalPractitionerName) {
        this.generalPractitionerName = generalPractitionerName;
    }

    public List<Long> getMedicalProcedureIds() {
        return medicalProcedureIds;
    }

    public void setMedicalProcedureIds(List<Long> medicalProcedureIds) {
        this.medicalProcedureIds = medicalProcedureIds;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }
}
