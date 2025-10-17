package com.tele.service;

import com.tele.dto.ExpertiseRequestDTO;
import com.tele.model.enums.ExpertiseStatus;
import com.tele.model.enums.Priority;
import com.tele.model.enums.Specialty;

import java.util.List;
import java.util.Optional;

public interface ExpertiseRequestService {

    ExpertiseRequestDTO createExpertiseRequest(Long consultationId, Long requestingDoctorId,
                                              Long specialistId, String question, String medicalData,
                                              Priority priority);

    ExpertiseRequestDTO respondToRequest(Long requestId, String advice, String recommendations);

    List<ExpertiseRequestDTO> findBySpecialistAndStatus(Long specialistId, ExpertiseStatus status);

    List<ExpertiseRequestDTO> findBySpecialistFilteredByPriority(Long specialistId, Priority priority);

    List<ExpertiseRequestDTO> findBySpecialist(Long specialistId);

    Optional<ExpertiseRequestDTO> findById(Long requestId);
}