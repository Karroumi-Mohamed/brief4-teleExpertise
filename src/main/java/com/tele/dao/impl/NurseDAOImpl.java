package com.tele.dao.impl;

import com.tele.dao.NurseDAO;
import com.tele.model.Nurse;

public class NurseDAOImpl extends GenericDAOImpl<Nurse, Long> implements NurseDAO {

    public NurseDAOImpl() {
        super(Nurse.class);
    }
}
