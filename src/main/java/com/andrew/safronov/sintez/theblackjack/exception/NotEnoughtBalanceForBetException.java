package com.andrew.safronov.sintez.theblackjack.exception;

public class NotEnoughtBalanceForBetException extends BlackjackGameException {

    private static final long serialVersionUID = 8221732035436425287L;

    public NotEnoughtBalanceForBetException() {
    }

    public NotEnoughtBalanceForBetException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotEnoughtBalanceForBetException(String message) {
        super(message);
    }

    public NotEnoughtBalanceForBetException(Throwable cause) {
        super(cause);
    }

}
