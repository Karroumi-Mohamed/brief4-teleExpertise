package com.tele.service;

import com.tele.dto.TimeSlotDTO;
import com.tele.model.enums.DaySlot;
import java.time.LocalDate;
import java.util.List;

public interface TimeSlotService {

    List<TimeSlotDTO> getAvailableSlots(Long specialistId, LocalDate date);

    List<TimeSlotDTO> getAvailableSlotsByDateRange(Long specialistId, LocalDate startDate, LocalDate endDate);

    boolean isSlotAvailable(Long specialistId, LocalDate date, DaySlot daySlot);

    TimeSlotDTO bookSlot(Long specialistId, LocalDate date, DaySlot daySlot, Long consultationId);

    void releaseSlot(Long specialistId, LocalDate date, DaySlot daySlot);

    List<TimeSlotDTO> getSpecialistSchedule(Long specialistId, LocalDate date);


    List<TimeSlotDTO> generateVirtualSlots(Long specialistId, LocalDate date);
}