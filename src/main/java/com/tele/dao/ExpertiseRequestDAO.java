package com.tele.dao;

import com.tele.model.ExpertiseRequest;
import com.tele.model.enums.ExpertiseStatus;
import com.tele.model.enums.Priority;

import java.util.List;

public interface ExpertiseRequestDAO extends GenericDAO<ExpertiseRequest, Long> {

    List<ExpertiseRequest> findBySpecialistId(Long specialistId);

    List<ExpertiseRequest> findByStatus(ExpertiseStatus status);

    List<ExpertiseRequest> findByPriority(Priority priority);

    List<ExpertiseRequest> findBySpecialistIdAndStatus(Long specialistId, ExpertiseStatus status);

    List<ExpertiseRequest> findByRequestingDoctorId(Long doctorId);
}
