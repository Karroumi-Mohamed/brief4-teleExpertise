package com.tele.dto;

import com.tele.model.enums.UserRole;

public class UserDTO {
    private Long id;
    private String email;
    private String fname;
    private String lname;
    private String phone;
    private UserRole role;

    public UserDTO() {
    }

    public UserDTO(Long id, String email, String fname, String lname, String phone, UserRole role) {
        this.id = id;
        this.email = email;
        this.fname = fname;
        this.lname = lname;
        this.phone = phone;
        this.role = role;
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

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getFullName() {
        return fname + " " + lname;
    }
}
