package com.andrew.safronov.sintez.theblackjack.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.andrew.safronov.sintez.theblackjack.entity.Purse;
import com.andrew.safronov.sintez.theblackjack.service.PurseService;

@Controller
@RequestMapping("/initPurse")
public class PlayerController {

    private static final Logger LOGGER = Logger.getLogger(PlayerController.class);

    private static final int DAFAULT_BUDGET_VALUE = 100;

    @Autowired
    private PurseService purseService;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody Purse registerDefaultPlayer() {
        LOGGER.info("Ititialize new 'DEFAULT' user in system with balance = " + DAFAULT_BUDGET_VALUE);
        Purse purse = new Purse();
        purse.setBalance(DAFAULT_BUDGET_VALUE);

        return purseService.registerNewPurse(purse);
    }

    @RequestMapping(value = "/{balance}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody Purse registerSpecialPlayer(@PathVariable int balance) {
        LOGGER.info("Ititialize new 'SPECIAL' user in system with balance = " + balance);
        Purse purse = new Purse();
        purse.setBalance(balance);

        return purseService.registerNewPurse(purse);
    }

}
