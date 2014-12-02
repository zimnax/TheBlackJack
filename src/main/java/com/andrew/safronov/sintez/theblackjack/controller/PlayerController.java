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

import com.andrew.safronov.sintez.theblackjack.entity.Purse;
import com.andrew.safronov.sintez.theblackjack.exception.ExceptionInfo;
import com.andrew.safronov.sintez.theblackjack.exception.PurseNotFoundException;
import com.andrew.safronov.sintez.theblackjack.exception.constants.ExceptionConstants;
import com.andrew.safronov.sintez.theblackjack.game.Desk;
import com.andrew.safronov.sintez.theblackjack.service.GameEngineService;
import com.andrew.safronov.sintez.theblackjack.service.PurseService;

@Controller
@RequestMapping("/purse")
public class PlayerController {

	private static final Logger LOGGER = Logger.getLogger(PlayerController.class);

	private static final int DAFAULT_BUDGET_VALUE = 100;

	@Autowired
	private PurseService purseService;

	@Autowired
	private GameEngineService gameEngineService;

	/**
	 * This method responsible for the {@link URI} /purse/initDefaultPlayer
	 * request
	 * <p>
	 * {@link RequestMethod.GET}
	 * 
	 * @return {@link Desk}
	 */
	@RequestMapping(value = "/initDefaultPlayer", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Desk registerDefaultPlayer() {
		LOGGER.info("Ititialize new 'DEFAULT' user in system with balance = " + DAFAULT_BUDGET_VALUE);
		Purse purse = new Purse();
		purse.setBalance(DAFAULT_BUDGET_VALUE);
		return purseService.registerNewPurse(purse);
	}

	/**
	 * This method responsible for the {@link URI}
	 * /purse/initSpecialPlayer/{balance} request
	 * <p>
	 * {@link RequestMethod.GET}
	 * 
	 * @param balance
	 *            {@link int}
	 * @return {@link Desk}
	 */
	@RequestMapping(value = "/initSpecialPlayer/{balance}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Desk registerSpecialPlayer(@PathVariable int balance) {
		LOGGER.info("Ititialize new 'SPECIAL' user in system with balance = " + balance);
		Purse purse = new Purse();
		purse.setBalance(balance);
		return purseService.registerNewPurse(purse);
	}

	/**
	 * This method responsible for the {@link URI}
	 * /purse/replenish/{purseID}/{coints} request
	 * 
	 * @param purseID
	 *            {@link long}
	 * @param coints
	 *            {@link int}
	 * 
	 * @return {@link Desk}
	 */
	@RequestMapping(value = "/replenish/{purseID}/{coints}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Desk replenishPurse(@PathVariable long purseID, @PathVariable int coints) {
		if (purseService.isPurseExist(purseID)) {
			LOGGER.info("Replenish existing purse with id: " + purseID + " by " + coints + " coints");
			return purseService.replenishPurse(purseID, coints);
		}
		LOGGER.info(ExceptionConstants.PURSE_NOT_FOUND_EXCEPTION);
		throw new PurseNotFoundException(ExceptionConstants.PURSE_NOT_FOUND_EXCEPTION);
	}

	@ExceptionHandler(RuntimeException.class)
	private @ResponseBody ExceptionInfo handleBadRequest(HttpServletRequest req, Exception ex) {
		return new ExceptionInfo(req.getRequestURL().toString(), ex);
	}

}
