package com.tele.dao;

import com.tele.model.MedicalProcedure;
import com.tele.model.enums.MedicalProcedureType;

import java.util.List;

public interface MedicalProcedureDAO extends GenericDAO<MedicalProcedure, Long> {

    List<MedicalProcedure> findByType(MedicalProcedureType type);
}
