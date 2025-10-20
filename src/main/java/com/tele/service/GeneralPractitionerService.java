package com.tele.service;

import com.tele.dto.GeneralPractitionerDTO;

import java.util.List;
import java.util.Optional;

public interface GeneralPractitionerService {

    GeneralPractitionerDTO createGeneralPractitioner(String fname, String lname, String phone, String email, String passwordHash);

    void deleteGeneralPractitioner(Long id);

    void updateGeneralPractitioner(Long id, String fname, String lname, String phone, String email);

    Optional<GeneralPractitionerDTO> findById(Long id);

    List<GeneralPractitionerDTO> findAll();
}
