package com.andrew.safronov.sintez.theblackjack.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application-context.xml")
@WebAppConfiguration
public class AppControllerTest {

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }
    
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    // @Autowired
    // private TodoService todoServiceMock;

    @Test
    public void getShopInJSON() throws Exception {
//        this.mockMvc.perform(get("/user/andew").accept(MediaType.APPLICATION_JSON)).andExpect(status().is(404));
        // this.mockMvc.perform(get("/foo").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
        // .andExpect(content().mimeType(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$.name").value("Lee"));
        
        this.mockMvc.perform(get("/user/andew").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().is(404))
        .andExpect(jsonPath("$.name").value("Andrew"));

    }

}
