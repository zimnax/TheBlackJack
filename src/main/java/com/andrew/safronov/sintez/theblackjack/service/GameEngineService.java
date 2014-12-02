package com.andrew.safronov.sintez.theblackjack.service;

import com.andrew.safronov.sintez.theblackjack.game.Desk;

public interface GameEngineService {

	Desk dealCards(long purseId);

	Desk doBet(long purseId, int coins);

	Desk doHit(long purseId);

	Desk doStand(long purseId);

}
