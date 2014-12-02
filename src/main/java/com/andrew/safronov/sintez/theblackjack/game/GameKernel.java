package com.andrew.safronov.sintez.theblackjack.game;

import java.util.Collections;
import java.util.Stack;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.andrew.safronov.sintez.theblackjack.game.enums.CardRanks;
import com.andrew.safronov.sintez.theblackjack.game.enums.CardSuits;
import com.andrew.safronov.sintez.theblackjack.game.enums.GameStatus;

@Component
public class GameKernel {

	private static final Logger LOGGER = Logger.getLogger(GameKernel.class);

	private static final int THE_BLACK_JACK = 21;

	private static final int ACE_DIFFERENCE = 10;

	private static final int artificialIntelligenceLimit = 17;

	public void initFreshCards(Stack<Card> cards) {
		LOGGER.info("Start to init net cards");
		for (CardSuits suit : CardSuits.values()) {
			for (CardRanks rank : CardRanks.values()) {
				cards.push(new Card(suit, rank));
			}
		}
		Collections.shuffle(cards);
	}

	public Desk analyzeCards(Desk desk) {
		LOGGER.info("Start to analyze cards");
		int playerPoints = analyzePlayerPoints(desk.getPlayerCards());
		desk.setPlayerPoints(playerPoints);
		if (playerPoints == THE_BLACK_JACK) {
			LOGGER.info("Player has a BLACKJACK ");
			desk.setGameStatus(GameStatus.WIN.getStatus());
			desk.setPlayerPoints(playerPoints);
			return analyzeBlackJackSituation(desk);
		} else {
			return analyzeHitAction(desk, playerPoints);
		}
	}

	private Desk analyzeHitAction(Desk desk, int playerPoints) {
		if (playerPoints > THE_BLACK_JACK) {
			LOGGER.info("Player points: " + playerPoints);
			if (desk.getPlayerCards().contains(new Card(CardSuits.HEARTS, CardRanks.ACE))) {
				LOGGER.info("Dealer contains big ACE. Transform it to small ACE");
				playerPoints -= ACE_DIFFERENCE;
				desk.setPlayerPoints(playerPoints);
				return desk;
			} else {
				desk.getDealerCards().push(desk.getHiddenDealerCard());
				int analyzeDealerPoints = analyzeDealerPoints(desk.getDealerCards());

				LOGGER.info("Start to subtract balanse");
				desk.setDealerPoints(analyzeDealerPoints);
				desk.setGameStatus(GameStatus.LOOSE.getStatus());
				int bet = desk.getBet();
				desk.getPurse().setBalance(desk.getPurse().getBalance() - bet);

				return desk;
			}
		}
		desk.setPlayerPoints(playerPoints);
		desk.setGameStatus(GameStatus.PENDING.getStatus());
		return desk;
	}

	private Desk analyzeBlackJackSituation(Desk desk) {
		LOGGER.info("Start to analyze PLAYER has a BLACKJACK situation");
		desk.getDealerCards().push(desk.getHiddenDealerCard());
		int dealerPoints = analyzeDealerPoints(desk.getDealerCards());
		desk.setDealerPoints(dealerPoints);

		if (dealerPoints == THE_BLACK_JACK) {
			LOGGER.info("Dealer has a BLACKJACK ");
			desk.setGameStatus(GameStatus.DRAW.getStatus());
			LOGGER.info("Game result: DRAW");
			return desk;
		} else {
			return dealerLuckyGame(desk, dealerPoints);
		}
	}

	public int analyzePlayerPoints(Stack<Card> playerCards) {
		int playerPoints = 0;
		for (Card card : playerCards) {
			playerPoints += card.getCardRank().getValue();
		}
		return playerPoints;
	}

	public int analyzeDealerPoints(Stack<Card> dealerCards) {
		int dealerPoints = 0;
		for (Card card : dealerCards) {
			dealerPoints += card.getCardRank().getValue();
		}
		return dealerPoints;
	}

	public Desk dealerLuckyGame(Desk desk, int dealerPoints) {
		LOGGER.info("Start dealer lucky game");

		while (dealerPoints < artificialIntelligenceLimit || dealerPoints == THE_BLACK_JACK) {
			desk.getDealerCards().push(desk.getCards().pop());
			dealerPoints = analyzeDealerPoints(desk.getDealerCards());
			LOGGER.info("Dealer points: " + dealerPoints);
			desk.setDealerPoints(dealerPoints);
			if (dealerPoints == THE_BLACK_JACK) {
				LOGGER.info("Dealer has a BLACKJACK ");
				LOGGER.info("Game result: DRAW");
				desk.setGameStatus(GameStatus.DRAW.getStatus());
				return desk;
			}
			if (dealerPoints > THE_BLACK_JACK) {
				LOGGER.info("Dealer points: " + dealerPoints);
				if (desk.getDealerCards().contains(new Card(CardSuits.HEARTS, CardRanks.ACE))) {
					LOGGER.info("Dealer contains big ACE. Transform it to small ACE");
					dealerPoints -= ACE_DIFFERENCE;
					continue;
				} else {
					LOGGER.info("Dealer BUST ");
					desk.setGameStatus(GameStatus.WIN.getStatus());
					desk.setDealerPoints(dealerPoints);
					double wins = desk.getBet() * 2;
					desk.getPurse().setBalance(wins);
					return desk;
				}
			}
		}
		return desk;
	}

	public Desk getOneMoreCard(Desk desk) {
		LOGGER.info("Player do HIT action");
		System.out.println("PLAYER CARDS AMOUNT: " + desk.getPlayerCards().size());
		Card popCard = desk.getCards().pop();
		desk.getPlayerCards().push(popCard);
		LOGGER.info("Hit card: " + popCard.getCardRank() + " " + popCard.getCardSuit());
		return analyzeCards(desk);
	}

	public Desk stand(Desk desk) {
		LOGGER.info("Playre do STAND action");
		return analyzeCards(desk);
	}
}
