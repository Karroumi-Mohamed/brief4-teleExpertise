package com.tele.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

import com.tele.model.enums.Specialty;
import com.tele.model.enums.UserRole;

@Entity
@DiscriminatorValue("SPECIALIST")
public class Specialist extends User {

    @Enumerated(EnumType.STRING)
    @Column(name = "specialty", length = 50)
    private Specialty specialty;

    @Column(name = "expertise_fee")
    private Double expertiseFee;

    @Column(name = "avg_consultation_duration")
    private Integer avgConsultationDuration = 30;


    @OneToMany(mappedBy = "specialist", fetch = FetchType.LAZY)
    private List<ExpertiseRequest> receivedRequests = new ArrayList<>();



    public Specialist() {
        super();
        setRole(UserRole.SPECIALIST);
        this.avgConsultationDuration = 30;
    }

    public Specialist(String email, String passwordHash, String firstName, String lastName) {
        super(email, passwordHash, firstName, lastName, UserRole.SPECIALIST);
        this.avgConsultationDuration = 30;
    }

    public Specialist(String email, String passwordHash, String firstName, String lastName,
                     Specialty specialty, Double expertiseFee) {
        super(email, passwordHash, firstName, lastName, UserRole.SPECIALIST);
        this.specialty = specialty;
        this.expertiseFee = expertiseFee;
        this.avgConsultationDuration = 30;
    }


    public Specialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }

    public Double getExpertiseFee() {
        return expertiseFee;
    }

    public void setExpertiseFee(Double expertiseFee) {
        this.expertiseFee = expertiseFee;
    }

    public Integer getAvgConsultationDuration() {
        return avgConsultationDuration;
    }

    public void setAvgConsultationDuration(Integer avgConsultationDuration) {
        this.avgConsultationDuration = avgConsultationDuration;
    }

    public List<ExpertiseRequest> getReceivedRequests() {
        return receivedRequests;
    }

    public void setReceivedRequests(List<ExpertiseRequest> receivedRequests) {
        this.receivedRequests = receivedRequests;
    }


    public void addExpertiseRequest(ExpertiseRequest request) {
        receivedRequests.add(request);
        request.setSpecialist(this);
    }

    public void removeExpertiseRequest(ExpertiseRequest request) {
        receivedRequests.remove(request);
        request.setSpecialist(null);
    }

}
