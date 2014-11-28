package com.andrew.safronov.sintez.theblackjack.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.andrew.safronov.sintez.theblackjack.entity.Purse;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-application-context.xml")
public class PurseRepositoryTest {

    @Autowired
    PurseRepository purseRepository;

    @Test
    public void saveOnePurseInDatabase() {
        Purse purse = new Purse();
        purse.setBalance(100);
        Purse savedPurse = purseRepository.save(purse);

        Assert.assertEquals(purse.getBalance(), savedPurse.getBalance());
    }

}
