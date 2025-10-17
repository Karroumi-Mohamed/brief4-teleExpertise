package com.tele.dao;

import com.tele.model.Specialist;
import com.tele.model.enums.Specialty;

import java.util.List;

public interface SpecialistDAO extends GenericDAO<Specialist, Long> {

    List<Specialist> findBySpecialty(Specialty specialty);

    List<Specialist> findBySpecialtyOrderByFee(Specialty specialty);
}
