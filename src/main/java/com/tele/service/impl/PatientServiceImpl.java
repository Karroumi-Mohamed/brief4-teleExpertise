package com.tele.service.impl;

import com.tele.dao.PatientDAO;
import com.tele.dao.impl.PatientDAOImpl;
import com.tele.dto.PatientDTO;
import com.tele.dto.VitalSignsDTO;
import com.tele.model.Patient;
import com.tele.model.VitalSigns;
import com.tele.service.PatientService;
import com.tele.util.DTOMapper;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PatientServiceImpl implements PatientService {

    private final PatientDAO patientDAO;

    public PatientServiceImpl() {
        this.patientDAO = new PatientDAOImpl();
    }

    public PatientServiceImpl(PatientDAO patientDAO) {
        this.patientDAO = patientDAO;
    }

    @Override
    public PatientDTO createPatient(String fname, String lname, LocalDate birthDate,
                                  String socialSecurityNumber, String phone, String address,
                                  VitalSignsDTO vitalSignsDTO) {

        VitalSigns vitalSigns = DTOMapper.toEntity(vitalSignsDTO);
        Patient patient = new Patient(fname, lname, birthDate, socialSecurityNumber, phone, address);
        patient.setVitalSigns(vitalSigns);

        Patient savedPatient = patientDAO.save(patient);
        return DTOMapper.toDTO(savedPatient);
    }

    @Override
    public PatientDTO updateVitalSigns(Long patientId, VitalSignsDTO vitalSignsDTO) {
        Optional<Patient> patientOpt = patientDAO.findById(patientId);
        if (!patientOpt.isPresent()) {
            return null;
        }

        Patient patient = patientOpt.get();
        VitalSigns vitalSigns = DTOMapper.toEntity(vitalSignsDTO);
        patient.setVitalSigns(vitalSigns);

        Patient updatedPatient = patientDAO.update(patient);
        return DTOMapper.toDTO(updatedPatient);
    }

    @Override
    public Optional<PatientDTO> findBySocialSecurityNumber(String ssn) {
        return patientDAO.findBySocialSecurityNumber(ssn)
                .map(DTOMapper::toDTO);
    }

    @Override
    public List<PatientDTO> findByRegistrationDate(LocalDate date) {
        return patientDAO.findByCreatedAt(date)
                .stream()
                .map(DTOMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PatientDTO> findByName(String name) {
        return patientDAO.findByName(name)
                .stream()
                .map(DTOMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<PatientDTO> findById(Long patientId) {
        return patientDAO.findById(patientId)
                .map(DTOMapper::toDTO);
    }

    @Override
    public long count(){
        return patientDAO.count();
    }
}