package com.vizaco.onlinecontrol.controller;

import com.vizaco.onlinecontrol.controller.securityContextMock.WithMockUser;
import com.vizaco.onlinecontrol.model.Role;
import com.vizaco.onlinecontrol.model.Student;
import com.vizaco.onlinecontrol.model.User;
import com.vizaco.onlinecontrol.service.RoleService;
import com.vizaco.onlinecontrol.service.StudentService;
import com.vizaco.onlinecontrol.service.UserService;
import com.vizaco.onlinecontrol.utils.Utils;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.test.context.support.WithSecurityContextTestExcecutionListener;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.ServletTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.Validator;
import org.springframework.web.context.WebApplicationContext;

import java.util.*;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by super on 3/10/15.
 */
@Ignore
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/application-context.xml", "classpath:spring/mvc-core-config.xml"})
@TestExecutionListeners(listeners = {ServletTestExecutionListener.class,
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        WithSecurityContextTestExcecutionListener.class})
public class UserControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Autowired
    private StudentService mockStudentService;

    @Autowired
    private UserService mockUserService;

    @Autowired
    private RoleService mockRoleService;

    @Autowired
    @Qualifier("userValidator")
    private Validator userValidator;

    private UserController userController;

    @Before
    public void setUp() throws Exception {

        mockStudentService = mock(StudentService.class);
        mockUserService = mock(UserService.class);
        userController = new UserController(mockUserService, mockStudentService, mockRoleService);
        ReflectionTestUtils.setField(userController, "userValidator", userValidator);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
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

    @Test
    public void registerGet1Role1StudentTest() throws Exception {

        Student student1 = new Student("firstName1", "lastName1", "middleName1", null, null, null, null);
        student1.setStudentId(1L);
        List<Student> resultListStudents  = Arrays.asList(student1);
        Role role1 = new Role("role1", "role1");
        role1.setRoleId(1L);
        List<Role> resultListRoles  = Arrays.asList(role1);
        when(mockRoleService.getAllRoles()).thenReturn(resultListRoles);
        when(mockStudentService.getAllStudents()).thenReturn(resultListStudents);

        MockHttpServletRequestBuilder requestBuilder = get("/registration");

        ResultActions resultActions = mockMvc.perform(requestBuilder);
        resultActions.andExpect(view().name("/users/createOrUpdateUserForm"));
        resultActions.andExpect(forwardedUrl("/users/createOrUpdateUserForm"));
        resultActions.andExpect(model().attributeExists("user"));
        resultActions.andExpect(model().attributeExists("roles"));
        resultActions.andExpect(model().attributeExists("students"));

        List<Role> roles = (List<Role>) resultActions.andReturn().getModelAndView().getModel().get("roles");
        assertEquals(1, roles.size());
        List<Student> students = (List<Student>) resultActions.andReturn().getModelAndView().getModel().get("students");
        assertEquals(1, students.size());

    }

    @Test
    public void registerGet2Roles2StudentsTest() throws Exception {

        Student student1 = new Student("firstName1", "lastName1", "middleName1", null, null, null, null);
        student1.setStudentId(1L);
        Student student2 = new Student("firstName2", "lastName2", "middleName2", null, null, null, null);
        student1.setStudentId(2L);
        List<Student> resultListStudents  = Arrays.asList(student1, student2);
        Role role1 = new Role("role1", "role1");
        role1.setRoleId(1L);
        Role role2 = new Role("role2", "role2");
        role2.setRoleId(2L);
        List<Role> resultListRoles  = Arrays.asList(role1, role2);
        when(mockRoleService.getAllRoles()).thenReturn(resultListRoles);
        when(mockStudentService.getAllStudents()).thenReturn(resultListStudents);

        MockHttpServletRequestBuilder requestBuilder = get("/registration");

        ResultActions resultActions = mockMvc.perform(requestBuilder);
        resultActions.andExpect(view().name("/users/createOrUpdateUserForm"));
        resultActions.andExpect(forwardedUrl("/users/createOrUpdateUserForm"));
        resultActions.andExpect(model().attributeExists("user"));
        resultActions.andExpect(model().attributeExists("roles"));
        resultActions.andExpect(model().attributeExists("students"));

        List<Role> roles = (List<Role>) resultActions.andReturn().getModelAndView().getModel().get("roles");
        assertEquals(resultListRoles.size(), roles.size());
        List<Student> students = (List<Student>) resultActions.andReturn().getModelAndView().getModel().get("students");
        assertEquals(resultListStudents.size(), students.size());

    }

    @Test
    @WithMockUser(id = 1L, login="user", password = "password",roles={"USER"})
    public void registerPost1Role1StudentTest() throws Exception {

        User modelUser = new User("login1", "password1", "firstName1", "lastName1", "middleName1", null, null);
        modelUser.setUserId(1L);

        MockHttpServletRequestBuilder requestBuilder = post("/registration").flashAttr("user", modelUser);

        ResultActions resultActions = mockMvc.perform(requestBuilder);
        resultActions.andExpect(status().is3xxRedirection());
        resultActions.andExpect(redirectedUrl("/users/1"));
        resultActions.andExpect(view().name("redirect:/users/1"));
        resultActions.andExpect(model().attributeExists("user"));
        User user = (User) resultActions.andReturn().getModelAndView().getModel().get("user");
        assertEquals(modelUser, user);

        verify(mockUserService).saveUser(modelUser);

    }

    @Test
    public void registerPostLoginHasErrorAnd1Role1StudentInDbTest() throws Exception {

        User modelUser = new User(null, "password1", "firstName1", "lastName1", "middleName1", null, null);
        modelUser.setUserId(1L);

        Student student1 = new Student("firstName1", "lastName1", "middleName1", null, null, null, null);
        student1.setStudentId(1L);
        List<Student> resultListStudents  = Arrays.asList(student1);
        Role role1 = new Role("role1", "role1");
        role1.setRoleId(1L);
        List<Role> resultListRoles  = Arrays.asList(role1);
        when(mockRoleService.getAllRoles()).thenReturn(resultListRoles);
        when(mockStudentService.getAllStudents()).thenReturn(resultListStudents);

        MockHttpServletRequestBuilder requestBuilder = post("/registration")
                .flashAttr("user", modelUser);

        ResultActions resultActions = mockMvc.perform(requestBuilder);
        resultActions.andExpect(view().name("/users/createOrUpdateUserForm"));
        resultActions.andExpect(forwardedUrl("/users/createOrUpdateUserForm"));
        resultActions.andExpect(model().attributeHasFieldErrors("user", "login"));

        resultActions.andExpect(model().attributeExists("roles"));
        List<Role> roles = (List<Role>) resultActions.andReturn().getModelAndView().getModel().get("roles");
        assertEquals(resultListRoles.size(), roles.size());

        resultActions.andExpect(model().attributeExists("students"));
        List<Student> students = (List<Student>) resultActions.andReturn().getModelAndView().getModel().get("students");
        assertEquals(resultListStudents.size(), students.size());

        verify(mockUserService, never()).saveUser(modelUser);

    }

    @Test
    public void registerPostLoginPasswordHasErrorAnd1Role1StudentInDbTest() throws Exception {

        User modelUser = new User(null, null, "firstName1", "lastName1", "middleName1", null, null);
        modelUser.setUserId(1L);

        Student student1 = new Student("firstName1", "lastName1", "middleName1", null, null, null, null);
        student1.setStudentId(1L);
        List<Student> resultListStudents  = Arrays.asList(student1);
        Role role1 = new Role("role1", "role1");
        role1.setRoleId(1L);
        List<Role> resultListRoles  = Arrays.asList(role1);
        when(mockRoleService.getAllRoles()).thenReturn(resultListRoles);
        when(mockStudentService.getAllStudents()).thenReturn(resultListStudents);

        MockHttpServletRequestBuilder requestBuilder = post("/registration")
                .flashAttr("user", modelUser);

        ResultActions resultActions = mockMvc.perform(requestBuilder);
        resultActions.andExpect(view().name("/users/createOrUpdateUserForm"));
        resultActions.andExpect(forwardedUrl("/users/createOrUpdateUserForm"));
        resultActions.andExpect(model().attributeHasFieldErrors("user", "login", "password"));

        resultActions.andExpect(model().attributeExists("roles"));
        List<Role> roles = (List<Role>) resultActions.andReturn().getModelAndView().getModel().get("roles");
        assertEquals(resultListRoles.size(), roles.size());

        resultActions.andExpect(model().attributeExists("students"));
        List<Student> students = (List<Student>) resultActions.andReturn().getModelAndView().getModel().get("students");
        assertEquals(resultListStudents.size(), students.size());

        verify(mockUserService, never()).saveUser(modelUser);

    }

    @Test
    @WithMockUser(id = 1L, login="user", password = "password",roles={"USER"})
    public void initAccountFormCorrectAccessTest() throws Exception {

        User modelUser = new User("login", "password", "firstName1", "lastName1", "middleName1", null, null);
        modelUser.setUserId(1L);

        Utils mockUsersUtil = mock(Utils.class);
        when(mockUsersUtil.getUser("1", mockUserService)).thenReturn(modelUser);
        ReflectionTestUtils.setField(userController, "usersUtils", mockUsersUtil);

        MockHttpServletRequestBuilder requestBuilder = get("/users/{userId}", 1L).flashAttr("userId", modelUser.getUserId());

        ResultActions resultActions = mockMvc.perform(requestBuilder)
                .andExpect(view().name("/users/account"))
                .andExpect(forwardedUrl("/users/account"))
                .andExpect(model().attribute("user", hasProperty("userId", is(1L))))
                .andExpect(model().attribute("user", hasProperty("login", is("login"))))
                .andExpect(model().attribute("user", hasProperty("password", is("password"))));

        User user = (User) resultActions.andReturn().getModelAndView().getModel().get("user");
        assertEquals(modelUser, user);

    }

    @Test
    @WithMockUser(id = 2L, login="user", password = "password",roles={"USER"})
    public void initAccountFormNoCorrectAccessTest() throws Exception {

        User modelUser = new User("login", "password", "firstName1", "lastName1", "middleName1", null, null);
        modelUser.setUserId(1L);

        Utils mockUsersUtil = mock(Utils.class);
        when(mockUsersUtil.getUser("1", mockUserService)).thenReturn(modelUser);
        ReflectionTestUtils.setField(userController, "usersUtils", mockUsersUtil);

        MockHttpServletRequestBuilder requestBuilder = get("/users/{userId}", 1L).flashAttr("userId", modelUser.getUserId());

        ResultActions resultActions = mockMvc.perform(requestBuilder)
        .andExpect(status().is3xxRedirection())
        .andExpect(redirectedUrl("/exception/403?userId=" + modelUser.getUserId()))
        .andExpect(view().name("redirect:/exception/403"));

    }

    @Test
    public void editGet1Role1StudentTest() throws Exception {

        Student student1 = new Student("firstName1", "lastName1", "middleName1", null, null, null, null);
        student1.setStudentId(1L);
        List<Student> resultListStudents  = Arrays.asList(student1);
        Role role1 = new Role("role1", "role1");
        role1.setRoleId(1L);
        List<Role> resultListRoles  = Arrays.asList(role1);

        User modelUser = new User("login", "password", "firstName1", "lastName1", "middleName1", new ArrayList(resultListStudents), new ArrayList(resultListRoles));
        modelUser.setUserId(1L);
        Utils mockUsersUtil = mock(Utils.class);
        when(mockUsersUtil.getUser("1", mockUserService)).thenReturn(modelUser);
        ReflectionTestUtils.setField(userController, "usersUtils", mockUsersUtil);

        MockHttpServletRequestBuilder requestBuilder = get("/users/{userId}/edit", 1L);

        ResultActions resultActions = mockMvc.perform(requestBuilder);
        resultActions.andExpect(view().name("/users/createOrUpdateUserForm"));
        resultActions.andExpect(forwardedUrl("/users/createOrUpdateUserForm"));
        resultActions.andExpect(model().attributeExists("user"));
        resultActions.andExpect(model().attributeExists("roles"));
        resultActions.andExpect(model().attributeExists("students"));

        Set<Role> roles = (Set<Role>) resultActions.andReturn().getModelAndView().getModel().get("roles");
        assertEquals(1, roles.size());
        Set<Student> students = (Set<Student>) resultActions.andReturn().getModelAndView().getModel().get("students");
        assertEquals(1, students.size());

    }

    @Test
    @WithMockUser(id = 1L, login="user", password = "password",roles={"USER"})
    public void editPut1Role1StudentTest() throws Exception {

        User modelUser = new User("login1", "password1", "firstName1", "lastName1", "middleName1", null, null);
        modelUser.setUserId(1L);

        Utils mockUsersUtil = mock(Utils.class);
        when(mockUsersUtil.getUser("1", mockUserService)).thenReturn(modelUser);
        ReflectionTestUtils.setField(userController, "usersUtils", mockUsersUtil);

        MockHttpServletRequestBuilder requestBuilder = put("/users/{userId}/edit", 1L).flashAttr("user", modelUser);

        ResultActions resultActions = mockMvc.perform(requestBuilder);
        resultActions.andExpect(status().is3xxRedirection());
        resultActions.andExpect(redirectedUrl("/users/1"));
        resultActions.andExpect(view().name("redirect:/users/1"));
        resultActions.andExpect(model().attributeExists("user"));
        User user = (User) resultActions.andReturn().getModelAndView().getModel().get("user");
        assertEquals(modelUser, user);

        verify(mockUserService).saveUser(modelUser);

    }

    @Test
    @WithMockUser(id = 1L, login="user", password = "password",roles={"USER"})
    public void editPutLoginHasErrorAnd1Role1StudentInDbTest() throws Exception {

        Student student1 = new Student("firstName1", "lastName1", "middleName1", null, null, null, null);
        student1.setStudentId(1L);
        List<Student> resultListStudents  = Arrays.asList(student1);
        Role role1 = new Role("role1", "role1");
        role1.setRoleId(1L);
        List<Role> resultListRoles  = Arrays.asList(role1);

        User modelUser = new User(null, "password", "firstName1", "lastName1", "middleName1", new ArrayList(resultListStudents), new ArrayList(resultListRoles));
        modelUser.setUserId(1L);

        Utils mockUsersUtil = mock(Utils.class);
        when(mockUsersUtil.getUser("1", mockUserService)).thenReturn(modelUser);
        ReflectionTestUtils.setField(userController, "usersUtils", mockUsersUtil);

        MockHttpServletRequestBuilder requestBuilder = put("/users/{userId}/edit", 1L)
                .flashAttr("user", modelUser);

        ResultActions resultActions = mockMvc.perform(requestBuilder);
        resultActions.andExpect(view().name("/users/createOrUpdateUserForm"));
        resultActions.andExpect(forwardedUrl("/users/createOrUpdateUserForm"));
        resultActions.andExpect(model().attributeHasFieldErrors("user", "login"));

        resultActions.andExpect(model().attributeExists("roles"));
        Set<Role> roles = (Set<Role>) resultActions.andReturn().getModelAndView().getModel().get("roles");
        assertEquals(resultListRoles.size(), roles.size());

        resultActions.andExpect(model().attributeExists("students"));
        Set<Student> students = (Set<Student>) resultActions.andReturn().getModelAndView().getModel().get("students");
        assertEquals(resultListStudents.size(), students.size());

        verify(mockUserService, never()).saveUser(modelUser);

    }

    @Test
    @WithMockUser(id = 1L, login="user", password = "password",roles={"USER"})
    public void deleteUserTest() throws Exception {

        User modelUser = new User("login1", "password1", "firstName1", "lastName1", "middleName1", null, null);
        modelUser.setUserId(1L);

        Utils mockUsersUtil = mock(Utils.class);
        when(mockUsersUtil.getUser("1", mockUserService)).thenReturn(modelUser);
        ReflectionTestUtils.setField(userController, "usersUtils", mockUsersUtil);

        MockHttpServletRequestBuilder requestBuilder = delete("/users/{userId}/delete", 1L).flashAttr("userId", 1L);

        ResultActions resultActions = mockMvc.perform(requestBuilder);
        resultActions.andExpect(status().is3xxRedirection());
        resultActions.andExpect(redirectedUrl("/users?userId=" + 1L));
        resultActions.andExpect(view().name("redirect:/users"));

        verify(mockUserService).deleteUser(1L);

    }

}
