package com.andrew.safronov.sintez.theblackjack.game.enums;

public enum GameActions {

	START_GAME("startGame"), DEAL_CARD("dealCard"), FINISH_GAME("finishGame"), HIT("hit"), STAND("stand"), BET("bet"), REPLENISH(
			"replenish");

	private String action;

	private GameActions(String act) {
		action = act;
	}

	public String getAction() {
		return action;
	}
}
