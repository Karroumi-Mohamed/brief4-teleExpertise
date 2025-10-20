package com.tele.service.impl;

import com.tele.dao.NurseDAO;
import com.tele.dao.impl.NurseDAOImpl;
import com.tele.dto.NurseDTO;
import com.tele.model.Nurse;
import com.tele.service.NurseService;
import com.tele.util.DTOMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class NurseServiceImpl implements NurseService {

    private final NurseDAO nurseDAO;

    public NurseServiceImpl() {
        this.nurseDAO = new NurseDAOImpl();
    }

    public NurseServiceImpl(NurseDAO nurseDAO) {
        this.nurseDAO = nurseDAO;
    }

    @Override
    public NurseDTO createNurse(String fname, String lname, String phone, String email, String passwordHash) {
        Nurse nurse = new Nurse(email, passwordHash, fname, lname, phone);
        Nurse savedNurse = nurseDAO.save(nurse);
        return DTOMapper.toDTO(savedNurse);
    }

    @Override
    public void deleteNurse(Long id) {
        Optional<Nurse> nurseOpt = nurseDAO.findById(id);
        if (nurseOpt.isEmpty()) {
            return;
        }
        nurseDAO.delete(nurseOpt.get());
    }

    @Override
    public void updateNurse(Long id, String fname, String lname, String phone, String email) {
        Optional<Nurse> nurseOpt = nurseDAO.findById(id);
        if (nurseOpt.isPresent()) {
            Nurse nurse = nurseOpt.get();
            nurse.setFname(fname);
            nurse.setLname(lname);
            nurse.setPhone(phone);
            nurse.setEmail(email);
            nurseDAO.update(nurse);
        }
    }

    @Override
    public Optional<NurseDTO> findById(Long id) {
        return nurseDAO.findById(id).map(DTOMapper::toDTO);
    }

    @Override
    public List<NurseDTO> findAll() {
        return nurseDAO.findAll()
                .stream()
                .map(DTOMapper::toDTO)
                .collect(Collectors.toList());
    }
}
