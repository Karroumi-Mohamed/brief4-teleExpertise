package com.tele.dto;

import java.time.LocalDate;
import com.tele.model.enums.DaySlot;

public class TimeSlotDTO {

    private Long id;
    private DaySlot daySlot;
    private LocalDate date;
    private Long specialistId;
    private String specialistName;
    private boolean isAvailable;
    private boolean isBooked;
    private Long consultationId;

    public TimeSlotDTO() {
    }

    public TimeSlotDTO(DaySlot daySlot, LocalDate date, Long specialistId, String specialistName) {
        this.daySlot = daySlot;
        this.date = date;
        this.specialistId = specialistId;
        this.specialistName = specialistName;
        this.isAvailable = true;
        this.isBooked = false;
    }

    public TimeSlotDTO(Long id, DaySlot daySlot, LocalDate date, Long specialistId, String specialistName, boolean isBooked, Long consultationId) {
        this.id = id;
        this.daySlot = daySlot;
        this.date = date;
        this.specialistId = specialistId;
        this.specialistName = specialistName;
        this.isAvailable = !isBooked;
        this.isBooked = isBooked;
        this.consultationId = consultationId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DaySlot getDaySlot() {
        return daySlot;
    }

    public void setDaySlot(DaySlot daySlot) {
        this.daySlot = daySlot;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getSpecialistId() {
        return specialistId;
    }

    public void setSpecialistId(Long specialistId) {
        this.specialistId = specialistId;
    }

    public String getSpecialistName() {
        return specialistName;
    }

    public void setSpecialistName(String specialistName) {
        this.specialistName = specialistName;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
        this.isAvailable = !booked;
    }

    public Long getConsultationId() {
        return consultationId;
    }

    public void setConsultationId(Long consultationId) {
        this.consultationId = consultationId;
    }

    public String getDisplayTime() {
        if (daySlot == null) return "";

        switch (daySlot) {
            case S09H00_S09H30: return "09:00 - 09:30";
            case S09H30_S10H00: return "09:30 - 10:00";
            case S10H00_S10H30: return "10:00 - 10:30";
            case S10H30_S11H00: return "10:30 - 11:00";
            case S11H00_S11H30: return "11:00 - 11:30";
            case S11H30_S12H00: return "11:30 - 12:00";
            default: return daySlot.name();
        }
    }
}