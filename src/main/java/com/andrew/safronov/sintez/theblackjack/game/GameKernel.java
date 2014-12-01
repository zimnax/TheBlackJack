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

    public Desk hit(Desk desk) {
        LOGGER.info("Player do HIT action");
        Card popCard = desk.getCards().pop();
        desk.getPlayerCards().push(popCard);
        LOGGER.info("Hit card: " + popCard.getCardRank() + " " + popCard.getCardSuit());
        return analyzeCards(desk);
    }

    public Desk analyzeCards(Desk desk) {
        LOGGER.info("Start to analyze Player cards");
        int playerPoints = countPlayerPoints(desk.getPlayerCards());
        desk.setPlayerPoints(playerPoints);
        if (playerPoints == THE_BLACK_JACK) {
            Desk standDesk = stand(desk);
            if (standDesk.getDealerPoints() == THE_BLACK_JACK) {
                standDesk.setGameStatus(GameStatus.DRAW.getStatus());
                return standDesk;
            } else {
                standDesk.setGameStatus(GameStatus.WIN.getStatus());// TODO: blackjack
                standDesk.getPurse().setBalance(standDesk.getBet() * 2);
                return standDesk;
            }
        } else if (playerPoints > THE_BLACK_JACK) {
            desk.setGameStatus(GameStatus.LOOSE.getStatus());// TODO: blackjack
            desk.getPurse().setBalance(desk.getBet() - (desk.getBet() * 2));
            return desk;
        } else {
            desk.setGameStatus(GameStatus.PENDING.getStatus());
            return desk;
        }
    }

    public Desk stand(Desk desk) {
        LOGGER.info("Player do STAND action");
        return analyzeStandAction(desk);
    }

    private Desk analyzeStandAction(Desk desk) {
        LOGGER.info("Analyze Stand Action");
        int playerPoints = countPlayerPoints(desk.getPlayerCards());

        desk.getDealerCards().push(desk.getHiddenDealerCard());
        int dealerPoints = countDealerPoints(desk.getDealerCards());

        desk.setPlayerPoints(playerPoints);
        desk.setDealerPoints(dealerPoints);

        if (dealerPoints < artificialIntelligenceLimit) {///////////////////////
            Desk dealerGame = dealerLuckyGame(desk, dealerPoints);
            dealerGame.setPlayerPoints(playerPoints);
//            dealerGame.setDealerPoints(dealerPoints);
            if (dealerGame.getDealerPoints() > THE_BLACK_JACK) {
                dealerGame.setGameStatus(GameStatus.WIN.getStatus());
                dealerGame.getPurse().setBalance(dealerGame.getBet() * 2);
                return dealerGame;
            } else if (dealerGame.getDealerPoints() <= THE_BLACK_JACK && dealerPoints > dealerGame.getPlayerPoints()) {
                dealerGame.setGameStatus(GameStatus.LOOSE.getStatus());

                dealerGame.getPurse().setBalance(dealerGame.getPurse().getBalance() - (dealerGame.getBet() * 2));
                return dealerGame;
            } else if (dealerGame.getDealerPoints() == dealerGame.getPlayerPoints()) {
                dealerGame.setGameStatus(GameStatus.DRAW.getStatus());
                return dealerGame;
            }
        } else {
            return checkPoints(desk);
        }
        return desk;
    }

    public Desk dealerLuckyGame(Desk desk, int dealerPoints) {
        LOGGER.info("Dealer start lucky game");
        int sumDealerPoints = dealerPoints;
        while (sumDealerPoints < artificialIntelligenceLimit || dealerPoints == THE_BLACK_JACK) {
            desk.getDealerCards().push(desk.getCards().pop());
            sumDealerPoints = countDealerPoints(desk.getDealerCards());
        }
        desk.setDealerPoints(sumDealerPoints);
        return desk;
    }

    public Desk checkPoints(Desk desk) {
        int playerPoints = countPlayerPoints(desk.getPlayerCards());
        int dealerPoints = countDealerPoints(desk.getDealerCards());

        desk.setDealerPoints(dealerPoints);
        desk.setPlayerPoints(playerPoints);

        if (dealerPoints > playerPoints && dealerPoints <= THE_BLACK_JACK) {
            desk.setGameStatus(GameStatus.LOOSE.getStatus());
            desk.getPurse().setBalance(desk.getPurse().getBalance() - (desk.getBet() * 2));
            desk.setDealerPoints(dealerPoints);
            return desk;
        } else if (playerPoints > dealerPoints && playerPoints <= THE_BLACK_JACK) {
            desk.setGameStatus(GameStatus.WIN.getStatus());
            desk.getPurse().setBalance(desk.getPurse().getBalance() * 2);
            return desk;
        } else {
            desk.setGameStatus(GameStatus.DRAW.getStatus());
            return desk;
        }
    }

    public int countPlayerPoints(Stack<Card> playerCards) {
        int playerPoints = 0;
        for (Card card : playerCards) {
            playerPoints += card.getCardRank().getValue();
        }
        if (playerPoints > THE_BLACK_JACK && playerCards.contains(new Card(CardSuits.HEARTS, CardRanks.ACE))) {
            LOGGER.info("Dealer contains big ACE. Transform it to small ACE");
            playerPoints -= ACE_DIFFERENCE;
            LOGGER.info("Count dealer points. TOTAL: " + playerPoints);
            return playerPoints;
        }
        LOGGER.info("Count player points. TOTAL: " + playerPoints);
        return playerPoints;
    }

    public int countDealerPoints(Stack<Card> dealerCards) {
        int dealerPoints = 0;
        for (Card card : dealerCards) {
            dealerPoints += card.getCardRank().getValue();
        }
        if (dealerPoints > THE_BLACK_JACK && dealerCards.contains(new Card(CardSuits.HEARTS, CardRanks.ACE))) {
            LOGGER.info("Dealer contains big ACE. Transform it to small ACE");
            dealerPoints -= ACE_DIFFERENCE;
            LOGGER.info("Count dealer points. TOTAL: " + dealerPoints);
            return dealerPoints;
        }
        LOGGER.info("Count dealer points. TOTAL: " + dealerPoints);
        return dealerPoints;
    }

}
