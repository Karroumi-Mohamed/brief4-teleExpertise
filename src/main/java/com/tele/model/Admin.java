package com.tele.model;

import com.tele.model.enums.UserRole;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("ADMIN")
public class Admin extends User{
    public Admin(){
        super();
        setRole(UserRole.ADMIN);
    }

    public Admin(String email, String passwordHash, String fname, String lname){
        super(email, passwordHash, fname, lname, UserRole.ADMIN);
    }

    public Admin(String email, String passwordHash, String fname, String lname, String phone){
        super(email, passwordHash, fname, lname, phone, UserRole.ADMIN);
    }
}
