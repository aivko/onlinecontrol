package com.vizaco.onlinecontrol.controller;

import com.vizaco.onlinecontrol.model.Student;
import com.vizaco.onlinecontrol.model.User;
import com.vizaco.onlinecontrol.service.StudentService;
import com.vizaco.onlinecontrol.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by super on 3/10/15.
 */
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/application-context.xml", "classpath:spring/mvc-core-config.xml"})
public class UserControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Autowired
    private StudentService mockStudentService;

    @Autowired
    private UserService mockUserService;

    @Before
    public void setUp() throws Exception {

        mockStudentService = mock(StudentService.class);
        mockUserService = mock(UserService.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new UserController(mockUserService, mockStudentService)).build();
    }

    @Test
    public void initAccountForm1InDbTest() throws Exception {

        User user1 = new User("login1", "password1", "firstName1", "lastName1", "middleName1", null, null);
        user1.setUserId(1L);
        List<User> resultList  = Arrays.asList(user1);
        when(mockUserService.getAllUsers()).thenReturn(resultList);

        User modelUser = new User("login1", "password1", "firstName1", "lastName1", "middleName1", null, null);
        modelUser.setUserId(1L);

        MockHttpServletRequestBuilder requestBuilder = get("/users");

        ResultActions resultActions = mockMvc.perform(requestBuilder);
        resultActions.andExpect(view().name("users/users"));
        resultActions.andExpect(forwardedUrl("users/users"));
        resultActions.andExpect(model().attributeExists("users"));

        List<User> users = (List<User>) resultActions.andReturn().getModelAndView().getModel().get("users");
        assertEquals(1, users.size());

    }

    @Test
    public void initAccountForm2InDbTest() throws Exception {

        User user1 = new User("login1", "password1", "firstName1", "lastName1", "middleName1", null, null);
        user1.setUserId(1L);
        User user2 = new User("login2", "password2", "firstName2", "lastName2", "middleName2", null, null);
        user2.setUserId(2L);
        List<User> resultList  = Arrays.asList(user1, user2);
        when(mockUserService.getAllUsers()).thenReturn(resultList);

        User modelUser = new User("login1", "password1", "firstName1", "lastName1", "middleName1", null, null);
        modelUser.setUserId(1L);

        MockHttpServletRequestBuilder requestBuilder = get("/users");

        ResultActions resultActions = mockMvc.perform(requestBuilder);
        resultActions.andExpect(view().name("users/users"));
        resultActions.andExpect(forwardedUrl("users/users"));
        resultActions.andExpect(model().attributeExists("users"));

        List<User> users = (List<User>) resultActions.andReturn().getModelAndView().getModel().get("users");
        assertEquals(2, users.size());

    }

}
