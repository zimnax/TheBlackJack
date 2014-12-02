package com.andrew.safronov.sintez.theblackjack.service;

import com.andrew.safronov.sintez.theblackjack.game.Desk;

public interface GameEngineService {

	/**
	 * Method dealCards deal {@link Card} to the Player and Dealer;
	 * <p>
	 * Method initialize new cards, shuffle it, and set two Card to Player and
	 * two Card to Dealer. One of Dealers Cards stay hidden.
	 * 
	 * 
	 * @param purseId
	 *            {@link Purse}
	 * @return {@link Desk}
	 */
	Desk dealCards(long purseId);

	/**
	 * Method doBet take a bet from the Player
	 * 
	 * 
	 * @param purseId
	 *            {@link Purse}
	 * @param coins
	 * @return {@link Desk}
	 */
	Desk doBet(long purseId, int coins);

	/**
	 * Method doHit take a one random {@link Card} from the {@link Stack<Cards>}
	 * and deal it to the Player.
	 * 
	 * 
	 * @param purseId
	 *            {@link Purse}
	 * @return {@link Desk}
	 */
	Desk doHit(long purseId);

	/**
	 * Method doStand interrupt game, counted Players points, and Dealer doHit
	 * and counted Points too. The pints are comparing, and winner determined.
	 * 
	 * @param purseId
	 *            {@link Purse}
	 * @return {@link Desk}
	 */
	Desk doStand(long purseId);

}
