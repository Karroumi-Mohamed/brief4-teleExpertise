package com.tele.dao.impl;

import jakarta.persistence.EntityManager;
import com.tele.dao.SpecialistDAO;
import com.tele.model.Specialist;
import com.tele.model.enums.Specialty;

import java.util.List;

public class SpecialistDAOImpl extends GenericDAOImpl<Specialist, Long> implements SpecialistDAO {
    
    public SpecialistDAOImpl() {
        super(Specialist.class);
    }
    
    @Override
    public List<Specialist> findBySpecialty(Specialty specialty) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery(
                "SELECT s FROM Specialist s WHERE s.specialty = :specialty", 
                Specialist.class)
                .setParameter("specialty", specialty)
                .getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error finding specialists by specialty", e);
        }
    }
    
    @Override
    public List<Specialist> findBySpecialtyOrderByFee(Specialty specialty) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery(
                "SELECT s FROM Specialist s WHERE s.specialty = :specialty ORDER BY s.expertiseFee ASC", 
                Specialist.class)
                .setParameter("specialty", specialty)
                .getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error finding specialists by specialty ordered by fee", e);
        }
    }
}
