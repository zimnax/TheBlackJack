package com.andrew.safronov.sintez.theblackjack.service;

import com.andrew.safronov.sintez.theblackjack.entity.Purse;
import com.andrew.safronov.sintez.theblackjack.game.Desk;

public interface PurseService {

	/**
	 * Method register new {@link Purse} in repository.
	 * <p>
	 * With purse also registered new {@link Game} and {@link Logs}
	 * 
	 * @param purse
	 *            {@link Purse}
	 * @return {@link Desk}
	 */
	Desk registerNewPurse(Purse purse);

	/**
	 * Method replenish existing {@link Purse} in repository for determinate
	 * amount of coins
	 * 
	 * @param purseID
	 *            {@link Purse}
	 * @param coints
	 * @return {@link Desk}
	 */
	Desk replenishPurse(long purseID, double coints);

	/**
	 * Method check is {@link Purse} with determinate purseId exist in database
	 * 
	 * @param purseID
	 *            {@link Purse}
	 * @return {@link boolean}
	 */
	boolean isPurseExist(long purseID);

}
