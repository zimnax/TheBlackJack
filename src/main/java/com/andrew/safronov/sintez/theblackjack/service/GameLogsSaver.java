package com.andrew.safronov.sintez.theblackjack.service;

import com.andrew.safronov.sintez.theblackjack.entity.Game;
import com.andrew.safronov.sintez.theblackjack.entity.Logs;
import com.andrew.safronov.sintez.theblackjack.entity.Purse;
import com.andrew.safronov.sintez.theblackjack.game.Desk;
import com.andrew.safronov.sintez.theblackjack.game.enums.GameParticipants;

public interface GameLogsSaver {

	Logs saveStartGameAction(Game game);

	Logs saveBetAction(Purse purse, int bet);

	Logs saveReplenishAction(Purse currentPurse, double coints);

	Logs saveDealCardsAction(Desk desk, GameParticipants participant);

	Logs saveHitAction(Desk desk, GameParticipants participant);

	Logs saveStandAction(Desk desk);

}
