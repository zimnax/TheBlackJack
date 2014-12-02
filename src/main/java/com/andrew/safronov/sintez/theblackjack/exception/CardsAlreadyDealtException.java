package com.andrew.safronov.sintez.theblackjack.exception;

public class CardsAlreadyDealtException extends BlackjackGameException {

	private static final long serialVersionUID = 2805217288140732798L;

	public CardsAlreadyDealtException() {
		super();
	}

	public CardsAlreadyDealtException(String message) {
		super(message);
	}

}
