package com.tele.service.impl;

import com.tele.dao.GeneralPractitionerDAO;
import com.tele.dao.impl.GeneralPractitionerDAOImpl;
import com.tele.dto.GeneralPractitionerDTO;
import com.tele.model.GeneralPractitioner;
import com.tele.service.GeneralPractitionerService;
import com.tele.util.DTOMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class GeneralPractitionerServiceImpl implements GeneralPractitionerService {

    private final GeneralPractitionerDAO generalPractitionerDAO;

    public GeneralPractitionerServiceImpl() {
        this.generalPractitionerDAO = new GeneralPractitionerDAOImpl();
    }

    public GeneralPractitionerServiceImpl(GeneralPractitionerDAO generalPractitionerDAO) {
        this.generalPractitionerDAO = generalPractitionerDAO;
    }

    @Override
    public GeneralPractitionerDTO createGeneralPractitioner(String fname, String lname, String phone, String email, String passwordHash) {
        GeneralPractitioner gp = new GeneralPractitioner(email, passwordHash, fname, lname, phone);
        GeneralPractitioner savedGP = generalPractitionerDAO.save(gp);
        return DTOMapper.toDTO(savedGP);
    }

    @Override
    public void deleteGeneralPractitioner(Long id) {
        Optional<GeneralPractitioner> gpOpt = generalPractitionerDAO.findById(id);
        if (gpOpt.isEmpty()) {
            return;
        }
        generalPractitionerDAO.delete(gpOpt.get());
    }

    @Override
    public void updateGeneralPractitioner(Long id, String fname, String lname, String phone, String email) {
        Optional<GeneralPractitioner> gpOpt = generalPractitionerDAO.findById(id);
        if (gpOpt.isPresent()) {
            GeneralPractitioner gp = gpOpt.get();
            gp.setFname(fname);
            gp.setLname(lname);
            gp.setPhone(phone);
            gp.setEmail(email);
            generalPractitionerDAO.update(gp);
        }
    }

    @Override
    public Optional<GeneralPractitionerDTO> findById(Long id) {
        return generalPractitionerDAO.findById(id).map(DTOMapper::toDTO);
    }

    @Override
    public List<GeneralPractitionerDTO> findAll() {
        return generalPractitionerDAO.findAll()
                .stream()
                .map(DTOMapper::toDTO)
                .collect(Collectors.toList());
    }
}
