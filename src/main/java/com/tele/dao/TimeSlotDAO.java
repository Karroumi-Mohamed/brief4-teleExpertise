package com.tele.dao;

import com.tele.model.TimeSlot;
import com.tele.model.enums.DaySlot;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TimeSlotDAO extends GenericDAO<TimeSlot, Long> {

    List<TimeSlot> findBySpecialistIdAndDate(Long specialistId, LocalDate date);

    List<TimeSlot> findBySpecialistIdAndDateBetween(Long specialistId, LocalDate startDate, LocalDate endDate);

    Optional<TimeSlot> findBySpecialistIdAndDateAndDaySlot(Long specialistId, LocalDate date, DaySlot daySlot);

    List<TimeSlot> findAvailableSlotsBySpecialistAndDate(Long specialistId, LocalDate date);

    List<TimeSlot> findBookedSlotsBySpecialistAndDate(Long specialistId, LocalDate date);

    boolean isSlotBooked(Long specialistId, LocalDate date, DaySlot daySlot);

    List<TimeSlot> findByConsultationId(Long consultationId);

    void deleteBySpecialistIdAndDate(Long specialistId, LocalDate date);
}