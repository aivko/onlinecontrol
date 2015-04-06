package com.vizaco.onlinecontrol.controller;

import com.vizaco.onlinecontrol.model.Student;
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
    public void processFindForm2InDbTest() throws Exception {

        Student student1 = new Student("firstName1", "lastName1", "middleName1", null, null, null, null);
        student1.setStudentId(1L);
        Student student2 = new Student("firstName2", "lastName1", "middleName2", null, null, null, null);
        student2.setStudentId(2L);
        List<Student> resultList  = Arrays.asList(student1, student2);
        when(mockStudentService.findStudentByLastName("lastName1")).thenReturn(resultList);

        Student modelStudent = new Student("firstName1", "lastName1", "middleName1", null, null, null, null);
        modelStudent.setStudentId(1L);

        MockHttpServletRequestBuilder requestBuilder = get("/students").flashAttr("student", modelStudent);

        ResultActions resultActions = mockMvc.perform(requestBuilder);
        resultActions.andExpect(view().name("students/students"));
        resultActions.andExpect(forwardedUrl("students/students"));
        resultActions.andExpect(redirectedUrl(null));
        resultActions.andExpect(model().attributeExists("students"));

        List<Student> students = (List<Student>) resultActions.andReturn().getModelAndView().getModel().get("students");
        assertEquals(2, students.size());

    }

}
