package com.tele.service;

import com.tele.dto.SpecialistDTO;
import com.tele.model.enums.Specialty;

import java.util.List;
import java.util.Optional;

public interface SpecialistService {

    SpecialistDTO createSpecialist(String fname, String lname, Specialty specialty, Double expertiseFee, String phone, String email, String passwordHash, Integer avgDuration);

    List<SpecialistDTO> findBySpecialtyOrderByFee(Specialty specialty);

    List<SpecialistDTO> findAvailableSpecialists();

    List<SpecialistDTO> findAll();

    Optional<SpecialistDTO> findById(Long id);

    void deleteSpecialist(Long id);

    void updateSpecialist(Long id, String fname, String lname, Specialty specialty, Double expertiseFee, String phone, String email, Integer avgDuration);
}
