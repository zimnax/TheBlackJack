package com.andrew.safronov.sintez.theblackjack.game.enums;

public enum GameParticipants {

	PLAYER("player"), DEALER("dealer");

	private String participant;

	GameParticipants(String participant) {
		this.participant = participant;
	}

	public String getParticipant() {
		return participant;
	}

}
