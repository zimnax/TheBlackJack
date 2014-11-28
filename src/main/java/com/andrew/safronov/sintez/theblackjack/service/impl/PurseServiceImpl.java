package com.andrew.safronov.sintez.theblackjack.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.andrew.safronov.sintez.theblackjack.entity.Purse;
import com.andrew.safronov.sintez.theblackjack.repository.PurseRepository;
import com.andrew.safronov.sintez.theblackjack.service.PurseService;

@Component
public class PurseServiceImpl implements PurseService {

    private static final Logger LOGGER = Logger.getLogger(PurseServiceImpl.class);

    @Autowired
    PurseRepository purseRepository;

    @Override
    public Purse registerNewPurse(Purse purse) {
        LOGGER.info("Start to register new purse");
        return purseRepository.save(purse);
    }

    @Override
    public Purse DO() {
        Purse purse = new Purse();
        purse.setBalance(100);
        purse.setPurseId(1l);

        return purse;
    }

}
