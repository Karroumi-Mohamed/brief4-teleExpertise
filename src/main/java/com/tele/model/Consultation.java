package com.tele.model;

import jakarta.persistence.*;
import com.tele.model.enums.ConsultationStatus;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "consultations")
public class Consultation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @Column(name = "reason", nullable = false, length = 500)
    private String reason;

    @Column(name = "observations", columnDefinition = "TEXT")
    private String observations;

    @Column(name = "symptoms", columnDefinition = "TEXT")
    private String symptoms;

    @Column(name = "diagnosis", columnDefinition = "TEXT")
    private String diagnosis;

    @Column(name = "treatment", columnDefinition = "TEXT")
    private String treatment;

    @Column(name = "consultation_date", nullable = false)
    private LocalDateTime consultationDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 50)
    private ConsultationStatus status;

    @Column(name = "cost", nullable = false)
    private Double cost = 150.0;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "general_practitioner_id", nullable = false)
    private GeneralPractitioner generalPractitioner;

    @OneToOne(mappedBy = "consultation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private ExpertiseRequest expertiseRequest;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "consultation_procedures",
        joinColumns = @JoinColumn(name = "consultation_id"),
        inverseJoinColumns = @JoinColumn(name = "procedure_id")
    )
    private List<MedicalProcedure> medicalProcedures = new ArrayList<>();


    public Consultation() {
        this.consultationDate = LocalDateTime.now();
        this.status = ConsultationStatus.IN_PROGRESS;
        this.cost = 150.0;
    }

    public Consultation(String reason, Patient patient, GeneralPractitioner generalPractitioner) {
        this();
        this.reason = reason;
        this.patient = patient;
        this.generalPractitioner = generalPractitioner;
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

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public GeneralPractitioner getGeneralPractitioner() {
        return generalPractitioner;
    }

    public void setGeneralPractitioner(GeneralPractitioner generalPractitioner) {
        this.generalPractitioner = generalPractitioner;
    }

    public ExpertiseRequest getExpertiseRequest() {
        return expertiseRequest;
    }

    public void setExpertiseRequest(ExpertiseRequest expertiseRequest) {
        this.expertiseRequest = expertiseRequest;
    }

    public List<MedicalProcedure> getMedicalProcedures() {
        return medicalProcedures;
    }

    public void setMedicalProcedures(List<MedicalProcedure> medicalProcedures) {
        this.medicalProcedures = medicalProcedures;
    }


    public void addMedicalProcedure(MedicalProcedure procedure) {
        medicalProcedures.add(procedure);
    }

    public void removeMedicalProcedure(MedicalProcedure procedure) {
        medicalProcedures.remove(procedure);
    }

    public void requestExpertise(ExpertiseRequest request) {
        this.expertiseRequest = request;
        this.status = ConsultationStatus.AWAITING_SPECIALIST_ADVICE;
        request.setConsultation(this);
    }

    public void completeConsultation() {
        this.status = ConsultationStatus.COMPLETED;
    }

    public Double calculateTotalCost() {
        Double total = this.cost;

        for (MedicalProcedure procedure : medicalProcedures) {
            total += procedure.getCost();
        }

        if (expertiseRequest != null && expertiseRequest.getSpecialist() != null) {
            total += expertiseRequest.getSpecialist().getExpertiseFee();
        }

        return total;
    }
}
