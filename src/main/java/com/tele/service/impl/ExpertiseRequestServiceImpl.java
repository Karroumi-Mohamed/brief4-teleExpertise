package com.tele.service.impl;

import com.tele.dao.ConsultationDAO;
import com.tele.dao.ExpertiseRequestDAO;
import com.tele.dao.UserDAO;
import com.tele.dao.impl.ConsultationDAOImpl;
import com.tele.dao.impl.ExpertiseRequestDAOImpl;
import com.tele.dao.impl.UserDAOImpl;
import com.tele.dto.ExpertiseRequestDTO;
import com.tele.model.*;
import com.tele.model.enums.ExpertiseStatus;
import com.tele.model.enums.Priority;
import com.tele.service.ExpertiseRequestService;
import com.tele.util.DTOMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ExpertiseRequestServiceImpl implements ExpertiseRequestService {

    private final ExpertiseRequestDAO expertiseRequestDAO;
    private final ConsultationDAO consultationDAO;
    private final UserDAO userDAO;

    public ExpertiseRequestServiceImpl() {
        this.expertiseRequestDAO = new ExpertiseRequestDAOImpl();
        this.consultationDAO = new ConsultationDAOImpl();
        this.userDAO = new UserDAOImpl();
    }

    public ExpertiseRequestServiceImpl(ExpertiseRequestDAO expertiseRequestDAO,
                                      ConsultationDAO consultationDAO,
                                      UserDAO userDAO) {
        this.expertiseRequestDAO = expertiseRequestDAO;
        this.consultationDAO = consultationDAO;
        this.userDAO = userDAO;
    }

    @Override
    public ExpertiseRequestDTO createExpertiseRequest(Long consultationId, Long requestingDoctorId,
                                                     Long specialistId, String question, String medicalData,
                                                     Priority priority) {
        if (consultationId == null || requestingDoctorId == null || specialistId == null ||
            question == null || question.trim().isEmpty() || priority == null) {
            throw new IllegalArgumentException("Required fields cannot be null or empty");
        }

        // Get consultation
        Optional<Consultation> consultationOpt = consultationDAO.findById(consultationId);
        if (consultationOpt.isEmpty()) {
            throw new IllegalArgumentException("Consultation not found with ID: " + consultationId);
        }

        // Get requesting doctor (GP)
        Optional<User> gpOpt = userDAO.findById(requestingDoctorId);
        if (gpOpt.isEmpty() || !(gpOpt.get() instanceof GeneralPractitioner)) {
            throw new IllegalArgumentException("General practitioner not found with ID: " + requestingDoctorId);
        }

        // Get specialist
        Optional<User> specialistOpt = userDAO.findById(specialistId);
        if (specialistOpt.isEmpty() || !(specialistOpt.get() instanceof Specialist)) {
            throw new IllegalArgumentException("Specialist not found with ID: " + specialistId);
        }

        Consultation consultation = consultationOpt.get();
        GeneralPractitioner gp = (GeneralPractitioner) gpOpt.get();
        Specialist specialist = (Specialist) specialistOpt.get();

        // Create expertise request
        ExpertiseRequest expertiseRequest = new ExpertiseRequest(
            question.trim(),
            medicalData != null ? medicalData.trim() : null,
            priority,
            consultation,
            gp,
            specialist
        );

        // Save to database
        ExpertiseRequest savedRequest = expertiseRequestDAO.save(expertiseRequest);

        return DTOMapper.toDTO(savedRequest);
    }

    @Override
    public ExpertiseRequestDTO respondToRequest(Long requestId, String advice, String recommendations) {
        Optional<ExpertiseRequest> requestOpt = expertiseRequestDAO.findById(requestId);
        if (requestOpt.isEmpty()) {
            return null;
        }

        ExpertiseRequest request = requestOpt.get();
        request.respond(advice, recommendations);
        expertiseRequestDAO.update(request);

        // Reload to avoid lazy initialization exception
        Optional<ExpertiseRequest> reloadedOpt = expertiseRequestDAO.findById(requestId);
        return reloadedOpt.map(DTOMapper::toDTO).orElse(null);
    }

    @Override
    public List<ExpertiseRequestDTO> findBySpecialistAndStatus(Long specialistId, ExpertiseStatus status) {
        return expertiseRequestDAO.findBySpecialistIdAndStatus(specialistId, status)
                .stream()
                .map(DTOMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ExpertiseRequestDTO> findBySpecialistFilteredByPriority(Long specialistId, Priority priority) {
        return expertiseRequestDAO.findBySpecialistId(specialistId)
                .stream()
                .filter(request -> request.getPriority() == priority)
                .map(DTOMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ExpertiseRequestDTO> findBySpecialist(Long specialistId) {
        return expertiseRequestDAO.findBySpecialistId(specialistId)
                .stream()
                .map(DTOMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ExpertiseRequestDTO> findById(Long requestId) {
        return expertiseRequestDAO.findById(requestId)
                .map(DTOMapper::toDTO);
    }

    @Override
    public List<ExpertiseRequestDTO> findByGP(Long gpId) {
        return expertiseRequestDAO.findByGPId(gpId)
                .stream()
                .map(DTOMapper::toDTO)
                .collect(Collectors.toList());
    }
}