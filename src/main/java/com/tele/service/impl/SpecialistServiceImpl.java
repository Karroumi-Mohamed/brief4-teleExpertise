package com.tele.service.impl;

import com.tele.dao.SpecialistDAO;
import com.tele.dao.impl.SpecialistDAOImpl;
import com.tele.dto.SpecialistDTO;
import com.tele.model.Specialist;
import com.tele.model.enums.Specialty;
import com.tele.service.SpecialistService;
import com.tele.util.DTOMapper;

import java.util.List;
import java.util.Optional;
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
    public SpecialistDTO createSpecialist(String firstName,
            String lastName,
            Specialty specialty,
            Double expertiseFee,
            String phone,
            String email,
            String passwordHash,
            Integer avgDuration) {
        Specialist specialist = new Specialist(email, passwordHash, firstName, lastName, specialty, expertiseFee);
        specialist.setPhone(phone);
        specialist.setAvgConsultationDuration(avgDuration);
        Specialist savedSpecialist = specialistDAO.save(specialist);
        return DTOMapper.toDTO(savedSpecialist);
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

    @Override
    public java.util.Optional<SpecialistDTO> findById(Long id) {
        return specialistDAO.findById(id).map(DTOMapper::toDTO);
    }

    @Override
    public void deleteSpecialist(Long id) {
        Optional<Specialist> specialistOpt = specialistDAO.findById(id);
        if (specialistOpt.isEmpty()) {
            return;
        }
        specialistDAO.delete(specialistOpt.get());
    }

    @Override
    public void updateSpecialist(Long id, String fname, String lname, Specialty specialty,
                                 Double expertiseFee, String phone, String email, Integer avgDuration) {
        java.util.Optional<Specialist> specialistOpt = specialistDAO.findById(id);
        if (specialistOpt.isPresent()) {
            Specialist specialist = specialistOpt.get();
            specialist.setFname(fname);
            specialist.setLname(lname);
            specialist.setSpecialty(specialty);
            specialist.setExpertiseFee(expertiseFee);
            specialist.setPhone(phone);
            specialist.setEmail(email);
            specialist.setAvgConsultationDuration(avgDuration);
            specialistDAO.update(specialist);
        }
    }
}
