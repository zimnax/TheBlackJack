package com.andrew.safronov.sintez.theblackjack.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.andrew.safronov.sintez.theblackjack.entity.Purse;
import com.andrew.safronov.sintez.theblackjack.service.PurseService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-application-context.xml")
public class PurseServiceImplTest {

    @Autowired
    private PurseService purseService;

    
    
    @Test
    public void registerNewPurse() {

        Purse purse = new Purse();
        purse.setBalance(100);

        Purse registerNewPurse = purseService.registerNewPurse(purse);
        System.out.println(registerNewPurse.getPurseId());
    }
}
