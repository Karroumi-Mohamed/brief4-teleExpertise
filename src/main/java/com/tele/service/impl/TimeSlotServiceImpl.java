package com.tele.service.impl;

import com.tele.dao.TimeSlotDAO;
import com.tele.dao.SpecialistDAO;
import com.tele.dto.TimeSlotDTO;
import com.tele.model.Specialist;
import com.tele.model.TimeSlot;
import com.tele.model.enums.DaySlot;
import com.tele.service.TimeSlotService;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TimeSlotServiceImpl implements TimeSlotService {

    private final TimeSlotDAO timeSlotDAO;
    private final SpecialistDAO specialistDAO;

    public TimeSlotServiceImpl(TimeSlotDAO timeSlotDAO, SpecialistDAO specialistDAO) {
        this.timeSlotDAO = timeSlotDAO;
        this.specialistDAO = specialistDAO;
    }

    @Override
    public List<TimeSlotDTO> getAvailableSlots(Long specialistId, LocalDate date) {
        List<DaySlot> allSlots = Arrays.asList(DaySlot.values());

        List<TimeSlot> bookedSlots = timeSlotDAO.findBySpecialistIdAndDate(specialistId, date);

        Optional<Specialist> specialistOpt = specialistDAO.findById(specialistId);
        String specialistName = specialistOpt.map(s -> s.getFname() + " " + s.getLname()).orElse("Unknown");

        return allSlots.stream()
                .filter(slot -> !isSlotBooked(bookedSlots, slot))
                .map(slot -> new TimeSlotDTO(slot, date, specialistId, specialistName))
                .collect(Collectors.toList());
    }

    @Override
    public List<TimeSlotDTO> getAvailableSlotsByDateRange(Long specialistId, LocalDate startDate, LocalDate endDate) {
        List<TimeSlotDTO> allSlots = new ArrayList<>();
        LocalDate currentDate = startDate;

        while (!currentDate.isAfter(endDate)) {
            allSlots.addAll(getAvailableSlots(specialistId, currentDate));
            currentDate = currentDate.plusDays(1);
        }

        return allSlots;
    }

    @Override
    public boolean isSlotAvailable(Long specialistId, LocalDate date, DaySlot daySlot) {
        return !timeSlotDAO.isSlotBooked(specialistId, date, daySlot);
    }

    @Override
    public TimeSlotDTO bookSlot(Long specialistId, LocalDate date, DaySlot daySlot, Long consultationId) {
        if (!isSlotAvailable(specialistId, date, daySlot)) {
            throw new IllegalStateException("Time slot is not available");
        }

        Optional<Specialist> specialistOpt = specialistDAO.findById(specialistId);
        if (specialistOpt.isEmpty()) {
            throw new IllegalArgumentException("Specialist not found");
        }

        TimeSlot timeSlot = new TimeSlot(daySlot, date);
        timeSlot.setSpecialist(specialistOpt.get());

        TimeSlot savedSlot = timeSlotDAO.save(timeSlot);

        return new TimeSlotDTO(
                savedSlot.getId(),
                savedSlot.getDaySlot(),
                savedSlot.getDate(),
                specialistId,
                specialistOpt.get().getFname() + " " + specialistOpt.get().getLname(),
                true,
                consultationId
        );
    }

    @Override
    public void releaseSlot(Long specialistId, LocalDate date, DaySlot daySlot) {
        Optional<TimeSlot> slotOpt = timeSlotDAO.findBySpecialistIdAndDateAndDaySlot(specialistId, date, daySlot);
        slotOpt.ifPresent(timeSlotDAO::delete);
    }

    @Override
    public List<TimeSlotDTO> getSpecialistSchedule(Long specialistId, LocalDate date) {
        // Get all possible day slots
        List<DaySlot> allSlots = Arrays.asList(DaySlot.values());

        // Get booked slots for this specialist + date
        List<TimeSlot> bookedSlots = timeSlotDAO.findBySpecialistIdAndDate(specialistId, date);

        // Get specialist name
        Optional<Specialist> specialistOpt = specialistDAO.findById(specialistId);
        String specialistName = specialistOpt.map(s -> s.getFname() + " " + s.getLname()).orElse("Unknown");

        // Create DTOs for all slots (both available and booked)
        return allSlots.stream()
                .map(slot -> {
                    Optional<TimeSlot> booked = bookedSlots.stream()
                            .filter(b -> b.getDaySlot().equals(slot))
                            .findFirst();

                    if (booked.isPresent()) {
                        TimeSlot bookedSlot = booked.get();
                        return new TimeSlotDTO(
                                bookedSlot.getId(),
                                slot,
                                date,
                                specialistId,
                                specialistName,
                                true,
                                bookedSlot.getConsultation() != null ? bookedSlot.getConsultation().getId() : null
                        );
                    } else {
                        return new TimeSlotDTO(slot, date, specialistId, specialistName);
                    }
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<TimeSlotDTO> generateVirtualSlots(Long specialistId, LocalDate date) {
        List<DaySlot> allSlots = Arrays.asList(DaySlot.values());

        Optional<Specialist> specialistOpt = specialistDAO.findById(specialistId);
        String specialistName = specialistOpt.map(s -> s.getFname() + " " + s.getLname()).orElse("Unknown");

        return allSlots.stream()
                .map(slot -> new TimeSlotDTO(slot, date, specialistId, specialistName))
                .collect(Collectors.toList());
    }

    private boolean isSlotBooked(List<TimeSlot> bookedSlots, DaySlot daySlot) {
        return bookedSlots.stream()
                .anyMatch(slot -> slot.getDaySlot().equals(daySlot));
    }
}