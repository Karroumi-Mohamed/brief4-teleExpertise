package com.tele.dao;

import com.tele.model.Consultation;
import com.tele.model.enums.ConsultationStatus;

import java.time.LocalDateTime;
import java.util.List;

public interface ConsultationDAO extends GenericDAO<Consultation, Long> {
    
    List<Consultation> findByPatientId(Long patientId);
    
    List<Consultation> findByGeneralPractitionerId(Long gpId);
    
    List<Consultation> findByStatus(ConsultationStatus status);
    
    List<Consultation> findByDateRange(LocalDateTime startDate, LocalDateTime endDate);
}
