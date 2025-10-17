package com.tele.service.impl;

import com.tele.dao.SpecialistDAO;
import com.tele.dao.impl.SpecialistDAOImpl;
import com.tele.dto.SpecialistDTO;
import com.tele.model.enums.Specialty;
import com.tele.service.SpecialistService;
import com.tele.util.DTOMapper;

import java.util.List;
import java.util.stream.Collectors;

public class SpecialistServiceImpl implements SpecialistService {

    private final SpecialistDAO specialistDAO;

    public SpecialistServiceImpl() {
        this.specialistDAO = new SpecialistDAOImpl();
    }

    public SpecialistServiceImpl(SpecialistDAO specialistDAO) {
        this.specialistDAO = specialistDAO;
    }

    @Override
    public List<SpecialistDTO> findBySpecialtyOrderByFee(Specialty specialty) {
        return specialistDAO.findBySpecialtyOrderByFee(specialty)
                .stream()
                .map(DTOMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<SpecialistDTO> findAvailableSpecialists() {
        return specialistDAO.findAll()
                .stream()
                .filter(specialist -> specialist.getExpertiseFee() != null)
                .map(DTOMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<SpecialistDTO> findAll() {
        return specialistDAO.findAll()
                .stream()
                .map(DTOMapper::toDTO)
                .collect(Collectors.toList());
    }
}