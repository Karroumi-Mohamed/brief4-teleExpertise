package com.tele.service.impl;

import com.tele.dao.ConsultationDAO;
import com.tele.dao.MedicalProcedureDAO;
import com.tele.dao.PatientDAO;
import com.tele.dao.UserDAO;
import com.tele.dao.impl.ConsultationDAOImpl;
import com.tele.dao.impl.MedicalProcedureDAOImpl;
import com.tele.dao.impl.PatientDAOImpl;
import com.tele.dao.impl.UserDAOImpl;
import com.tele.dto.ConsultationDTO;
import com.tele.model.*;
import com.tele.model.enums.ConsultationStatus;
import com.tele.service.ConsultationService;
import com.tele.util.DTOMapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ConsultationServiceImpl implements ConsultationService {

    private final ConsultationDAO consultationDAO;
    private final PatientDAO patientDAO;
    private final UserDAO userDAO;
    private final MedicalProcedureDAO medicalProcedureDAO;

    public ConsultationServiceImpl() {
        this.consultationDAO = new ConsultationDAOImpl();
        this.patientDAO = new PatientDAOImpl();
        this.userDAO = new UserDAOImpl();
        this.medicalProcedureDAO = new MedicalProcedureDAOImpl();
    }

    public ConsultationServiceImpl(ConsultationDAO consultationDAO, PatientDAO patientDAO,
                                 UserDAO userDAO, MedicalProcedureDAO medicalProcedureDAO) {
        this.consultationDAO = consultationDAO;
        this.patientDAO = patientDAO;
        this.userDAO = userDAO;
        this.medicalProcedureDAO = medicalProcedureDAO;
    }

    @Override
    public ConsultationDTO createConsultation(Long patientId, Long generalPractitionerId, String reason) {
        if (patientId == null || generalPractitionerId == null || reason == null || reason.trim().isEmpty()) {
            throw new IllegalArgumentException("Patient ID, GP ID, and reason are required");
        }

        Optional<Patient> patientOpt = patientDAO.findById(patientId);
        if (!patientOpt.isPresent()) {
            throw new IllegalArgumentException("Patient not found");
        }

        Optional<User> userOpt = userDAO.findById(generalPractitionerId);
        if (!userOpt.isPresent() || !(userOpt.get() instanceof GeneralPractitioner)) {
            throw new IllegalArgumentException("General practitioner not found");
        }

        Patient patient = patientOpt.get();
        GeneralPractitioner gp = (GeneralPractitioner) userOpt.get();

        Consultation consultation = new Consultation(reason.trim(), patient, gp);
        Consultation savedConsultation = consultationDAO.save(consultation);

        return DTOMapper.toDTO(savedConsultation);
    }

    @Override
    public ConsultationDTO updateConsultation(Long consultationId, String observations,
                                            String symptoms, String diagnosis, String treatment) {
        if (consultationId == null) {
            throw new IllegalArgumentException("Consultation ID is required");
        }

        Optional<Consultation> consultationOpt = consultationDAO.findById(consultationId);
        if (!consultationOpt.isPresent()) {
            throw new IllegalArgumentException("Consultation not found");
        }

        Consultation consultation = consultationOpt.get();

        if (observations != null) {
            consultation.setObservations(observations.trim());
        }
        if (symptoms != null) {
            consultation.setSymptoms(symptoms.trim());
        }
        if (diagnosis != null) {
            consultation.setDiagnosis(diagnosis.trim());
        }
        if (treatment != null) {
            consultation.setTreatment(treatment.trim());
        }

        Consultation updatedConsultation = consultationDAO.update(consultation);
        return DTOMapper.toDTO(updatedConsultation);
    }

    @Override
    public Double calculateTotalCost(Long consultationId) {
        if (consultationId == null) {
            throw new IllegalArgumentException("Consultation ID is required");
        }

        Optional<Consultation> consultationOpt = consultationDAO.findById(consultationId);
        if (!consultationOpt.isPresent()) {
            throw new IllegalArgumentException("Consultation not found");
        }

        Consultation consultation = consultationOpt.get();

        Double totalCost = consultation.getCost(); // Base consultation cost (150.0)

        Double proceduresCost = consultation.getMedicalProcedures()
                .stream()
                .mapToDouble(MedicalProcedure::getCost)
                .sum();

        totalCost += proceduresCost;

        // sdd expertise fee if exists
        if (consultation.getExpertiseRequest() != null &&
            consultation.getExpertiseRequest().getSpecialist() != null) {
            totalCost += consultation.getExpertiseRequest().getSpecialist().getExpertiseFee();
        }

        return totalCost;
    }

    @Override
    public ConsultationDTO addMedicalProcedure(Long consultationId, Long medicalProcedureId) {
        if (consultationId == null || medicalProcedureId == null) {
            throw new IllegalArgumentException("Consultation ID and Medical Procedure ID are required");
        }

        Optional<Consultation> consultationOpt = consultationDAO.findById(consultationId);
        if (!consultationOpt.isPresent()) {
            throw new IllegalArgumentException("Consultation not found");
        }

        Optional<MedicalProcedure> procedureOpt = medicalProcedureDAO.findById(medicalProcedureId);
        if (!procedureOpt.isPresent()) {
            throw new IllegalArgumentException("Medical procedure not found");
        }

        Consultation consultation = consultationOpt.get();
        MedicalProcedure procedure = procedureOpt.get();

        consultation.addMedicalProcedure(procedure);
        Consultation updatedConsultation = consultationDAO.update(consultation);

        return DTOMapper.toDTO(updatedConsultation);
    }

    @Override
    public ConsultationDTO completeConsultation(Long consultationId) {
        if (consultationId == null) {
            throw new IllegalArgumentException("Consultation ID is required");
        }

        Optional<Consultation> consultationOpt = consultationDAO.findById(consultationId);
        if (!consultationOpt.isPresent()) {
            throw new IllegalArgumentException("Consultation not found");
        }

        Consultation consultation = consultationOpt.get();
        consultation.completeConsultation();
        Consultation updatedConsultation = consultationDAO.update(consultation);

        return DTOMapper.toDTO(updatedConsultation);
    }

    @Override
    public ConsultationDTO requestExpertise(Long consultationId) {
        if (consultationId == null) {
            throw new IllegalArgumentException("Consultation ID is required");
        }

        Optional<Consultation> consultationOpt = consultationDAO.findById(consultationId);
        if (!consultationOpt.isPresent()) {
            throw new IllegalArgumentException("Consultation not found");
        }

        Consultation consultation = consultationOpt.get();
        consultation.setStatus(ConsultationStatus.AWAITING_SPECIALIST_ADVICE);
        Consultation updatedConsultation = consultationDAO.update(consultation);

        return DTOMapper.toDTO(updatedConsultation);
    }

    @Override
    public Optional<ConsultationDTO> findById(Long consultationId) {
        if (consultationId == null) {
            return Optional.empty();
        }

        Optional<Consultation> consultationOpt = consultationDAO.findById(consultationId);
        return consultationOpt.map(DTOMapper::toDTO);
    }

    @Override
    public List<ConsultationDTO> findByGeneralPractitioner(Long generalPractitionerId) {
        if (generalPractitionerId == null) {
            throw new IllegalArgumentException("General practitioner ID is required");
        }

        return consultationDAO.findByGeneralPractitionerId(generalPractitionerId)
                .stream()
                .map(DTOMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ConsultationDTO> findByPatient(Long patientId) {
        if (patientId == null) {
            throw new IllegalArgumentException("Patient ID is required");
        }

        return consultationDAO.findByPatientId(patientId)
                .stream()
                .map(DTOMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ConsultationDTO> findByStatus(ConsultationStatus status) {
        if (status == null) {
            throw new IllegalArgumentException("Status is required");
        }

        return consultationDAO.findByStatus(status)
                .stream()
                .map(DTOMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ConsultationDTO> findByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        if (startDate == null || endDate == null) {
            throw new IllegalArgumentException("Start date and end date are required");
        }

        return consultationDAO.findByDateRange(startDate, endDate)
                .stream()
                .map(DTOMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public long count(){
        return consultationDAO.count();
    }
}