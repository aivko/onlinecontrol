package com.vizaco.onlinecontrol.controller;

import com.vizaco.onlinecontrol.exceptions.CustomGenericException;
import com.vizaco.onlinecontrol.model.User;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Created by super on 3/10/15.
 */
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/application-context.xml", "classpath:spring/mvc-core-config.xml"})
public class MainControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Autowired
    private MainController mainController;

    @Before
    public void setUp() throws Exception {
//        this.mockMvc = webAppContextSetup(this.wac).alwaysExpect(status().isOk()).build();
        mockMvc = MockMvcBuilders.standaloneSetup(mainController).build();
    }

    @Test
    public void startTest() throws Exception {

        ResultActions resultActions = mockMvc.perform(get("/"));
        resultActions.andExpect(view().name("index"));
        resultActions.andExpect(forwardedUrl("index"));

    }

    @Test
    public void accessDeniedWithPrincipalTest() throws Exception {

        User user = new User("login1", "password1", "firstName1", "lastName1", "middleName1", null, null);
        user.setUserId(1L);
        TestingAuthenticationToken principal = new TestingAuthenticationToken(user, null);

        String expectedErrorMsg = "Hi " + principal.getName() + ", you do not have permission to access this page!";
        String expectedErrorCode = "403";

        MockHttpServletRequestBuilder requestBuilder = get("/exception/403").principal(principal);

        try {
            ResultActions resultActions = mockMvc.perform(requestBuilder);
        }catch (Exception ex){
            String errorCode = ((CustomGenericException) ex.getCause()).getErrorCode();
            String errorMsg = ((CustomGenericException) ex.getCause()).getErrorMsg();
            assertEquals(expectedErrorCode, errorCode);
            assertEquals(expectedErrorMsg, errorMsg);
        }

    }

    @Test
    public void accessDeniedWithoutPrincipalTest() throws Exception {

        String expectedErrorMsg = "You do not have permission to access this page!";
        String expectedErrorCode = "403";

        MockHttpServletRequestBuilder requestBuilder = get("/exception/403");

        try {
            ResultActions resultActions = mockMvc.perform(requestBuilder);
        }catch (Exception ex){
            String errorCode = ((CustomGenericException) ex.getCause()).getErrorCode();
            String errorMsg = ((CustomGenericException) ex.getCause()).getErrorMsg();
            assertEquals(expectedErrorCode, errorCode);
            assertEquals(expectedErrorMsg, errorMsg);
        }

    }

//    @Test
//    public void crushWithPrincipalTest() throws Exception {
//
//        User user = new User("login1", "password1", "firstName1", "lastName1", "middleName1", null, null);
//        user.setUserId(1L);
//        TestingAuthenticationToken principal = new TestingAuthenticationToken(user, null);
//
//        String expectedErrorMsg = "Hi " + principal.getName() + ", this page not found!";
//        String expectedErrorCode = "404";
//
//        MockHttpServletRequestBuilder requestBuilder = get("/**").principal(principal);
//
//        try {
//            ResultActions resultActions = mockMvc.perform(requestBuilder);
//        }catch (Exception ex){
//            String errorCode = ((CustomGenericException) ex.getCause()).getErrorCode();
//            String errorMsg = ((CustomGenericException) ex.getCause()).getErrorMsg();
//            assertEquals(expectedErrorCode, errorCode);
//            assertEquals(expectedErrorMsg, errorMsg);
//        }
//
//    }
//
//    @Test
//    public void crushWithoutPrincipalTest() throws Exception {
//
//        String expectedErrorMsg = "Page not found!";
//        String expectedErrorCode = "404";
//
//        MockHttpServletRequestBuilder requestBuilder = get("/**");
//
//        try {
//            ResultActions resultActions = mockMvc.perform(requestBuilder);
//        }catch (Exception ex){
//            String errorCode = ((CustomGenericException) ex.getCause()).getErrorCode();
//            String errorMsg = ((CustomGenericException) ex.getCause()).getErrorMsg();
//            assertEquals(expectedErrorCode, errorCode);
//            assertEquals(expectedErrorMsg, errorMsg);
//        }
//
//    }

}
