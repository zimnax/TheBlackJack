package com.andrew.safronov.sintez.theblackjack.exception;

public class BetAlreadyExistException extends BlackjackGameException {

	private static final long serialVersionUID = 6015533266489286180L;

	public BetAlreadyExistException() {
		super();
	}

	public BetAlreadyExistException(String message) {
		super(message);
	}

}
