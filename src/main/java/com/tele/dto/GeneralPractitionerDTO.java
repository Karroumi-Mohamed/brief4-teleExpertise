package com.tele.dto;

public class GeneralPractitionerDTO {
    private Long id;
    private String email;
    private String fname;
    private String lname;
    private String phone;
    private Double consultationFee;

    public GeneralPractitionerDTO() {
    }

    public GeneralPractitionerDTO(Long id, String email, String fname, String lname, String phone, Double consultationFee) {
        this.id = id;
        this.email = email;
        this.fname = fname;
        this.lname = lname;
        this.phone = phone;
        this.consultationFee = consultationFee;
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

    public Double getConsultationFee() {
        return consultationFee;
    }

    public void setConsultationFee(Double consultationFee) {
        this.consultationFee = consultationFee;
    }

    public String getFullName() {
        return fname + " " + lname;
    }
}
