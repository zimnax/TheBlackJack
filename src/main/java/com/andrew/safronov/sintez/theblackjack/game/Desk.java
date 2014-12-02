package com.andrew.safronov.sintez.theblackjack.game;

import java.util.Stack;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.andrew.safronov.sintez.theblackjack.entity.Purse;

public class Desk {

	private Purse purse;

	private int bet;

	private String gameStatus;

	private int playerPoints;

	private int dealerPoints;

	@JsonIgnore
	private Card hiddenDealerCard;

	public Desk(Purse purse) {
		super();
		this.purse = purse;
	}

	@JsonIgnore
	private Stack<Card> cards = new Stack<Card>();

	private Stack<Card> playerCards = new Stack<Card>();

	private Stack<Card> dealerCards = new Stack<Card>();

	public Purse getPurse() {
		return purse;
	}

	public void setPurse(Purse purse) {
		this.purse = purse;
	}

	public Stack<Card> getPlayerCards() {
		return playerCards;
	}

	public void setPlayerCards(Stack<Card> playerCards) {
		this.playerCards = playerCards;
	}

	public Stack<Card> getDealerCards() {
		return dealerCards;
	}

	public void setDealerCards(Stack<Card> dealerCards) {
		this.dealerCards = dealerCards;
	}

	public Stack<Card> getCards() {
		return cards;
	}

	public void setCards(Stack<Card> cards) {
		this.cards = cards;
	}

	public int getBet() {
		return bet;
	}

	public void setBet(int bet) {
		this.bet = bet;
	}

	public int getPlayerPoints() {
		return playerPoints;
	}

	public void setPlayerPoints(int playerPoints) {
		this.playerPoints = playerPoints;
	}

	public int getDealerPoints() {
		return dealerPoints;
	}

	public void setDealerPoints(int dealerPoints) {
		this.dealerPoints = dealerPoints;
	}

	public String getGameStatus() {
		return gameStatus;
	}

	public void setGameStatus(String gameStatus) {
		this.gameStatus = gameStatus;
	}

	public Card getHiddenDealerCard() {
		return hiddenDealerCard;
	}

	public void setHiddenDealerCard(Card hiddenDealerCard) {
		this.hiddenDealerCard = hiddenDealerCard;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Desk other = (Desk) obj;
		if (purse == null) {
			if (other.purse != null)
				return false;
		} else if (!purse.equals(other.purse))
			return false;
		return true;
	}

}
