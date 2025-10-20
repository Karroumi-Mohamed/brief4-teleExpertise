package com.tele.dao.impl;

import com.tele.dao.GeneralPractitionerDAO;
import com.tele.model.GeneralPractitioner;

public class GeneralPractitionerDAOImpl extends GenericDAOImpl<GeneralPractitioner, Long> implements GeneralPractitionerDAO {

    public GeneralPractitionerDAOImpl() {
        super(GeneralPractitioner.class);
    }
}
