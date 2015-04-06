package com.vizaco.onlinecontrol.service.impl;

import com.vizaco.onlinecontrol.dao.StudentDao;
import com.vizaco.onlinecontrol.dao.UserDao;
import com.vizaco.onlinecontrol.dao.jpa.JpaUserDaoImpl;
import com.vizaco.onlinecontrol.model.Role;
import com.vizaco.onlinecontrol.model.Student;
import com.vizaco.onlinecontrol.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created by super on 3/10/15.
 */
public class UserServiceImplTest {

    private UserDao userDao;

    @Before
    public void setUp() throws Exception {
        userDao = mock(UserDao.class);
    }

    private UserServiceImpl getUserService() {
        return new UserServiceImpl(userDao);
    }

    @Test
    public void UserServiceImplConstructorWith1ArgTest(){

        UserServiceImpl userService = new UserServiceImpl(userDao);

        UserDao actual = userService.getUserDao();

        assertEquals(userDao, actual);

    }

    @Test
    public void UserServiceImplConstructorWithNoArgTest(){

        UserServiceImpl userService = new UserServiceImpl();

        UserDao actual = userService.getUserDao();

        assertEquals(null, actual);

    }

    @Test
    public void findUserByLastName1InDBTest(){

        User user1 = new User("login1", "password1", "firstName1", "lastName1", "middleName1", null, null);
        user1.setUserId(1L);
        List<User> resultList  = Arrays.asList(user1);
        when(userDao.findByLastName("lastName1")).thenReturn(resultList);

        UserServiceImpl userService = getUserService();

        Collection<User> actual = userService.findUserByLastName("lastName1");

        assertEquals(1, actual.size());

    }

    @Test
    public void findUserByLastName2InDBTest(){

        User user1 = new User("login1", "password1", "firstName1", "lastName1", "middleName1", null, null);
        user1.setUserId(1L);
        User user2 = new User("login2", "password2", "firstName2", "lastName1", "middleName2", null, null);
        user1.setUserId(2L);
        List<User> resultList  = Arrays.asList(user1, user2);
        when(userDao.findByLastName("lastName1")).thenReturn(resultList);

        UserServiceImpl userService = getUserService();

        Collection<User> actual = userService.findUserByLastName("lastName1");

        assertEquals(2, actual.size());

    }

    @Test
    public void findUserByLastNameWithNullArgumentTest(){

        List<User> resultList  = Collections.EMPTY_LIST;
        when(userDao.findByLastName(null)).thenReturn(resultList);
        UserServiceImpl userService = getUserService();

        Collection<User> actual = userDao.findByLastName(null);

        assertEquals(0, actual.size());

    }

    @Test
    public void findUserByLogin1InDBTest(){

        User user1 = new User("login1", "password1", "firstName1", "lastName1", "middleName1", null, null);
        user1.setUserId(1L);

        when(userDao.findByLogin("login1")).thenReturn(user1);
        UserServiceImpl userService = getUserService();

        User actual = userService.findUserByLogin("login1");

        assertEquals(user1.getUserId(), actual.getUserId());
        assertEquals(user1.getFirstName(), actual.getFirstName());
        assertEquals(user1.getMiddleName(), actual.getMiddleName());
        assertEquals(user1.getLastName(), actual.getLastName());

    }

    @Test
    public void findUserByLoginEmptyInDBTest(){

        when(userDao.findByLogin("empty")).thenReturn(null);
        UserServiceImpl userService = getUserService();

        User actual = userService.findUserByLogin("empty");

        assertEquals(null, actual);

    }

    @Test
    public void findUserByLoginWithNullArgumentTest(){

        User user1  = null;
        when(userDao.findById(null)).thenReturn(user1);
        UserServiceImpl userService = getUserService();

        User actual = userService.findUserByLogin(null);

        assertEquals(null, actual);

    }

    @Test
    public void findUserById1InDBTest(){

        User user1 = new User("login1", "password1", "firstName1", "lastName1", "middleName1", null, null);
        user1.setUserId(1L);

        when(userDao.findById(1L)).thenReturn(user1);
        UserServiceImpl userService = getUserService();

        User actual = userService.findUserById(1L);

        assertEquals(user1.getUserId(), actual.getUserId());
        assertEquals(user1.getFirstName(), actual.getFirstName());
        assertEquals(user1.getMiddleName(), actual.getMiddleName());
        assertEquals(user1.getLastName(), actual.getLastName());

    }

    @Test
    public void findUserByIdEmptyInDBTest(){

        when(userDao.findById(9999L)).thenReturn(null);
        UserServiceImpl userService = getUserService();

        User actual = userService.findUserById(9999L);

        assertEquals(null, actual);

    }

    @Test
    public void findUserByIdWithNullArgumentTest(){

        when(userDao.findById(null)).thenReturn(null);
        UserServiceImpl userService = getUserService();

        User actual = userService.findUserById(null);

        assertEquals(null, actual);

    }

    @Test
    public void getAllUsers1InDBTest(){

        User user1 = new User("login1", "password1", "firstName1", "lastName1", "middleName1", null, null);
        user1.setUserId(1L);

        List<User> resultList  = Arrays.asList(user1);
        when(userDao.getAllUsers()).thenReturn(resultList);
        UserServiceImpl userService = getUserService();

        Collection<User> actual = userService.getAllUsers();

        assertEquals(1, actual.size());

    }

    @Test
    public void getAllUsers2InDBTest(){

        User user1 = new User("login1", "password1", "firstName1", "lastName1", "middleName1", null, null);
        user1.setUserId(1L);
        User user2 = new User("login2", "password2", "firstName2", "lastName2", "middleName2", null, null);
        user1.setUserId(2L);

        List<User> resultList  = Arrays.asList(user1, user2);
        when(userDao.getAllUsers()).thenReturn(resultList);
        UserServiceImpl userService = getUserService();

        Collection<User> actual = userService.getAllUsers();

        assertEquals(2, actual.size());

    }

    @Test
    public void getAllUsersEmptyDBTest(){

        List<User> resultList  = Collections.EMPTY_LIST;
        when(userDao.getAllUsers()).thenReturn(resultList);
        UserServiceImpl userService = getUserService();

        Collection<User> actual = userService.getAllUsers();

        assertEquals(0, actual.size());

    }

    @Test
    public void getAllRoles1InDBTest(){

        Role role1 = new Role("role1");
        role1.setRoleId(1L);

        List<Role> resultList  = Arrays.asList(role1);
        when(userDao.getAllRoles()).thenReturn(resultList);
        UserServiceImpl userService = getUserService();

        Collection<Role> actual = userService.getAllRoles();

        assertEquals(1, actual.size());

    }

    @Test
    public void getAllRoles2InDBTest(){

        Role role1 = new Role("role1");
        role1.setRoleId(1L);
        Role role2 = new Role("role2");
        role1.setRoleId(2L);

        List<Role> resultList  = Arrays.asList(role1, role2);
        when(userDao.getAllRoles()).thenReturn(resultList);
        UserServiceImpl userService = getUserService();

        Collection<Role> actual = userService.getAllRoles();

        assertEquals(2, actual.size());

    }

    @Test
    public void getAllRolesEmptyDBTest(){

        List<Role> resultList  = Collections.EMPTY_LIST;
        when(userDao.getAllRoles()).thenReturn(resultList);
        UserServiceImpl userService = getUserService();

        Collection<Role> actual = userService.getAllRoles();

        assertEquals(0, actual.size());

    }

    @Test
    public void getRoleById1InDBTest(){

        Role role1 = new Role("role1");
        role1.setRoleId(1L);

        List<Role> resultList  = Arrays.asList(role1);
        when(userDao.getRoleById(1L)).thenReturn(role1);
        UserServiceImpl userService = getUserService();

        Role actual = userService.getRoleById(1L);

        assertEquals(role1.getRoleId(), actual.getRoleId());
        assertEquals(role1.getName(), actual.getName());

    }

    @Test
    public void getRoleByIdEmptyInDBTest(){

        when(userDao.getRoleById(1L)).thenReturn(null);
        UserServiceImpl userService = getUserService();

        Role actual = userService.getRoleById(1L);

        assertEquals(null, actual);

    }

    @Test
    public void getRoleByIdWithNullArgumentTest(){

        when(userDao.getRoleById(null)).thenReturn(null);
        UserServiceImpl userService = getUserService();

        Role actual = userService.getRoleById(null);

        assertEquals(null, actual);

    }


    @Test
    public void saveNewUserTest(){

        User user1 = new User("login1", "password1", "firstName1", "lastName1", "middleName1", null, null);

        UserServiceImpl userService = getUserService();

        userService.saveUser(user1);

        verify(userDao).save(user1);

    }

    @Test
    public void saveExistUserTest(){

        User user1 = new User("login1", "password1", "firstName1", "lastName1", "middleName1", null, null);
        user1.setUserId(1L);

        UserServiceImpl userService = getUserService();

        userService.saveUser(user1);

        verify(userDao).save(user1);

    }

    @Test
    public void deleteNullUserTest(){

        User user1 = null;
        when(userDao.findById(null)).thenReturn(null);
        UserServiceImpl userService = getUserService();

        userService.deleteUser(null);

        verify(userDao, never()).delete(any(User.class));

    }

    @Test
    public void deleteExistUserTest(){

        User user1 = new User("login1", "password1", "firstName1", "lastName1", "middleName1", null, null);
        user1.setUserId(1L);
        when(userDao.findById(1L)).thenReturn(user1);
        UserServiceImpl userService = getUserService();

        userService.deleteUser(1L);

        verify(userDao).delete(user1);

    }

}
