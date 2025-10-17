package com.tele.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

import com.tele.model.enums.UserRole;

@Entity
@DiscriminatorValue("GENERAL_PRACTITIONER")
public class GeneralPractitioner extends User {

    @Column(name = "consultation_fee")
    private Double consultationFee = 150.0;


    @OneToMany(mappedBy = "generalPractitioner", fetch = FetchType.LAZY)
    private List<Consultation> consultations = new ArrayList<>();

    @OneToMany(mappedBy = "requestingDoctor", fetch = FetchType.LAZY)
    private List<ExpertiseRequest> expertiseRequests = new ArrayList<>();


    public GeneralPractitioner() {
        super();
        setRole(UserRole.GENERAL_PRACTITIONER);
        this.consultationFee = 150.0;
    }

    public GeneralPractitioner(String email, String passwordHash, String firstName, String lastName) {
        super(email, passwordHash, firstName, lastName, UserRole.GENERAL_PRACTITIONER);
        this.consultationFee = 150.0;
    }


    public Double getConsultationFee() {
        return consultationFee;
    }

    public void setConsultationFee(Double consultationFee) {
        this.consultationFee = consultationFee;
    }

    public List<Consultation> getConsultations() {
        return consultations;
    }

    public void setConsultations(List<Consultation> consultations) {
        this.consultations = consultations;
    }

    public List<ExpertiseRequest> getExpertiseRequests() {
        return expertiseRequests;
    }

    public void setExpertiseRequests(List<ExpertiseRequest> expertiseRequests) {
        this.expertiseRequests = expertiseRequests;
    }


    public void addConsultation(Consultation consultation) {
        consultations.add(consultation);
        consultation.setGeneralPractitioner(this);
    }

    public void removeConsultation(Consultation consultation) {
        consultations.remove(consultation);
        consultation.setGeneralPractitioner(null);
    }

    public void addExpertiseRequest(ExpertiseRequest request) {
        expertiseRequests.add(request);
        request.setRequestingDoctor(this);
    }

    public void removeExpertiseRequest(ExpertiseRequest request) {
        expertiseRequests.remove(request);
        request.setRequestingDoctor(null);
    }
}
