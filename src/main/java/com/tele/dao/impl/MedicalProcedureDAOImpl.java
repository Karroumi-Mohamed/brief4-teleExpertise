package com.tele.dao.impl;

import jakarta.persistence.EntityManager;
import com.tele.dao.MedicalProcedureDAO;
import com.tele.model.MedicalProcedure;
import com.tele.model.enums.MedicalProcedureType;

import java.util.List;

public class MedicalProcedureDAOImpl extends GenericDAOImpl<MedicalProcedure, Long> implements MedicalProcedureDAO {

    public MedicalProcedureDAOImpl() {
        super(MedicalProcedure.class);
    }

    @Override
    public List<MedicalProcedure> findByType(MedicalProcedureType type) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery(
                "SELECT m FROM MedicalProcedure m WHERE m.type = :type",
                MedicalProcedure.class)
                .setParameter("type", type)
                .getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error finding medical procedures by type", e);
        }
    }
}
