package com.tele.service;

import com.tele.dto.NurseDTO;

import java.util.List;
import java.util.Optional;

public interface NurseService {

    NurseDTO createNurse(String fname, String lname, String phone, String email, String passwordHash);

    void deleteNurse(Long id);

    void updateNurse(Long id, String fname, String lname, String phone, String email);

    Optional<NurseDTO> findById(Long id);

    List<NurseDTO> findAll();
}
