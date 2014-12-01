package com.andrew.safronov.sintez.theblackjack.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.andrew.safronov.sintez.theblackjack.entity.Game;
import com.andrew.safronov.sintez.theblackjack.entity.Logs;
import com.andrew.safronov.sintez.theblackjack.entity.Purse;
import com.andrew.safronov.sintez.theblackjack.game.enums.GameActions;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-application-context.xml")
@Transactional
public class PurseRepositoryTest {

    @Autowired
    private PurseRepository purseRrpository;

    @Test
    public void savePurseWithNestedHierarchy_checkMappingsPurse() {

        Purse purse = new Purse();
        purse.setBalance(100);

        Game game = new Game();
        purse.setGame(game);

        Logs logs = new Logs();
        logs.setGame(game);
        logs.setAction(GameActions.START_GAME.getAction());
        game.getGameLogs().add(logs);

        Purse savedPurse = purseRrpository.save(purse);

        Assert.assertNotNull(savedPurse.getPurseId());
    }

    @Test
    public void savePurseWithNestedHierarchy_checkMappingsGame() {

        Purse purse = new Purse();
        purse.setBalance(100);

        Game game = new Game();
        purse.setGame(game);

        Logs logs = new Logs();
        logs.setGame(game);
        logs.setAction(GameActions.START_GAME.getAction());
        game.getGameLogs().add(logs);

        Purse savedPurse = purseRrpository.save(purse);

        Assert.assertNotNull(savedPurse.getGame().getGameId());
    }

    @Test
    public void savePurseWithNestedHierarchy_checkMappingsLogs() {

        Purse purse = new Purse();
        purse.setBalance(100);

        Game game = new Game();
        purse.setGame(game);

        Logs logs = new Logs();
        logs.setGame(game);
        logs.setAction(GameActions.START_GAME.getAction());
        game.getGameLogs().add(logs);

        Purse savedPurse = purseRrpository.save(purse);

        Assert.assertEquals(1, savedPurse.getGame().getGameLogs().size());
    }

}
