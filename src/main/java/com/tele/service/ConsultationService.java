package com.tele.service;

import com.tele.dto.ConsultationDTO;
import com.tele.model.enums.ConsultationStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ConsultationService {

    ConsultationDTO createConsultation(Long patientId, Long generalPractitionerId, String reason);

    ConsultationDTO updateConsultation(Long consultationId, String observations,
                                       String symptoms, String diagnosis, String treatment);

    Double calculateTotalCost(Long consultationId);

    ConsultationDTO addMedicalProcedure(Long consultationId, Long medicalProcedureId);

    ConsultationDTO completeConsultation(Long consultationId);

    ConsultationDTO requestExpertise(Long consultationId);

    Optional<ConsultationDTO> findById(Long consultationId);

    List<ConsultationDTO> findByGeneralPractitioner(Long generalPractitionerId);

    List<ConsultationDTO> findByPatient(Long patientId);

    List<ConsultationDTO> findByStatus(ConsultationStatus status);


    List<ConsultationDTO> findByDateRange(LocalDateTime startDate, LocalDateTime endDate);

    long count();
}