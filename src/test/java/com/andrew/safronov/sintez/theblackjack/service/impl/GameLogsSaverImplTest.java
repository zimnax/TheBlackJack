package com.andrew.safronov.sintez.theblackjack.service.impl;

import java.util.Stack;

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
import com.andrew.safronov.sintez.theblackjack.game.Card;
import com.andrew.safronov.sintez.theblackjack.game.Desk;
import com.andrew.safronov.sintez.theblackjack.game.enums.CardRanks;
import com.andrew.safronov.sintez.theblackjack.game.enums.CardSuits;
import com.andrew.safronov.sintez.theblackjack.game.enums.GameActions;
import com.andrew.safronov.sintez.theblackjack.game.enums.GameParticipants;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-application-context.xml")
@Transactional
public class GameLogsSaverImplTest {

	@Autowired
	private GameLogsSaverImpl gameLogsSaverImpl;

	@Test
	public void saveStartGameActionCheckAction() {
		Game game = new Game();
		Logs saveStartGameAction = gameLogsSaverImpl.saveStartGameAction(game);

		Assert.assertEquals(saveStartGameAction.getAction(), GameActions.START_GAME.getAction());
	}

	@Test
	public void saveReplenishActionCheckAction() {
		Purse purse = new Purse();
		int coins = 100;
		Logs saveStartGameAction = gameLogsSaverImpl.saveReplenishAction(purse, coins);

		Assert.assertEquals(saveStartGameAction.getAction(), GameActions.REPLENISH.getAction());
	}

	@Test
	public void saveReplenishActionCheckValue() {
		Purse purse = new Purse();
		double coins = 100;
		Logs saveStartGameAction = gameLogsSaverImpl.saveReplenishAction(purse, coins);

		Assert.assertEquals(saveStartGameAction.getValue(), coins,1);
	}

	@Test
	public void dealCardActionCheckPlayerAction() {
		Desk desk = new Desk(new Purse());
		Stack<Card> cards = new Stack<Card>();
		cards.push(new Card(CardSuits.HEARTS, CardRanks.EIGHT));
		desk.setPlayerCards(cards);

		Logs saveDealCardsAction = gameLogsSaverImpl.saveDealCardsAction(desk, GameParticipants.PLAYER);

		Assert.assertEquals(GameActions.DEAL_CARD.getAction(), saveDealCardsAction.getAction());
	}

	@Test
	public void dealCardActionCheckDealerAction() {
		Desk desk = new Desk(new Purse());
		Stack<Card> cards = new Stack<Card>();
		cards.push(new Card(CardSuits.HEARTS, CardRanks.EIGHT));
		desk.setDealerCards(cards);

		Logs saveDealCardsAction = gameLogsSaverImpl.saveDealCardsAction(desk, GameParticipants.DEALER);

		Assert.assertEquals(GameActions.DEAL_CARD.getAction(), saveDealCardsAction.getAction());
	}

	@Test
	public void dealCardActionCheckCardRank() {
		Desk desk = new Desk(new Purse());
		Stack<Card> cards = new Stack<Card>();
		cards.push(new Card(CardSuits.HEARTS, CardRanks.EIGHT));
		desk.setDealerCards(cards);

		Logs saveDealCardsAction = gameLogsSaverImpl.saveDealCardsAction(desk, GameParticipants.DEALER);

		Assert.assertEquals(CardRanks.EIGHT.getValue(), saveDealCardsAction.getCardRank());
	}

	@Test
	public void saveHitActionCheckPlayerAction() {

		Desk desk = new Desk(new Purse());
		Stack<Card> cards = new Stack<Card>();
		cards.push(new Card(CardSuits.DIAMONDS, CardRanks.EIGHT));
		desk.setPlayerCards(cards);

		Logs saveDealCardsAction = gameLogsSaverImpl.saveHitAction(desk, GameParticipants.PLAYER);

		Assert.assertEquals(GameActions.HIT.getAction(), saveDealCardsAction.getAction());
	}

	@Test
	public void saveHitActionCheckCardRank() {

		Desk desk = new Desk(new Purse());
		Stack<Card> cards = new Stack<Card>();
		cards.push(new Card(CardSuits.DIAMONDS, CardRanks.EIGHT));
		desk.setPlayerCards(cards);

		Logs saveDealCardsAction = gameLogsSaverImpl.saveHitAction(desk, GameParticipants.PLAYER);

		Assert.assertEquals(CardRanks.EIGHT.getValue(), saveDealCardsAction.getCardRank());
	}

	@Test
	public void saveHitActionCheckDealerAction() {

		Desk desk = new Desk(new Purse());
		Stack<Card> cards = new Stack<Card>();
		cards.push(new Card(CardSuits.DIAMONDS, CardRanks.EIGHT));
		desk.setDealerCards(cards);

		Logs saveDealCardsAction = gameLogsSaverImpl.saveHitAction(desk, GameParticipants.DEALER);

		Assert.assertEquals(GameActions.HIT.getAction(), saveDealCardsAction.getAction());
	}

}
