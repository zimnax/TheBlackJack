package com.andrew.safronov.sintez.theblackjack.game;

import com.andrew.safronov.sintez.theblackjack.game.enums.CardRanks;
import com.andrew.safronov.sintez.theblackjack.game.enums.CardSuits;

public class Card {

	private CardRanks cardRank;

	private CardSuits cardSuit;

	public Card(CardSuits cardSuit, CardRanks cardRank) {
		super();
		this.cardSuit = cardSuit;
		this.cardRank = cardRank;
	}

	public CardRanks getCardRank() {
		return cardRank;
	}

	public void setCardRank(CardRanks cardRank) {
		this.cardRank = cardRank;
	}

	public CardSuits getCardSuit() {
		return cardSuit;
	}

	public void setCardSuit(CardSuits cardSuit) {
		this.cardSuit = cardSuit;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		if (cardRank != other.cardRank)
			return false;
		return true;
	}

}
