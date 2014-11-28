package com.andrew.safronov.sintez.theblackjack.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;
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

import com.andrew.safronov.sintez.theblackjack.entity.Purse;
import com.andrew.safronov.sintez.theblackjack.repository.PurseRepository;
import com.andrew.safronov.sintez.theblackjack.service.PurseService;
import com.andrew.safronov.sintez.theblackjack.service.impl.PurseServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application-context.xml")
@WebAppConfiguration
public class PlayerControllerTest {

//    @Autowired
//    private PurseService purseServiceMock;

//    @Autowired
//    public PurseRepository purseRepositoryMock;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        PlayerController instance = new PlayerController();
        mockMvc = MockMvcBuilders.standaloneSetup(instance).build();
    }

    public MockMvc mockMvc;

    @Test
    public void registerDefaultPlayer() throws Exception {

        Purse purse = new Purse();
        purse.setBalance(100);
        purse.setPurseId(1l);
        
        PurseService purseServiceMock = org.mockito.Mockito.mock(PurseServiceImpl.class);

        when(purseServiceMock.DO()).thenReturn(purse);
        System.out.println("HOHO");
        
        mockMvc.perform(get("/initPurse"))
        .andExpect(status().isOk());

        System.out.println("HOHO2");
        // when(purseRepositoryMock.save(any(Purse.class))).thenReturn(purse);
        //
        // when(purseServiceMock.registerNewPurse(any(Purse.class))).thenReturn(purse);

//        MockHttpServletRequestBuilder getRequest = get("/initPurse").accept(MediaType.ALL);
//        ResultActions results = mockMvc.perform(getRequest);

        // results.andExpect(status().isOk());
        // results.andExpect(content().contentType(MediaType.APPLICATION_JSON));
        // results.andExpect(jsonPath("$.balance").value(100));
    }

}
