package com.andrew.safronov.sintez.theblackjack.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andrew.safronov.sintez.theblackjack.entity.Game;
import com.andrew.safronov.sintez.theblackjack.entity.Logs;
import com.andrew.safronov.sintez.theblackjack.entity.Purse;
import com.andrew.safronov.sintez.theblackjack.game.Desk;
import com.andrew.safronov.sintez.theblackjack.repository.PurseRepository;
import com.andrew.safronov.sintez.theblackjack.service.PurseService;

@Service
public class PurseServiceImpl implements PurseService {

	private static final Logger LOGGER = Logger.getLogger(PurseServiceImpl.class);

	@Autowired
	private PurseRepository purseRepository;

	@Autowired
	private GameLogsSaverImpl gameLogsSaver;

	@Override
	public Desk registerNewPurse(Purse purse) {
		LOGGER.info("Start to register new purse");
		Purse defPurse = initPurseByDefaultGame(purse);
		Purse savedPurse = purseRepository.save(defPurse);

		Desk desk = new Desk(savedPurse);
		GameEngineServiceImpl.currentSystemDesks.put(new Long(savedPurse.getPurseId()), desk);

		return desk;
	}

	@Override
	public Desk replenishPurse(long purseID, double coins) {
		LOGGER.info("Start to replenish existing purse");

		Desk desk = GameEngineServiceImpl.currentSystemDesks.get(purseID);
		Purse currentPurse = desk.getPurse();
		currentPurse.setBalance(currentPurse.getBalance() + coins);

		Logs savedReplanishAction = gameLogsSaver.saveReplenishAction(currentPurse, coins);
		currentPurse.getGame().getGameLogs().add(savedReplanishAction);
		purseRepository.save(currentPurse);

		return desk;
	}

	@Override
	public boolean isPurseExist(long purseID) {
		LOGGER.info("Check is purse exist");
		return purseRepository.exists(purseID);
	}

	private Purse initPurseByDefaultGame(Purse purse) {
		LOGGER.info("Start to init purse. Adding game");
		Game game = new Game();
		game.setPurse(purse);

		Logs savedStartGameAction = gameLogsSaver.saveStartGameAction(game);
		game.getGameLogs().add(savedStartGameAction);

		purse.setGame(game);
		return purse;
	}

}
