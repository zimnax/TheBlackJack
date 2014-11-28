package com.andrew.safronov.sintez.theblackjack.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-application-context.xml")
@WebAppConfiguration
public class AppControllerTest {

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		AppController instance = new AppController();
		mockMvc = MockMvcBuilders.standaloneSetup(instance).build();
	}

	private MockMvc mockMvc;

	@Test
	public void getShopInJSON() throws Exception {
		MockHttpServletRequestBuilder getRequest = get("/user/sintez").accept(MediaType.ALL);
		ResultActions results = mockMvc.perform(getRequest);
		results.andExpect(status().isOk());
		results.andExpect(content().contentType(MediaType.APPLICATION_JSON));
		results.andExpect(jsonPath("$.balance").value(100));
	}

}
