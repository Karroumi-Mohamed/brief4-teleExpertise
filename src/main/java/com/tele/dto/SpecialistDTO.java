package com.tele.dto;

import com.tele.model.enums.Specialty;

public class SpecialistDTO {
    private Long id;
    private String email;
    private String fname;
    private String lname;
    private String phone;
    private Specialty specialty;
    private Double expertiseFee;
    private Integer avgConsultationDuration;

    public SpecialistDTO() {
    }

    public SpecialistDTO(Long id, String email, String fname, String lname, String phone,
                        Specialty specialty, Double expertiseFee, Integer avgConsultationDuration) {
        this.id = id;
        this.email = email;
        this.fname = fname;
        this.lname = lname;
        this.phone = phone;
        this.specialty = specialty;
        this.expertiseFee = expertiseFee;
        this.avgConsultationDuration = avgConsultationDuration;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getFullName() {
        return fname + " " + lname;
    }
}
