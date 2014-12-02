package com.andrew.safronov.sintez.theblackjack.service;

import com.andrew.safronov.sintez.theblackjack.entity.Game;
import com.andrew.safronov.sintez.theblackjack.entity.Logs;
import com.andrew.safronov.sintez.theblackjack.entity.Purse;
import com.andrew.safronov.sintez.theblackjack.game.Desk;
import com.andrew.safronov.sintez.theblackjack.game.enums.GameActions;
import com.andrew.safronov.sintez.theblackjack.game.enums.GameParticipants;

public interface GameLogsSaver {

	/**
	 * Method save {@link GameActions.START_GAME} action in database logs
	 * 
	 * @param game
	 *            {@link Game}
	 * 
	 * @return {@link Logs}
	 */
	Logs saveStartGameAction(Game game);

	/**
	 * Method save {@link GameActions.BET} action in database logs
	 * 
	 * @param purse
	 *            {@link Purse}
	 * @param bet
	 * 
	 * @return {@link Logs}
	 */
	Logs saveBetAction(Purse purse, int bet);

	/**
	 * Method save {@link GameActions.REPLENISH} action in database logs
	 * 
	 * @param currentPurse
	 *            {@link Purse}
	 * @param coints
	 * 
	 * @return {@link Logs}
	 */
	Logs saveReplenishAction(Purse currentPurse, double coints);

	/**
	 * Method save {@link GameActions.DEAL_CARD} action in database logs
	 * 
	 * @param desk
	 *            {@link Desk}
	 * @param participant
	 *            {@link GameParticipants}
	 * @return {@link Logs}
	 */
	Logs saveDealCardsAction(Desk desk, GameParticipants participant);

	/**
	 * Method save {@link GameActions.HIT} action in database logs
	 * 
	 * @param desk
	 *            {@link Desk}
	 * @param participant
	 *            {@link GameParticipants}
	 * @return {@link Logs}
	 */
	Logs saveHitAction(Desk desk, GameParticipants participant);

	/**
	 * Method save {@link GameActions.STAND} action in database logs
	 * 
	 * @param desk
	 *            {@link Desk}
	 * 
	 * @return {@link Logs}
	 */
	Logs saveStandAction(Desk desk);

}
