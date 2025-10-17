package com.tele.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class VitalSigns {

    @Column(name = "blood_pressure", length = 7)
    private String bloodPressure;// bhal 120/80

    @Column(name = "heart_rate")
    private Integer heartRate;

    @Column(name = "temperature")
    private Double temperature;

    @Column(name = "respiratory_rate")
    private Integer respiratoryRate;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "height")
    private Double height;

    @Column(name = "recorded_at", nullable = false)
    private LocalDateTime recordedAt;

    public VitalSigns() {
        this.recordedAt = LocalDateTime.now();
    }

    public VitalSigns(String bloodPressure, Integer heartRate, Double temperature, Integer respiratoryRate,
            Double weight, Double height) {
        this.bloodPressure = bloodPressure;
        this.heartRate = heartRate;
        this.temperature = temperature;
        this.respiratoryRate = respiratoryRate;
        this.weight = weight;
        this.height = height;
        this.recordedAt = LocalDateTime.now();
    }

    public String getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(String bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public Integer getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(Integer heartRate) {
        this.heartRate = heartRate;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Integer getRespiratoryRate() {
        return respiratoryRate;
    }

    public void setRespiratoryRate(Integer respiratoryRate) {
        this.respiratoryRate = respiratoryRate;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public LocalDateTime getRecordedAt() {
        return recordedAt;
    }

    public void setRecordedAt(LocalDateTime recordedAt) {
        this.recordedAt = recordedAt;
    }

}
