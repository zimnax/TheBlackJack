package com.andrew.safronov.sintez.theblackjack.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.andrew.safronov.sintez.theblackjack.entity.Game;
import com.andrew.safronov.sintez.theblackjack.entity.Purse;
import com.andrew.safronov.sintez.theblackjack.exception.BetAlreadyExistException;
import com.andrew.safronov.sintez.theblackjack.game.Card;
import com.andrew.safronov.sintez.theblackjack.game.Desk;
import com.andrew.safronov.sintez.theblackjack.game.GameKernel;
import com.andrew.safronov.sintez.theblackjack.game.enums.CardRanks;
import com.andrew.safronov.sintez.theblackjack.game.enums.CardSuits;
import com.andrew.safronov.sintez.theblackjack.game.enums.GameStatus;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-application-context.xml")
public class GameEngineServiceImplTest {

    @Autowired
    private GameEngineServiceImpl gameEngineServiceImpl;

    @Autowired
    private PurseServiceImpl purseServise;

    @Autowired
    private GameKernel gameKernel;

    @Test
    public void dealCardsCheckPlayerCardEmount() {
        Purse purse = new Purse();
        purse.setBalance(100);

        Desk desk = purseServise.registerNewPurse(purse);
        desk.setBet(50);
        Desk deskWithCards = gameEngineServiceImpl.dealCards(desk.getPurse().getPurseId());

        Assert.assertEquals(2, deskWithCards.getPlayerCards().size());
    }

    @Test
    public void dealCardsCheckDealerCardEmount() {
        Purse purse = new Purse();
        purse.setBalance(100);

        Desk desk = purseServise.registerNewPurse(purse);
        desk.setBet(50);
        Desk deskWithCards = gameEngineServiceImpl.dealCards(desk.getPurse().getPurseId());

        Assert.assertEquals(1d, deskWithCards.getDealerCards().size(), 1);
    }

    @Test
    public void dealCardsCheckAmountOfRemainingCards() {// TODO: rewrite
        int amountCardsAfterDeal = 48;
        Purse purse = new Purse();
        purse.setBalance(100);

        Desk desk = purseServise.registerNewPurse(purse);
        desk.setBet(50);
        Desk deskWithCards = gameEngineServiceImpl.dealCards(desk.getPurse().getPurseId());

        Assert.assertEquals(amountCardsAfterDeal, deskWithCards.getCards().size());
    }

    @Test
    public void dealCardsCheckActionLogs() {
        int amountOfLogsAfterDeal = 5;
        Purse purse = new Purse();
        purse.setBalance(100);

        Desk desk = purseServise.registerNewPurse(purse);
        desk.setBet(50);
        Desk deskWithCards = gameEngineServiceImpl.dealCards(desk.getPurse().getPurseId());

        Assert.assertEquals(amountOfLogsAfterDeal, deskWithCards.getPurse().getGame().getGameLogs().size());
    }

    @Test
    public void doBetCheckSum() {
        int betCoins = 50;

        Purse purse = new Purse();
        purse.setBalance(100);

        Desk desk = purseServise.registerNewPurse(purse);
        Desk deskWithBet = gameEngineServiceImpl.doBet(desk.getPurse().getPurseId(), betCoins);

        Assert.assertEquals(betCoins, deskWithBet.getBet());
    }

    @Test
    public void initialDealCheckPlayerCardAmount() {
        Purse purse = new Purse();
        Desk desk = new Desk(purse);
        Game game = new Game();
        purse.setGame(game);

        gameKernel.initFreshCards(desk.getCards());
        Desk initialDesk = gameEngineServiceImpl.initialDeal(desk);

        Assert.assertEquals(2, initialDesk.getPlayerCards().size());
    }

    @Test(expected = BetAlreadyExistException.class)
    public void doBetBetAlreadyExist() {
        int betCoins = 50;

        Purse purse = new Purse();
        purse.setBalance(100);

        Desk desk = purseServise.registerNewPurse(purse);
        desk.setBet(50);
        Desk deskWithBet = gameEngineServiceImpl.doBet(desk.getPurse().getPurseId(), betCoins);
        gameEngineServiceImpl.doBet(deskWithBet.getPurse().getPurseId(), betCoins);

        Assert.assertEquals(betCoins, deskWithBet.getBet());
    }

    @Test
    public void doHitCheckCardsAmount() {

        Purse purse = new Purse();
        purse.setBalance(100);

        Desk savedDesk = purseServise.registerNewPurse(purse);
        savedDesk.setBet(50);

        gameKernel.initFreshCards(savedDesk.getCards());
        Desk initialDeal = gameEngineServiceImpl.initialDeal(savedDesk);
        Desk doHitDesk = gameEngineServiceImpl.doHit(initialDeal.getPurse().getPurseId());

        Assert.assertEquals(3, doHitDesk.getPlayerCards().size());
    }

    @Test
    public void doHitCheckCardSum() {

        Purse purse = new Purse();
        purse.setBalance(100);

        Desk savedDesk = purseServise.registerNewPurse(purse);
        savedDesk.setBet(50);

        savedDesk.getCards().add(new Card(CardSuits.DIAMONDS, CardRanks.TWO));
        savedDesk.getCards().add(new Card(CardSuits.DIAMONDS, CardRanks.ACE));

        savedDesk.getPlayerCards().add(new Card(CardSuits.HEARTS, CardRanks.ACE));
        savedDesk.getPlayerCards().add(new Card(CardSuits.HEARTS, CardRanks.SEVEN));

        savedDesk.getDealerCards().add(new Card(CardSuits.SPADES, CardRanks.NINE));
        savedDesk.setHiddenDealerCard(new Card(CardSuits.DIAMONDS, CardRanks.NINE));

        Desk doHitDesk = gameEngineServiceImpl.doHit(savedDesk.getPurse().getPurseId());

        Assert.assertEquals(19, doHitDesk.getPlayerPoints(), 1);
    }

    @Test
    public void doHitAndStandCheckSum() {

        Purse purse = new Purse();
        purse.setBalance(100);

        Desk savedDesk = purseServise.registerNewPurse(purse);
        savedDesk.setBet(50);

        savedDesk.getCards().add(new Card(CardSuits.DIAMONDS, CardRanks.TWO));
        savedDesk.getCards().add(new Card(CardSuits.DIAMONDS, CardRanks.ACE));

        savedDesk.getPlayerCards().add(new Card(CardSuits.HEARTS, CardRanks.ACE));
        savedDesk.getPlayerCards().add(new Card(CardSuits.HEARTS, CardRanks.SEVEN));

        savedDesk.getDealerCards().add(new Card(CardSuits.SPADES, CardRanks.NINE));
        savedDesk.setHiddenDealerCard(new Card(CardSuits.DIAMONDS, CardRanks.NINE));

        Desk doHitDesk = gameEngineServiceImpl.doHit(savedDesk.getPurse().getPurseId());
        Desk doStand = gameEngineServiceImpl.doStand(doHitDesk.getPurse().getPurseId());

        Assert.assertEquals(GameStatus.WIN.getStatus(), doStand.getGameStatus());
    }

}
