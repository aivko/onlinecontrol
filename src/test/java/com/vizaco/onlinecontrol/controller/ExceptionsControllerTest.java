package com.vizaco.onlinecontrol.controller;

import com.vizaco.onlinecontrol.exceptions.CustomGenericException;
import com.vizaco.onlinecontrol.model.User;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created by super on 3/10/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:spring/application-context.xml", "classpath:spring/mvc-core-config.xml"})
public class ExceptionsControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        this.mockMvc = webAppContextSetup(this.wac).build();
    }

    @Test
    @Ignore
    public void handleExceptionTest() throws Exception {

        ResultActions resultActions = mockMvc.perform(get("/test/exceptionController"));
        resultActions.andExpect(view().name("errors/exception"));
        resultActions.andExpect(forwardedUrl("/WEB-INF/jsp/errors/exception.jsp"));
        resultActions.andExpect(model().attributeExists("exception"));

    }

}
