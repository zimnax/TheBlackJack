package com.andrew.safronov.sintez.theblackjack.service.impl;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andrew.safronov.sintez.theblackjack.entity.Purse;
import com.andrew.safronov.sintez.theblackjack.exception.BetAlreadyExistException;
import com.andrew.safronov.sintez.theblackjack.exception.CardsAlreadyDealtException;
import com.andrew.safronov.sintez.theblackjack.exception.NotEnoughtBalanceForBetException;
import com.andrew.safronov.sintez.theblackjack.exception.constants.ExceptionConstants;
import com.andrew.safronov.sintez.theblackjack.game.Desk;
import com.andrew.safronov.sintez.theblackjack.game.GameKernel;
import com.andrew.safronov.sintez.theblackjack.game.enums.GameParticipants;
import com.andrew.safronov.sintez.theblackjack.repository.PurseRepository;
import com.andrew.safronov.sintez.theblackjack.service.GameEngineService;
import com.andrew.safronov.sintez.theblackjack.service.GameLogsSaver;

@Service
public class GameEngineServiceImpl implements GameEngineService {

    private static final Logger LOGGER = Logger.getLogger(GameEngineServiceImpl.class);

    public static ConcurrentMap<Long, Desk> currentSystemDesks = new ConcurrentHashMap<Long, Desk>();

    @Autowired
    private GameLogsSaver gameLogSaver;

    @Autowired
    private PurseRepository purseRepository;

    @Autowired
    private GameKernel gameKernel;

    @Override
    public Desk dealCards(long purseId) {
        LOGGER.info("Start to deal cards");
        Desk desk = currentSystemDesks.get(new Long(purseId));

        if (desk.getBet() != 0) {
            if (desk.getPlayerCards().isEmpty()) {

                gameKernel.initFreshCards(desk.getCards());
                Desk initialDesk = initialDeal(desk);
                purseRepository.save(initialDesk.getPurse());

                return gameKernel.analyzeCards(desk);
            }
            throw new CardsAlreadyDealtException(ExceptionConstants.CARDS_ALREADY_DEALT_EXCEPTION);
        }
        throw new BetAlreadyExistException(ExceptionConstants.BET_DOES_NOT_EXIST_EXCEPTION);
    }

    public Desk initialDeal(Desk desk) {
        LOGGER.info("Start to perform initial deal");
        Purse purse = desk.getPurse();

        desk.getPlayerCards().add(desk.getCards().pop());
        purse.getGame().getGameLogs().add(gameLogSaver.saveDealCardsAction(desk, GameParticipants.PLAYER));

        desk.getPlayerCards().add(desk.getCards().pop());
        purse.getGame().getGameLogs().add(gameLogSaver.saveDealCardsAction(desk, GameParticipants.PLAYER));

        desk.getDealerCards().add(desk.getCards().pop());
        purse.getGame().getGameLogs().add(gameLogSaver.saveDealCardsAction(desk, GameParticipants.DEALER));

        desk.setHiddenDealerCard(desk.getCards().pop());
        purse.getGame().getGameLogs().add(gameLogSaver.saveDealCardsAction(desk, GameParticipants.DEALER));
        return desk;

    }

    @Override
    public Desk doBet(long purseId, int coins) {
        LOGGER.info("Start to do bet action");
        Desk desk = currentSystemDesks.get(new Long(purseId));
        if (desk.getPurse().getBalance() < coins) {
            throw new NotEnoughtBalanceForBetException(ExceptionConstants.NOT_ENOUGHT_BALANCE_FOR_BET_EXCEPTION);
        } else {
            if (desk.getBet() == 0) {
                LOGGER.info("Bet = 0. Create new bet");
                desk.setBet(coins);
            } else {
                LOGGER.info("Bet already exist");
                throw new BetAlreadyExistException(ExceptionConstants.BET_ALREADY_EXIST_EXCEPTION);
            }
            Purse purse = desk.getPurse();
            purse.getGame().getGameLogs().add(gameLogSaver.saveBetAction(purse, coins));
        }
        return desk;
    }

    @Override
    public Desk doHit(long purseId) {
        LOGGER.info("Start to do hit action");
        Desk desk = currentSystemDesks.get(new Long(purseId));
        Purse purse = desk.getPurse();
        purse.getGame().getGameLogs().add(gameLogSaver.saveHitAction(desk, GameParticipants.PLAYER));
        purseRepository.save(purse);
        return gameKernel.hit(desk);
    }

    @Override
    public Desk doStand(long purseId) {
        LOGGER.info("Start to do STAND action");
        Desk desk = currentSystemDesks.get(new Long(purseId));
        Purse purse = desk.getPurse();
        purse.getGame().getGameLogs().add(gameLogSaver.saveStandAction(desk));
        purseRepository.save(purse);
        return gameKernel.stand(desk);
    }

}
