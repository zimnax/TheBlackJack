package com.andrew.safronov.sintez.theblackjack.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.andrew.safronov.sintez.theblackjack.exception.ExceptionInfo;
import com.andrew.safronov.sintez.theblackjack.exception.PurseNotFoundException;
import com.andrew.safronov.sintez.theblackjack.exception.constants.ExceptionConstants;
import com.andrew.safronov.sintez.theblackjack.game.Desk;
import com.andrew.safronov.sintez.theblackjack.service.GameEngineService;
import com.andrew.safronov.sintez.theblackjack.service.PurseService;

@Controller
@RequestMapping("/action/{purseId}")
public class GameEngineController {

    private static final Logger LOGGER = Logger.getLogger(GameEngineController.class);

    @Autowired
    private GameEngineService gameEngineService;

    @Autowired
    private PurseService purseService;

    @RequestMapping(value = "/bet/{coins}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody Desk doBet(@PathVariable long purseId, @PathVariable int coins) {
        if (purseService.isPurseExist(purseId)) {
            LOGGER.info("Purse exist. Start to do bet");
            return gameEngineService.doBet(purseId, coins);
        }
        throw new PurseNotFoundException(ExceptionConstants.PURSE_NOT_FOUND_EXCEPTION);
    }

    @RequestMapping(value = "/deal", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody Desk deal(@PathVariable long purseId) {
        if (purseService.isPurseExist(purseId)) {
            return gameEngineService.dealCards(purseId);
        }
        throw new PurseNotFoundException(ExceptionConstants.PURSE_NOT_FOUND_EXCEPTION);
    }

    @RequestMapping(value = "/hit", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody Desk hit(@PathVariable long purseId) {
        if (purseService.isPurseExist(purseId)) {
            return gameEngineService.doHit(purseId);
        }
        throw new PurseNotFoundException(ExceptionConstants.PURSE_NOT_FOUND_EXCEPTION);
    }

    @RequestMapping(value = "/stand", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody Desk stand(@PathVariable long purseId) {
        if (purseService.isPurseExist(purseId)) {
            return gameEngineService.doStand(purseId);
        }
        throw new PurseNotFoundException(ExceptionConstants.PURSE_NOT_FOUND_EXCEPTION);
    }

    @ExceptionHandler(RuntimeException.class)
    private @ResponseBody ExceptionInfo handleBadRequest(HttpServletRequest req, Exception ex) {
        return new ExceptionInfo(req.getRequestURL().toString(), ex);
    }

}
