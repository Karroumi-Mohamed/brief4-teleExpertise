package com.tele.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PatientDTO {
    private Long id;
    private String fname;
    private String lname;
    private LocalDate birthDate;
    private String socialSecurityNumber;
    private String phone;
    private String address;
    private LocalDateTime createdAt;
    private VitalSignsDTO vitalSigns;

    public PatientDTO() {
    }

    public PatientDTO(Long id, String fname, String lname, LocalDate birthDate,
                     String socialSecurityNumber, String phone, String address,
                     LocalDateTime createdAt, VitalSignsDTO vitalSigns) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.birthDate = birthDate;
        this.socialSecurityNumber = socialSecurityNumber;
        this.phone = phone;
        this.address = address;
        this.createdAt = createdAt;
        this.vitalSigns = vitalSigns;
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
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
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

    public VitalSignsDTO getVitalSigns() {
        return vitalSigns;
    }

    public void setVitalSigns(VitalSignsDTO vitalSigns) {
        this.vitalSigns = vitalSigns;
    }

    public String getFullName() {
        return fname + " " + lname;
    }
}
