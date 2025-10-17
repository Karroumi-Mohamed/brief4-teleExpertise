package com.tele.util;

import com.tele.dto.*;
import com.tele.model.*;

public class DTOMapper {

    public static PatientDTO toDTO(Patient patient) {
        if (patient == null)
            return null;

        VitalSignsDTO vitalSignsDTO = patient.getVitalSigns() != null
                ? toDTO(patient.getVitalSigns())
                : null;

        return new PatientDTO(
                patient.getId(),
                patient.getFname(),
                patient.getLname(),
                patient.getBirthDate(),
                patient.getSocialSecurityNumber(),
                patient.getPhone(),
                patient.getAddress(),
                patient.getCreatedAt(),
                vitalSignsDTO);
    }

    public static VitalSignsDTO toDTO(VitalSigns vitalSigns) {
        if (vitalSigns == null)
            return null;

        return new VitalSignsDTO(
                vitalSigns.getBloodPressure(),
                vitalSigns.getHeartRate(),
                vitalSigns.getTemperature(),
                vitalSigns.getRespiratoryRate(),
                vitalSigns.getWeight(),
                vitalSigns.getHeight(),
                vitalSigns.getRecordedAt());
    }

    public static VitalSigns toEntity(VitalSignsDTO dto) {
        if (dto == null)
            return null;

        VitalSigns vitalSigns = new VitalSigns(
                dto.getBloodPressure(),
                dto.getHeartRate(),
                dto.getTemperature(),
                dto.getRespiratoryRate(),
                dto.getWeight(),
                dto.getHeight());
        vitalSigns.setRecordedAt(dto.getRecordedAt());
        return vitalSigns;
    }

    public static UserDTO toDTO(User user) {
        if (user == null)
            return null;

        return new UserDTO(
                user.getId(),
                user.getEmail(),
                user.getFname(),
                user.getLname(),
                user.getPhone(),
                user.getRole());
    }

    public static SpecialistDTO toDTO(Specialist specialist) {
        if (specialist == null)
            return null;

        return new SpecialistDTO(
                specialist.getId(),
                specialist.getEmail(),
                specialist.getFname(),
                specialist.getLname(),
                specialist.getPhone(),
                specialist.getSpecialty(),
                specialist.getExpertiseFee(),
                specialist.getAvgConsultationDuration());
    }

    public static ConsultationDTO toDTO(Consultation consultation) {
        if (consultation == null)
            return null;

        ConsultationDTO dto = new ConsultationDTO(
                consultation.getId(),
                consultation.getReason(),
                consultation.getObservations(),
                consultation.getSymptoms(),
                consultation.getDiagnosis(),
                consultation.getTreatment(),
                consultation.getConsultationDate(),
                consultation.getStatus(),
                consultation.getCost(),
                consultation.getPatient() != null ? consultation.getPatient().getId() : null,
                consultation.getPatient() != null
                        ? consultation.getPatient().getFname() + " " + consultation.getPatient().getLname()
                        : null,
                consultation.getGeneralPractitioner() != null ? consultation.getGeneralPractitioner().getId() : null,
                consultation.getGeneralPractitioner() != null ? consultation.getGeneralPractitioner().getFname() + " "
                        + consultation.getGeneralPractitioner().getLname() : null);

        dto.setTotalCost(consultation.calculateTotalCost());

        return dto;
    }

    public static ExpertiseRequestDTO toDTO(ExpertiseRequest expertiseRequest) {
        if (expertiseRequest == null)
            return null;

        return new ExpertiseRequestDTO(
                expertiseRequest.getId(),
                expertiseRequest.getQuestion(),
                expertiseRequest.getMedicalData(),
                expertiseRequest.getPriority(),
                expertiseRequest.getAdvice(),
                expertiseRequest.getRecommendations(),
                expertiseRequest.getStatus(),
                expertiseRequest.getCreatedAt(),
                expertiseRequest.getRespondedAt(),
                expertiseRequest.getConsultation() != null ? expertiseRequest.getConsultation().getId() : null,
                expertiseRequest.getRequestingDoctor() != null ? expertiseRequest.getRequestingDoctor().getId() : null,
                expertiseRequest.getRequestingDoctor() != null ? expertiseRequest.getRequestingDoctor().getFname() + " "
                        + expertiseRequest.getRequestingDoctor().getLname() : null,
                expertiseRequest.getSpecialist() != null ? expertiseRequest.getSpecialist().getId() : null,
                expertiseRequest.getSpecialist() != null ? expertiseRequest.getSpecialist().getFname() + " "
                        + expertiseRequest.getSpecialist().getLname() : null,
                expertiseRequest.getSpecialist() != null ? expertiseRequest.getSpecialist().getSpecialty().toString()
                        : null);
    }
}
