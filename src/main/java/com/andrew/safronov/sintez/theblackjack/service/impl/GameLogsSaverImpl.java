package com.andrew.safronov.sintez.theblackjack.service.impl;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andrew.safronov.sintez.theblackjack.entity.Game;
import com.andrew.safronov.sintez.theblackjack.entity.Logs;
import com.andrew.safronov.sintez.theblackjack.entity.Purse;
import com.andrew.safronov.sintez.theblackjack.game.Desk;
import com.andrew.safronov.sintez.theblackjack.game.enums.GameActions;
import com.andrew.safronov.sintez.theblackjack.game.enums.GameParticipants;
import com.andrew.safronov.sintez.theblackjack.repository.LogsRepository;
import com.andrew.safronov.sintez.theblackjack.service.GameLogsSaver;

@Service
public class GameLogsSaverImpl implements GameLogsSaver {

	private static final Logger LOGGER = Logger.getLogger(GameLogsSaverImpl.class);

	@Autowired
	private LogsRepository logsRepository;

	@Override
	public Logs saveBetAction(Purse purse, int bet) {
		LOGGER.info("Save BET action for purse: " + purse.getPurseId());

		Logs betGameLog = new Logs();
		betGameLog.setGame(purse.getGame());
		betGameLog.setAction(GameActions.BET.getAction());
		betGameLog.setTime(new Date().getTime());
		return betGameLog;
	}

	@Override
	public Logs saveStartGameAction(Game game) {
		LOGGER.info("Save START_GAME action");

		Logs startGameLog = new Logs();
		startGameLog.setGame(game);
		startGameLog.setAction(GameActions.START_GAME.getAction());
		startGameLog.setTime(new Date().getTime());
		return startGameLog;
	}

	@Override
	public Logs saveReplenishAction(Purse currentPurse, double coins) {
		LOGGER.info("Save REPLENISH action for purse: " + currentPurse.getPurseId());

		Logs replenishLog = new Logs();
		replenishLog.setAction(GameActions.REPLENISH.getAction());
		replenishLog.setValue(coins);
		replenishLog.setTime(new Date().getTime());
		replenishLog.setGame(currentPurse.getGame());
		return replenishLog;
	}

	@Override
	public Logs saveDealCardsAction(Desk desk, GameParticipants participant) {
		LOGGER.info("Save DEAL_CARD action for purse: " + desk.getPurse().getPurseId());

		Logs dealCardLog = new Logs();
		dealCardLog.setAction(GameActions.DEAL_CARD.getAction());
		dealCardLog.setParticipant(participant.getParticipant());

		if (participant == GameParticipants.PLAYER) {
			dealCardLog.setCardRank(desk.getPlayerCards().peek().getCardRank().getValue());
		} else {
			dealCardLog.setCardRank(desk.getDealerCards().peek().getCardRank().getValue());
		}
		dealCardLog.setTime(new Date().getTime());
		dealCardLog.setGame(desk.getPurse().getGame());

		return dealCardLog;
	}

	@Override
	public Logs saveHitAction(Desk desk, GameParticipants participant) {
		LOGGER.info("Save HIT action for purse: " + desk.getPurse().getPurseId());

		Logs hitLog = new Logs();
		hitLog.setAction(GameActions.HIT.getAction());
		hitLog.setParticipant(participant.getParticipant());

		if (participant == GameParticipants.PLAYER) {
			hitLog.setCardRank(desk.getPlayerCards().peek().getCardRank().getValue());
		} else {
			hitLog.setCardRank(desk.getDealerCards().peek().getCardRank().getValue());
		}
		hitLog.setTime(new Date().getTime());
		hitLog.setGame(desk.getPurse().getGame());

		return hitLog;
	}

	@Override
	public Logs saveStandAction(Desk desk) {
		LOGGER.info("Save STAND action for purse: " + desk.getPurse().getPurseId());
		Logs standLog = new Logs();
		standLog.setAction(GameActions.STAND.getAction());
		standLog.setTime(new Date().getTime());
		standLog.setGame(desk.getPurse().getGame());
		return standLog;
	}

}
