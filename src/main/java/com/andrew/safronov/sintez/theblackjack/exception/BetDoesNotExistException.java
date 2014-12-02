package com.andrew.safronov.sintez.theblackjack.exception;

public class BetDoesNotExistException extends BlackjackGameException {

	private static final long serialVersionUID = 5616755106325333932L;

	public BetDoesNotExistException() {
		super();
	}

	public BetDoesNotExistException(String message, Throwable cause) {
		super(message, cause);
	}

	public BetDoesNotExistException(String message) {
		super(message);
	}

	public BetDoesNotExistException(Throwable cause) {
		super(cause);
	}

}
