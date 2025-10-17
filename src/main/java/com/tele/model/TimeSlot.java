package com.tele.model;

import java.time.LocalDate;

import jakarta.persistence.*;

import com.tele.model.enums.DaySlot;

@Entity
@Table(name = "time_slots")
public class TimeSlot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "day_slot", nullable = false)
    private DaySlot daySlot;
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "specialist_id")
    private Specialist specialist;

    @OneToOne(mappedBy = "timeSlot", fetch = FetchType.LAZY)
    private Consultation consultation;

    public TimeSlot() {
    }

    public TimeSlot(DaySlot daySlot, LocalDate date) {
        this.daySlot = daySlot;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public DaySlot getDaySlot() {
        return daySlot;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDaySlot(DaySlot daySlot) {
        this.daySlot = daySlot;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Specialist getSpecialist() {
        return specialist;
    }

    public void setSpecialist(Specialist specialist) {
        this.specialist = specialist;
    }

    public Consultation getConsultation() {
        return consultation;
    }

    public void setConsultation(Consultation consultation) {
        this.consultation = consultation;
    }

    public boolean isAvailable() {
        return consultation == null;
    }
}
