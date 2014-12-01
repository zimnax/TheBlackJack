package com.andrew.safronov.sintez.theblackjack.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.andrew.safronov.sintez.theblackjack.exception.constants.ExceptionConstants;
import com.andrew.safronov.sintez.theblackjack.service.PurseService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-application-context.xml")
@WebAppConfiguration
public class PlayerControllerTest {

    private static final double DAFAULT_BUDGET_VALUE = 100;

    @Autowired
    private PurseService purseService;

    @Autowired
    private PlayerController playerControllerInstance;

    private MockMvc mockMvcPlayerController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvcPlayerController = MockMvcBuilders.standaloneSetup(playerControllerInstance).build();
    }

    @Test
    public void registerDefaultPlayerStatusIsOk() throws Exception {

        MockHttpServletRequestBuilder initPlayerResult = get("/purse/initDefaultPlayer").accept(MediaType.ALL);
        ResultActions results = mockMvcPlayerController.perform(initPlayerResult);

        results.andExpect(status().isOk());
    }

    @Test
    public void registerDefaultPlayerReturnJSON() throws Exception {

        MockHttpServletRequestBuilder initPlayerResult = get("/purse/initDefaultPlayer").accept(MediaType.ALL);
        ResultActions results = mockMvcPlayerController.perform(initPlayerResult);

        results.andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void registerDefaultPlayerCheckBalance() throws Exception {

        MockHttpServletRequestBuilder initPlayerResult = get("/purse/initDefaultPlayer").accept(MediaType.ALL);
        ResultActions results = mockMvcPlayerController.perform(initPlayerResult);

        results.andExpect(jsonPath("$.purse.balance").value(DAFAULT_BUDGET_VALUE));
    }

    @Test
    public void registerSpecialPlayerStatusIsOk() throws Exception {

        MockHttpServletRequestBuilder initPlayerResult = get("/purse/initSpecialPlayer/888").accept(MediaType.ALL);
        ResultActions results = mockMvcPlayerController.perform(initPlayerResult);

        results.andExpect(status().isOk());
    }

    @Test
    public void registerSpecialPlayerReturnJSON() throws Exception {

        MockHttpServletRequestBuilder initPlayerResult = get("/purse/initSpecialPlayer/888").accept(MediaType.ALL);
        ResultActions results = mockMvcPlayerController.perform(initPlayerResult);

        results.andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void registerSpecialPlayerCheckBalance() throws Exception {
        String randomSum = "888";

        MockHttpServletRequestBuilder initPlayerResult = get("/purse/initSpecialPlayer/" + randomSum).accept(
                MediaType.ALL);
        ResultActions results = mockMvcPlayerController.perform(initPlayerResult);

        results.andExpect(jsonPath("$.purse.balance").value(Double.parseDouble(randomSum)));
    }

    @Test
    public void replenishPurseSucess() throws Exception {

        get("/purse/initDefaultPlayer").accept(MediaType.ALL);
        String firsDatabaseID = "1";
        String replenishCoinsValue = "900";

        MockHttpServletRequestBuilder replenishPurseResult = get(
                "/purse/replenish/" + firsDatabaseID + "/" + replenishCoinsValue).accept(MediaType.ALL);
        ResultActions results = mockMvcPlayerController.perform(replenishPurseResult);

        results.andExpect(jsonPath("$.purse.balance").value(1000d));
    }

    @Test
    public void replenishPurseFail() throws Exception {

        get("/purse/initDefaultPlayer").accept(MediaType.ALL);
        String unexistingDatabaseID = "976";
        String replenishCoinsValue = "800";

        MockHttpServletRequestBuilder replenishPurseResult = get(
                "/purse/replenish/" + unexistingDatabaseID + "/" + replenishCoinsValue).accept(MediaType.ALL);
        ResultActions results = mockMvcPlayerController.perform(replenishPurseResult);

        results.andExpect(jsonPath("$.ex").value(ExceptionConstants.PURSE_NOT_FOUND_EXCEPTION));
    }

}
