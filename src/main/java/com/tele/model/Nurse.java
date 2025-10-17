package com.tele.model;

import com.tele.model.enums.UserRole;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("NURSE")
public class Nurse extends User {

    public Nurse() {
        super();
        setRole(UserRole.NURSE);
    }

    public Nurse(String email, String passwordHash, String fname, String lname) {
        super(email, passwordHash, fname, lname, UserRole.NURSE);
    }
    
    public Nurse(String email, String passwordHash, String fname, String lname, String phone) {
        super(email, passwordHash, fname, lname, phone, UserRole.NURSE);
    }
}
