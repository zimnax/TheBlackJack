package com.andrew.safronov.sintez.theblackjack.service;

import com.andrew.safronov.sintez.theblackjack.entity.Purse;
import com.andrew.safronov.sintez.theblackjack.game.Desk;

public interface PurseService {

	Desk registerNewPurse(Purse purse);

	Desk replenishPurse(long purseID, double coints);

	boolean isPurseExist(long purseID);

}
