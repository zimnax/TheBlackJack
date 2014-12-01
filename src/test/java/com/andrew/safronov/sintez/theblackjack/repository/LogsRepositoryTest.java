package com.andrew.safronov.sintez.theblackjack.repository;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.andrew.safronov.sintez.theblackjack.entity.Game;
import com.andrew.safronov.sintez.theblackjack.entity.Logs;
import com.andrew.safronov.sintez.theblackjack.entity.Purse;
import com.andrew.safronov.sintez.theblackjack.game.enums.GameActions;
import com.andrew.safronov.sintez.theblackjack.repository.LogsRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-application-context.xml")
@Transactional
public class LogsRepositoryTest {

	@Autowired
	private LogsRepository logsRepository;

	@Test
	public void saveStartGameActionCheckAction() {

		Game game = new Game();
		Logs startGameLog = new Logs();
		startGameLog.setGame(game);
		startGameLog.setAction(GameActions.START_GAME.getAction());
		startGameLog.setTime(new Date().getTime());

		Logs savedLog = logsRepository.save(startGameLog);

		Assert.assertEquals(startGameLog.getAction(), savedLog.getAction());
	}

	@Test
	public void saveReplenishActionCheckAction() {
		Purse purse = new Purse();
		purse.setBalance(100);

		int coins = 100;

		Logs replenishLog = new Logs();
		replenishLog.setAction(GameActions.REPLENISH.getAction());
		replenishLog.setValue(coins);
		replenishLog.setTime(new Date().getTime());

		Logs savedLog = logsRepository.save(replenishLog);
		
		Assert.assertEquals(replenishLog.getAction(), savedLog.getAction());
	}

	@Test
	public void saveReplenishActionCheckSavedValue() {
		Purse purse = new Purse();
		purse.setBalance(100);

		double coins = 100;

		Logs replenishLog = new Logs();
		replenishLog.setAction(GameActions.REPLENISH.getAction());
		replenishLog.setValue(coins);
		replenishLog.setTime(new Date().getTime());

		Logs savedLog = logsRepository.save(replenishLog);

		Assert.assertEquals(coins, savedLog.getValue(),1);
	}
	
}
