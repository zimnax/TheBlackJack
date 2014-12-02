package com.andrew.safronov.sintez.theblackjack.exception;

public class PurseNotFoundException extends BlackjackGameException {

	private static final long serialVersionUID = -6299512094671221205L;

	public PurseNotFoundException() {
		super();
	}

	public PurseNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public PurseNotFoundException(String message) {
		super(message);
	}

	public PurseNotFoundException(Throwable cause) {
		super(cause);
	}

}
