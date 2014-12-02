package com.andrew.safronov.sintez.theblackjack.game.enums;

public enum GameStatus {

	PENDING("pending"), WIN("win"), LOOSE("loose"), DRAW("draw");

	private String status;

	private GameStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

}
