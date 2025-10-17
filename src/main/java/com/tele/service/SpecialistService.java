package com.tele.service;

import com.tele.dto.SpecialistDTO;
import com.tele.model.enums.Specialty;

import java.util.List;

public interface SpecialistService {

    List<SpecialistDTO> findBySpecialtyOrderByFee(Specialty specialty);

    List<SpecialistDTO> findAvailableSpecialists();

    List<SpecialistDTO> findAll();
}