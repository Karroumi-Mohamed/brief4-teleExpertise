package com.tele.dao;

import com.tele.model.Patient;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PatientDAO extends GenericDAO<Patient, Long> {

    Optional<Patient> findBySocialSecurityNumber(String socialSecurityNumber);

    List<Patient> findByCreatedAt(LocalDate date);

    List<Patient> findByName(String name);

    boolean existsBySocialSecurityNumber(String socialSecurityNumber);
}
