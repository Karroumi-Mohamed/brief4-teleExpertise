package com.tele.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fname", nullable = false, length = 50)
    private String fname;

    @Column(name = "lname", nullable = false, length = 50)
    private String lname;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(name = "social_security_number", nullable = false, unique = true, length = 50)
    private String SocialSecurityNumber;

    @Column(name = "phone", nullable = false, length = 15)
    private String phone;

    @Column(name = "address", length = 100)
    private String address;

    @Column(name = "mutuelle", length = 100)
    private String mutuelle;

    @Column(name = "allergies", columnDefinition = "TEXT")
    private String allergies;

    @Column(name = "current_treatments", columnDefinition = "TEXT")
    private String currentTreatments;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Embedded
    private VitalSigns vitalSigns;

    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY)
    private List<Consultation> consultations = new ArrayList<>();

    public Patient() {
        createdAt = LocalDateTime.now();
    }

    public Patient(String fname, String lname, LocalDate birthDate, String socialSecurityNumber, String phone,
            String address) {
        this.fname = fname;
        this.lname = lname;
        this.birthDate = birthDate;
        SocialSecurityNumber = socialSecurityNumber;
        this.phone = phone;
        this.address = address;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getSocialSecurityNumber() {
        return SocialSecurityNumber;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        SocialSecurityNumber = socialSecurityNumber;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public VitalSigns getVitalSigns() {
        return vitalSigns;
    }

    public void setVitalSigns(VitalSigns vitalSigns) {
        this.vitalSigns = vitalSigns;
    }

    public String getMutuelle() {
        return mutuelle;
    }

    public void setMutuelle(String mutuelle) {
        this.mutuelle = mutuelle;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public String getCurrentTreatments() {
        return currentTreatments;
    }

    public void setCurrentTreatments(String currentTreatments) {
        this.currentTreatments = currentTreatments;
    }

    public List<Consultation> getConsultations() {
        return consultations;
    }

    public void setConsultations(List<Consultation> consultations) {
        this.consultations = consultations;
    }
}
