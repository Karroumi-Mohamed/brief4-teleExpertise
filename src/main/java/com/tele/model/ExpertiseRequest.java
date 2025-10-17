package com.tele.model;

import jakarta.persistence.*;
import com.tele.model.enums.ExpertiseStatus;
import com.tele.model.enums.Priority;

import java.time.LocalDateTime;

@Entity
@Table(name = "expertise_requests")
public class ExpertiseRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @Column(name = "question", nullable = false, columnDefinition = "TEXT")
    private String question;

    @Column(name = "medical_data", columnDefinition = "TEXT")
    private String medicalData;

    @Enumerated(EnumType.STRING)
    @Column(name = "priority", nullable = false, length = 20)
    private Priority priority;

    @Column(name = "advice", columnDefinition = "TEXT")
    private String advice;

    @Column(name = "recommendations", columnDefinition = "TEXT")
    private String recommendations;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private ExpertiseStatus status;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "responded_at")
    private LocalDateTime respondedAt;



    @OneToOne
    @JoinColumn(name = "consultation_id", nullable = false)
    private Consultation consultation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "requesting_doctor_id", nullable = false)
    private GeneralPractitioner requestingDoctor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "specialist_id", nullable = false)
    private Specialist specialist;


    public ExpertiseRequest() {
        this.createdAt = LocalDateTime.now();
        this.status = ExpertiseStatus.PENDING;
    }

    public ExpertiseRequest(String question, String medicalData, Priority priority,
                          Consultation consultation, GeneralPractitioner requestingDoctor,
                          Specialist specialist) {
        this();
        this.question = question;
        this.medicalData = medicalData;
        this.priority = priority;
        this.consultation = consultation;
        this.requestingDoctor = requestingDoctor;
        this.specialist = specialist;
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

    public Consultation getConsultation() {
        return consultation;
    }

    public void setConsultation(Consultation consultation) {
        this.consultation = consultation;
    }

    public GeneralPractitioner getRequestingDoctor() {
        return requestingDoctor;
    }

    public void setRequestingDoctor(GeneralPractitioner requestingDoctor) {
        this.requestingDoctor = requestingDoctor;
    }

    public Specialist getSpecialist() {
        return specialist;
    }

    public void setSpecialist(Specialist specialist) {
        this.specialist = specialist;
    }


    public void respond(String advice, String recommendations) {
        this.advice = advice;
        this.recommendations = recommendations;
        this.status = ExpertiseStatus.COMPLETED;
        this.respondedAt = LocalDateTime.now();
    }
}
