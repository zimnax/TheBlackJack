package com.andrew.safronov.sintez.theblackjack.game;

import java.util.Stack;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.andrew.safronov.sintez.theblackjack.entity.Purse;
import com.andrew.safronov.sintez.theblackjack.game.Card;
import com.andrew.safronov.sintez.theblackjack.game.Desk;
import com.andrew.safronov.sintez.theblackjack.game.GameKernel;
import com.andrew.safronov.sintez.theblackjack.game.enums.CardRanks;
import com.andrew.safronov.sintez.theblackjack.game.enums.CardSuits;
import com.andrew.safronov.sintez.theblackjack.game.enums.GameStatus;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-application-context.xml")
@WebAppConfiguration
public class GameKernelTest {

    @Autowired
    private GameKernel gameKernel;

    @Test
    public void initFreshCardsCheskAmount() {
        int expectedCardEmount = 52;

        Stack<Card> cards = new Stack<Card>();
        gameKernel.initFreshCards(cards);

        Assert.assertEquals(expectedCardEmount, cards.size());

    }

    @Test
    public void doHitActionWIN() {

        Desk desk = new Desk(new Purse());

        desk.getCards().push(new Card(CardSuits.SPADES, CardRanks.FOUR));
        desk.getCards().push(new Card(CardSuits.SPADES, CardRanks.THREE));

        desk.getPlayerCards().push(new Card(CardSuits.HEARTS, CardRanks.ACE));
        desk.getPlayerCards().push(new Card(CardSuits.CLUBS, CardRanks.TEN));

        desk.getDealerCards().push(new Card(CardSuits.HEARTS, CardRanks.NINE));
        desk.setHiddenDealerCard((new Card(CardSuits.CLUBS, CardRanks.TWO)));

        Desk analyzedDesk = gameKernel.analyzeCards(desk);

        Assert.assertEquals(GameStatus.WIN.getStatus(), analyzedDesk.getGameStatus());
    }

    @Test
    public void doHitActionDraw() {

        Desk desk = new Desk(new Purse());

        desk.getCards().push(new Card(CardSuits.SPADES, CardRanks.FOUR));
        desk.getCards().push(new Card(CardSuits.SPADES, CardRanks.SIX));

        desk.getPlayerCards().push(new Card(CardSuits.HEARTS, CardRanks.TEN));
        desk.getPlayerCards().push(new Card(CardSuits.CLUBS, CardRanks.SEVEN));

        desk.getDealerCards().push(new Card(CardSuits.HEARTS, CardRanks.NINE));
        desk.setHiddenDealerCard((new Card(CardSuits.CLUBS, CardRanks.TWO)));

        Desk analyzedDesk = gameKernel.analyzeCards(desk);

        gameKernel.stand(analyzedDesk);

        Assert.assertEquals(GameStatus.DRAW.getStatus(), analyzedDesk.getGameStatus());
    }

    @Test
    public void analyzeCardUserHasBlackJackAndWin() {

        Desk desk = new Desk(new Purse());

        desk.getPlayerCards().push(new Card(CardSuits.HEARTS, CardRanks.ACE));
        desk.getPlayerCards().push(new Card(CardSuits.CLUBS, CardRanks.TEN));

        desk.getDealerCards().push(new Card(CardSuits.HEARTS, CardRanks.NINE));
        desk.setHiddenDealerCard((new Card(CardSuits.CLUBS, CardRanks.TEN)));

        Desk analyzedDesk = gameKernel.analyzeCards(desk);

        Assert.assertEquals(GameStatus.WIN.getStatus(), analyzedDesk.getGameStatus());

    }

    @Test
    public void analyzeCardPlayerCheckGameStatusDraw() {

        Desk desk = new Desk(new Purse());

        desk.getPlayerCards().push(new Card(CardSuits.HEARTS, CardRanks.ACE));
        desk.getPlayerCards().push(new Card(CardSuits.CLUBS, CardRanks.TEN));

        desk.getDealerCards().push(new Card(CardSuits.HEARTS, CardRanks.ACE));
        desk.setHiddenDealerCard((new Card(CardSuits.CLUBS, CardRanks.TEN)));

        Desk analyzedDesk = gameKernel.analyzeCards(desk);

        Assert.assertEquals(GameStatus.DRAW.getStatus(), analyzedDesk.getGameStatus());
    }

    @Test
    public void analyzeCardPlayerCheckStatusPending() {

        Desk desk = new Desk(new Purse());

        desk.getCards().push(new Card(CardSuits.SPADES, CardRanks.EIGHT));
        desk.getCards().push(new Card(CardSuits.SPADES, CardRanks.ACE));

        desk.getPlayerCards().push(new Card(CardSuits.HEARTS, CardRanks.EIGHT));
        desk.getPlayerCards().push(new Card(CardSuits.CLUBS, CardRanks.EIGHT));

        desk.getDealerCards().push(new Card(CardSuits.HEARTS, CardRanks.TWO));
        desk.setHiddenDealerCard((new Card(CardSuits.CLUBS, CardRanks.TEN)));

        Desk analyzedDesk = gameKernel.analyzeCards(desk);

        Assert.assertEquals(GameStatus.PENDING.getStatus(), analyzedDesk.getGameStatus());
    }

    @Test
    public void analyzeCardPlayerStandAndLoose() {

        Desk desk = new Desk(new Purse());

        desk.getCards().push(new Card(CardSuits.SPADES, CardRanks.EIGHT));
        desk.getCards().push(new Card(CardSuits.SPADES, CardRanks.ACE));

        desk.getPlayerCards().push(new Card(CardSuits.HEARTS, CardRanks.EIGHT));
        desk.getPlayerCards().push(new Card(CardSuits.CLUBS, CardRanks.EIGHT));

        desk.getDealerCards().push(new Card(CardSuits.HEARTS, CardRanks.TWO));
        desk.setHiddenDealerCard((new Card(CardSuits.CLUBS, CardRanks.TEN)));

        Desk analyzedDesk = gameKernel.analyzeCards(desk);

        gameKernel.hit(analyzedDesk);
        gameKernel.hit(analyzedDesk);

        Assert.assertEquals(GameStatus.LOOSE.getStatus(), analyzedDesk.getGameStatus());
    }

    @Test
    public void analyzeCardPlayerStandAndDraw() {

        Desk desk = new Desk(new Purse());

        desk.getCards().push(new Card(CardSuits.SPADES, CardRanks.NINE));
        desk.getCards().push(new Card(CardSuits.SPADES, CardRanks.FIVE));

        desk.getPlayerCards().push(new Card(CardSuits.HEARTS, CardRanks.EIGHT));
        desk.getPlayerCards().push(new Card(CardSuits.CLUBS, CardRanks.EIGHT));

        desk.getDealerCards().push(new Card(CardSuits.HEARTS, CardRanks.TWO));
        desk.setHiddenDealerCard((new Card(CardSuits.CLUBS, CardRanks.TEN)));

        Desk analyzedDesk = gameKernel.analyzeCards(desk);
        gameKernel.hit(analyzedDesk);

        Assert.assertEquals(GameStatus.DRAW.getStatus(), analyzedDesk.getGameStatus());
    }

}
