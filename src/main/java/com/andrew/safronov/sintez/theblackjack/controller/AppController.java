package com.andrew.safronov.sintez.theblackjack.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.andrew.safronov.sintez.theblackjack.model.entity.User;

@Controller
@RequestMapping("/user")
public class AppController {

    @RequestMapping(value = "{name}", method = RequestMethod.GET)
    public @ResponseBody User getShopInJSON() {

        System.out.println("JORICK---------------------------LABAn");

        User user = new User();
        user.setName("Andrew");
        return user;
    }

}