package com.tele.service;

import com.tele.dto.PatientDTO;
import com.tele.dto.VitalSignsDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PatientService {

    PatientDTO createPatient(String fname, String lname, LocalDate birthDate,
                           String socialSecurityNumber, String phone, String address,
                           String mutuelle, String allergies, String currentTreatments,
                           VitalSignsDTO vitalSigns);

    PatientDTO updateVitalSigns(Long patientId, VitalSignsDTO vitalSigns);

    PatientDTO updatePatientInfo(Long patientId, String phone, String address,
                                String mutuelle, String allergies, String currentTreatments);

    Optional<PatientDTO> findBySocialSecurityNumber(String ssn);

    List<PatientDTO> findByRegistrationDate(LocalDate date);

    List<PatientDTO> findByName(String name);

    Optional<PatientDTO> findById(Long patientId);

    long count();
}
