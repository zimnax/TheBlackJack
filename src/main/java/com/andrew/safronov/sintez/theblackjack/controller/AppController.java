package com.andrew.safronov.sintez.theblackjack.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.andrew.safronov.sintez.theblackjack.entity.Purse;

@Controller
@RequestMapping("/user")
public class AppController {
    
    

    @RequestMapping(value = "{name}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody Purse getShopInJSON(@PathVariable String name) {

        Purse purse = new Purse();
        purse.setBalance(100);
        return purse;
    }

}
