package com.tele.service.impl;

import com.tele.dao.ExpertiseRequestDAO;
import com.tele.dao.impl.ExpertiseRequestDAOImpl;
import com.tele.dto.ExpertiseRequestDTO;
import com.tele.model.enums.ExpertiseStatus;
import com.tele.model.enums.Priority;
import com.tele.service.ExpertiseRequestService;
import com.tele.util.DTOMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ExpertiseRequestServiceImpl implements ExpertiseRequestService {

    private final ExpertiseRequestDAO expertiseRequestDAO;

    public ExpertiseRequestServiceImpl() {
        this.expertiseRequestDAO = new ExpertiseRequestDAOImpl();
    }

    public ExpertiseRequestServiceImpl(ExpertiseRequestDAO expertiseRequestDAO) {
        this.expertiseRequestDAO = expertiseRequestDAO;
    }

    @Override
    public ExpertiseRequestDTO createExpertiseRequest(Long consultationId, Long requestingDoctorId,
                                                     Long specialistId, String question, String medicalData,
                                                     Priority priority) {
        return null;
    }

    @Override
    public ExpertiseRequestDTO respondToRequest(Long requestId, String advice, String recommendations) {
        return expertiseRequestDAO.findById(requestId)
                .map(request -> {
                    request.respond(advice, recommendations);
                    return expertiseRequestDAO.update(request);
                })
                .map(DTOMapper::toDTO)
                .orElse(null);
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
}