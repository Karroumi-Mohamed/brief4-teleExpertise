package com.tele.model;

import jakarta.persistence.*;
import com.tele.model.enums.MedicalProcedureType;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "medical_procedures")
public class MedicalProcedure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, length = 50)
    private MedicalProcedureType type;

    @Column(name = "cost", nullable = false)
    private Double cost;


    @ManyToMany(mappedBy = "medicalProcedures", fetch = FetchType.LAZY)
    private List<Consultation> consultations = new ArrayList<>();


    public MedicalProcedure() {
    }

    public MedicalProcedure(MedicalProcedureType type, Double cost) {
        this.type = type;
        this.cost = cost;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MedicalProcedureType getType() {
        return type;
    }

    public void setType(MedicalProcedureType type) {
        this.type = type;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public List<Consultation> getConsultations() {
        return consultations;
    }

    public void setConsultations(List<Consultation> consultations) {
        this.consultations = consultations;
    }
}
