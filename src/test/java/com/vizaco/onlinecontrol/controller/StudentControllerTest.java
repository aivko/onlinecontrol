package com.vizaco.onlinecontrol.controller;

import com.vizaco.onlinecontrol.exceptions.CustomGenericException;
import com.vizaco.onlinecontrol.model.Student;
import com.vizaco.onlinecontrol.model.User;
import com.vizaco.onlinecontrol.service.StudentService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
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
public class StudentControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Autowired
    private StudentService mockStudentService;

    @Before
    public void setUp() throws Exception {

        mockStudentService = mock(StudentService.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new StudentController(mockStudentService)).build();
    }

    @Test
    public void initFindFormTest() throws Exception {

        ResultActions resultActions = mockMvc.perform(get("/students/find"));
        resultActions.andExpect(view().name("students/findStudents"));
        resultActions.andExpect(forwardedUrl("students/findStudents"));
        resultActions.andExpect(redirectedUrl(null));
        resultActions.andExpect(model().attributeExists("student"));

    }

    @Test
    public void processFindForm1InDbTest() throws Exception {

        Student student1 = new Student("firstName1", "lastName1", "middleName1", null, null, null, null);
        student1.setStudentId(1L);
        List<Student> resultList  = Arrays.asList(student1);
        when(mockStudentService.findStudentByLastName("lastName1")).thenReturn(resultList);

        Student modelStudent = new Student("firstName1", "lastName1", "middleName1", null, null, null, null);
        modelStudent.setStudentId(1L);

        MockHttpServletRequestBuilder requestBuilder = get("/students").flashAttr("student", modelStudent);

        ResultActions resultActions = mockMvc.perform(requestBuilder);
        resultActions.andExpect(status().is3xxRedirection());
        resultActions.andExpect(redirectedUrl("/students/1"));
        resultActions.andExpect(view().name("redirect:/students/1"));

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

    @Test
    public void processFindFormZeroInDbTest() throws Exception {

        List<Student> resultList  = Collections.emptyList();
        when(mockStudentService.findStudentByLastName("lastName1")).thenReturn(resultList);

        Student modelStudent = new Student("firstName1", "lastName1", "middleName1", null, null, null, null);
        modelStudent.setStudentId(1L);

        MockHttpServletRequestBuilder requestBuilder = get("/students").flashAttr("student", modelStudent);

        ResultActions resultActions = mockMvc.perform(requestBuilder);
        resultActions.andExpect(view().name("students/findStudents"));
        resultActions.andExpect(forwardedUrl("students/findStudents"));
        resultActions.andExpect(redirectedUrl(null));

    }

    @Test
    public void processUpdateUserFormStudentTest() throws Exception {


        Student modelStudent = new Student("firstName1", "lastName1", "middleName1", null, null, null, null);
        modelStudent.setStudentId(1L);

        doNothing().when(mockStudentService).saveStudent(modelStudent);

        MockHttpServletRequestBuilder requestBuilder = put("/students/1/edit").flashAttr("student", modelStudent);

        ResultActions resultActions = mockMvc.perform(requestBuilder);
        resultActions.andExpect(status().is3xxRedirection());
        resultActions.andExpect(redirectedUrl("/students/1"));
        resultActions.andExpect(view().name("redirect:/students/{studentId}"));

        verify(mockStudentService).saveStudent(modelStudent);

    }

    @Test
    public void processUpdateUserFormNullStudentTest() throws Exception {

        Student modelStudent = null;

        doNothing().when(mockStudentService).saveStudent(modelStudent);

        MockHttpServletRequestBuilder requestBuilder = put("/students/1/edit");

        ResultActions resultActions = mockMvc.perform(requestBuilder);
        resultActions.andExpect(status().is3xxRedirection());
        resultActions.andExpect(redirectedUrl("/students/1"));
        resultActions.andExpect(view().name("redirect:/students/{studentId}"));

//        verify(mockStudentService).saveStudent(modelStudent);

    }

    @Test
    public void showStudentTest() throws Exception {

        Student modelStudent = new Student("firstName1", "lastName1", "middleName1", null, null, null, null);
        modelStudent.setStudentId(1L);

        when(mockStudentService.findStudentById(1L)).thenReturn(modelStudent);

        MockHttpServletRequestBuilder requestBuilder = get("/students/1").flashAttr("studentId", 1L);

        ResultActions resultActions = mockMvc.perform(requestBuilder);

        resultActions.andExpect(model().attributeExists("student"));
        resultActions.andExpect(view().name("students/studentDetails"));

        Student student = (Student) resultActions.andReturn().getModelAndView().getModel().get("student");
        assertEquals(modelStudent, student);

    }

}
