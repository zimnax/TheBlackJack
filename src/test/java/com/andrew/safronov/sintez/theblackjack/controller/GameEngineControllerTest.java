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
import com.andrew.safronov.sintez.theblackjack.service.GameEngineService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-application-context.xml")
@WebAppConfiguration
public class GameEngineControllerTest {

    private static final String randomBetSum = "50";

    @Autowired
    private GameEngineService gameEngineService;

    @Autowired
    private GameEngineController gameEngineServiceInstance;

    @Autowired
    private PlayerController playerControllerInstance;

    private MockMvc mockMvcGameEngineController;

    private MockMvc mockMvcPlayerController;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvcGameEngineController = MockMvcBuilders.standaloneSetup(gameEngineServiceInstance).build();
        mockMvcPlayerController = MockMvcBuilders.standaloneSetup(playerControllerInstance).build();
    }

    @Test
    public void doBetStatusIsOk() throws Exception {

        MockHttpServletRequestBuilder initPlayerResult = get("/purse/initDefaultPlayer").accept(MediaType.ALL);
        mockMvcPlayerController.perform(initPlayerResult);

        MockHttpServletRequestBuilder doBetResult = get("/action/1/bet/" + randomBetSum).accept(MediaType.ALL);
        ResultActions betResults = mockMvcGameEngineController.perform(doBetResult);

        betResults.andExpect(status().isOk());
    }

    @Test
    public void doBetReturnJSON() throws Exception {

        MockHttpServletRequestBuilder initPlayerResult = get("/purse/initDefaultPlayer").accept(MediaType.ALL);
        mockMvcPlayerController.perform(initPlayerResult);

        MockHttpServletRequestBuilder doBetResult = get("/action/1/bet/" + randomBetSum).accept(MediaType.ALL);
        ResultActions betResults = mockMvcGameEngineController.perform(doBetResult);

        betResults.andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void doBetPurseNotFoundExceptionReturnJson() throws Exception {

        MockHttpServletRequestBuilder initPlayerResult = get("/purse/initDefaultPlayer").accept(MediaType.ALL);
        mockMvcPlayerController.perform(initPlayerResult);

        MockHttpServletRequestBuilder doBetResult = get("/action/666/deal/").accept(MediaType.ALL);
        ResultActions hitResults = mockMvcGameEngineController.perform(doBetResult);

        hitResults.andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void doBetPurseNotFoundExceptionCheckMessage() throws Exception {

        MockHttpServletRequestBuilder initPlayerResult = get("/purse/initDefaultPlayer").accept(MediaType.ALL);
        mockMvcPlayerController.perform(initPlayerResult);

        MockHttpServletRequestBuilder doBetResult = get("/action/666/deal/").accept(MediaType.ALL);
        ResultActions hitResults = mockMvcGameEngineController.perform(doBetResult);

        hitResults.andExpect(jsonPath("$.ex").value(ExceptionConstants.PURSE_NOT_FOUND_EXCEPTION));
    }

    @Test
    public void dealStatusIsOk() throws Exception {

        MockHttpServletRequestBuilder initPlayerResult = get("/purse/initDefaultPlayer").accept(MediaType.ALL);
        mockMvcPlayerController.perform(initPlayerResult);

        MockHttpServletRequestBuilder doBetResult = get("/action/1/deal").accept(MediaType.ALL);
        ResultActions dealResults = mockMvcGameEngineController.perform(doBetResult);

        dealResults.andExpect(status().isOk());
    }

    @Test
    public void doDealReturnJSON() throws Exception {

        MockHttpServletRequestBuilder initPlayerResult = get("/purse/initDefaultPlayer").accept(MediaType.ALL);
        mockMvcPlayerController.perform(initPlayerResult);

        MockHttpServletRequestBuilder doBetResult = get("/action/1/deal/").accept(MediaType.ALL);
        ResultActions dealResults = mockMvcGameEngineController.perform(doBetResult);

        dealResults.andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void doDealPurseNotFountExceptionReturnJson() throws Exception {

        MockHttpServletRequestBuilder initPlayerResult = get("/purse/initDefaultPlayer").accept(MediaType.ALL);
        mockMvcPlayerController.perform(initPlayerResult);

        MockHttpServletRequestBuilder doBetResult = get("/action/666/deal/").accept(MediaType.ALL);
        ResultActions dealResults = mockMvcGameEngineController.perform(doBetResult);

        dealResults.andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void doDealPurseNotFountExceptionCheckMessage() throws Exception {

        MockHttpServletRequestBuilder initPlayerResult = get("/purse/initDefaultPlayer").accept(MediaType.ALL);
        mockMvcPlayerController.perform(initPlayerResult);

        MockHttpServletRequestBuilder doBetResult = get("/action/666/deal/").accept(MediaType.ALL);
        ResultActions dealResults = mockMvcGameEngineController.perform(doBetResult);

        dealResults.andExpect(jsonPath("$.ex").value(ExceptionConstants.PURSE_NOT_FOUND_EXCEPTION));
    }

    @Test
    public void doHitPurseNotFountExceptionCheckMessage() throws Exception {

        MockHttpServletRequestBuilder initPlayerResult = get("/purse/initDefaultPlayer").accept(MediaType.ALL);
        mockMvcPlayerController.perform(initPlayerResult);

        MockHttpServletRequestBuilder doBetResult = get("/action/666/hit/").accept(MediaType.ALL);
        ResultActions dealResults = mockMvcGameEngineController.perform(doBetResult);

        dealResults.andExpect(jsonPath("$.ex").value(ExceptionConstants.PURSE_NOT_FOUND_EXCEPTION));
    }

    @Test
    public void doStandPurseNotFountExceptionCheckMessage() throws Exception {

        MockHttpServletRequestBuilder initPlayerResult = get("/purse/initDefaultPlayer").accept(MediaType.ALL);
        mockMvcPlayerController.perform(initPlayerResult);

        MockHttpServletRequestBuilder doBetResult = get("/action/666/stand/").accept(MediaType.ALL);
        ResultActions dealResults = mockMvcGameEngineController.perform(doBetResult);

        dealResults.andExpect(jsonPath("$.ex").value(ExceptionConstants.PURSE_NOT_FOUND_EXCEPTION));
    }

}
