package com.andrew.safronov.sintez.theblackjack.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.andrew.safronov.sintez.theblackjack.entity.Purse;
import com.andrew.safronov.sintez.theblackjack.game.Desk;
import com.andrew.safronov.sintez.theblackjack.game.enums.GameActions;
import com.andrew.safronov.sintez.theblackjack.service.PurseService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-application-context.xml")
@Transactional
public class PurseServiceImplTest {

	private static final double DAFAULT_BUDGET_VALUE = 100;

	@Autowired
	private PurseService purseService;

	@Test
	public void isPurseExist() {
		Purse purse = new Purse();
		purse.setBalance(100);
		Desk registeredDesk = purseService.registerNewPurse(purse);

		boolean isPurseExist = purseService.isPurseExist(registeredDesk.getPurse().getPurseId());

		Assert.assertEquals(isPurseExist, true);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void registerNewPurse() {
		Purse purse = new Purse();
		purse.setBalance(100);
		Desk registeredDesk = purseService.registerNewPurse(purse);

		Assert.assertEquals(purse.getBalance(), registeredDesk.getPurse().getBalance(),1);
	}

	@Test
	public void registerNewPurseLogAction() {
		Purse purse = new Purse();
		purse.setBalance(100);
		Desk registeredDesk = purseService.registerNewPurse(purse);
		String action = registeredDesk.getPurse().getGame().getGameLogs().get(0).getAction();

		Assert.assertEquals(action, "startGame");
	}

	@SuppressWarnings("deprecation")
	@Test
	public void replenishPurse() {
		Purse purse = new Purse();
		purse.setBalance(DAFAULT_BUDGET_VALUE);
		Desk registeredDesk = purseService.registerNewPurse(purse);

		double coints = 200;
		Desk replenishedDesc = purseService.replenishPurse(registeredDesk.getPurse().getPurseId(), coints);

		Assert.assertEquals(DAFAULT_BUDGET_VALUE + coints, replenishedDesc.getPurse().getBalance(),1);
	}

	@Test
	public void replenishPurseCheckActionLog() {
		Purse purse = new Purse();
		purse.setBalance(100);
		Desk registeredDesk = purseService.registerNewPurse(purse);

		int coints = 200;
		Desk replenishedDesc = purseService.replenishPurse(registeredDesk.getPurse().getPurseId(), coints);

		Assert.assertEquals(GameActions.START_GAME.getAction(),
				replenishedDesc.getPurse().getGame().getGameLogs().get(0).getAction());
	}

	@Test
	public void replenishPurseCheckSumLog() {
		Purse purse = new Purse();
		purse.setBalance(100);
		Desk registeredDesk = purseService.registerNewPurse(purse);

		int coints = 200;
		Desk replenishedDesc = purseService.replenishPurse(registeredDesk.getPurse().getPurseId(), coints);

		Assert.assertEquals(coints, replenishedDesc.getPurse().getGame().getGameLogs().get(1).getValue(),1);
	}

}
