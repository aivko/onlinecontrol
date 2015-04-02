package com.vizaco.onlinecontrol.dao.jpa;

import com.vizaco.onlinecontrol.model.Role;
import com.vizaco.onlinecontrol.model.Student;
import com.vizaco.onlinecontrol.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Created by super on 3/10/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/application-context.xml"})
public class JpaUserDaoImplTest {

    @PersistenceContext
    private EntityManager mockEntityManager;

    @Before
    public void setUp() throws Exception {
        mockEntityManager = mock(EntityManager.class);
    }

    private JpaUserDaoImpl getUserDao(List<?> resultList) {
        Query spyQuery = spy(Query.class);
        when(spyQuery.getResultList()).thenReturn(resultList);

        when(mockEntityManager.createQuery(anyString())).thenReturn(spyQuery);
        when(mockEntityManager.createNamedQuery(anyString())).thenReturn(spyQuery);
        when(mockEntityManager.createNativeQuery(anyString())).thenReturn(spyQuery);

        return new JpaUserDaoImpl(mockEntityManager);
    }

    @Test
    public void findByLastName1InDBTest(){

        User user1 = new User("login1", "password1", "firstName1", "lastName1", "middleName1", null, null);

        List<User> resultList  = Arrays.asList(user1);
        JpaUserDaoImpl userDao = getUserDao(resultList);

        Collection<User> actual = userDao.findByLastName("lastName1");

        assertEquals(1, actual.size());

    }

    @Test
    public void findByLastName2InDBTest(){

        User user1 = new User("login1", "password1", "firstName1", "lastName1", "middleName1", null, null);
        User user2 = new User("login2", "password2", "firstName2", "lastName1", "middleName2", null, null);

        List<User> resultList  = Arrays.asList(user1, user2);
        JpaUserDaoImpl userDao = getUserDao(resultList);

        Collection<User> actual = userDao.findByLastName("lastName1");

        assertEquals(2, actual.size());

    }

    @Test
    public void findByLastNameWithNullArgumentTest(){

        List<User> resultList  = Collections.EMPTY_LIST;
        JpaUserDaoImpl userDao = getUserDao(resultList);

        Collection<User> actual = userDao.findByLastName(null);

        assertEquals(0, actual.size());

    }

    @Test
    public void findByLogin1InDBTest(){

        User user1 = new User("login1", "password1", "firstName1", "lastName1", "middleName1", null, null);
        user1.setUserId(1L);

        List<User> resultList  = Arrays.asList(user1);
        JpaUserDaoImpl userDao = getUserDao(resultList);

        User actual = userDao.findByLogin("login1");

        Long expected = 1L;
        assertEquals(expected, actual.getUserId());
        assertEquals("login1", actual.getLogin());

    }

    @Test
    public void findByLogin2InDBTest(){

        User user1 = new User("login1", "password1", "firstName1", "lastName1", "middleName1", null, null);
        user1.setUserId(1L);
        User user2 = new User("login1", "password2", "firstName2", "lastName2", "middleName2", null, null);
        user2.setUserId(2L);

        List<User> resultList  = Arrays.asList(user1, user2);
        JpaUserDaoImpl userDao = getUserDao(resultList);

        User actual = userDao.findByLogin("login1");

        Long expected = 1L;
        assertEquals(expected, actual.getUserId());
        assertEquals("login1", actual.getLogin());

    }

    @Test
    public void findByLoginWithNullArgumentTest(){

        List<User> resultList  = Collections.EMPTY_LIST;
        JpaUserDaoImpl userDao = getUserDao(resultList);

        User actual = userDao.findByLogin(null);

        assertEquals(null, actual);

    }

    @Test
    public void findById1InDBTest(){

        User user1 = new User("login1", "password1", "firstName1", "lastName1", "middleName1", null, null);
        user1.setUserId(1L);

        List<User> resultList  = Arrays.asList(user1);
        JpaUserDaoImpl userDao = getUserDao(resultList);

        User actual = userDao.findById(1L);

        assertEquals(user1.getUserId(), actual.getUserId());
        assertEquals(user1.getFirstName(), actual.getFirstName());
        assertEquals(user1.getMiddleName(), actual.getMiddleName());
        assertEquals(user1.getLastName(), actual.getLastName());

    }

    @Test
    public void findById2InDBTest(){

        User user1 = new User("login1", "password1", "firstName1", "lastName1", "middleName1", null, null);
        user1.setUserId(1L);
        User user2 = new User("login2", "password2", "firstName2", "lastName2", "middleName2", null, null);
        user2.setUserId(1L);

        List<User> resultList  = Arrays.asList(user1, user2);
        JpaUserDaoImpl userDao = getUserDao(resultList);

        User actual = userDao.findById(1L);

        assertEquals(user1.getUserId(), actual.getUserId());
        assertEquals(user1.getFirstName(), actual.getFirstName());
        assertEquals(user1.getMiddleName(), actual.getMiddleName());
        assertEquals(user1.getLastName(), actual.getLastName());

    }

    @Test
    public void findByIdEmptyInDBTest(){

        List<User> resultList  = Collections.EMPTY_LIST;
        JpaUserDaoImpl userDao = getUserDao(resultList);

        User actual = userDao.findById(99999L);

        assertEquals(null, actual);

    }

    @Test
    public void findByIdWithNullArgumentTest(){

        List<User> resultList  = Collections.EMPTY_LIST;
        JpaUserDaoImpl userDao = getUserDao(resultList);

        User actual = userDao.findById(null);

        assertEquals(null, actual);

    }

    @Test
    public void getAllUsers1InDBTest(){

        User user1 = new User("login1", "password1", "firstName1", "lastName1", "middleName1", null, null);
        user1.setUserId(1L);

        List<User> resultList  = Arrays.asList(user1);
        JpaUserDaoImpl userDao = getUserDao(resultList);

        Collection<User> actual = userDao.getAllUsers();

        assertEquals(1, actual.size());

    }

    @Test
    public void getAllUsers2InDBTest(){

        User user1 = new User("login1", "password1", "firstName1", "lastName1", "middleName1", null, null);
        user1.setUserId(1L);
        User user2 = new User("login2", "password2", "firstName2", "lastName2", "middleName2", null, null);
        user1.setUserId(2L);

        List<User> resultList  = Arrays.asList(user1, user2);
        JpaUserDaoImpl userDao = getUserDao(resultList);

        Collection<User> actual = userDao.getAllUsers();

        assertEquals(2, actual.size());

    }

    @Test
    public void getAllRoles1InDBTest(){

        Role role1 = new Role("role1");
        role1.setRoleId(1L);

        List<Role> resultList  = Arrays.asList(role1);
        JpaUserDaoImpl userDao = getUserDao(resultList);

        Collection<Role> actual = userDao.getAllRoles();

        assertEquals(1, actual.size());

    }

    @Test
    public void getAllRoles2InDBTest(){

        Role role1 = new Role("role1");
        role1.setRoleId(1L);
        Role role2 = new Role("role2");
        role1.setRoleId(2L);

        List<Role> resultList  = Arrays.asList(role1, role2);
        JpaUserDaoImpl userDao = getUserDao(resultList);

        Collection<Role> actual = userDao.getAllRoles();

        assertEquals(2, actual.size());

    }

    @Test
    public void getRoleById1InDBTest(){

        Role role1 = new Role("role1");
        role1.setRoleId(1L);

        List<Role> resultList  = Arrays.asList(role1);
        JpaUserDaoImpl userDao = getUserDao(resultList);

        Role actual = userDao.getRoleById(1L);

        assertEquals(role1.getRoleId(), actual.getRoleId());
        assertEquals(role1.getRoleId(), actual.getRoleId());

    }

    @Test
    public void getRoleById2InDBTest(){

        Role role1 = new Role("role1");
        role1.setRoleId(1L);
        Role role2 = new Role("role2");
        role1.setRoleId(2L);

        List<Role> resultList  = Arrays.asList(role1, role2);
        JpaUserDaoImpl userDao = getUserDao(resultList);

        Role actual = userDao.getRoleById(1L);

        assertEquals(role1.getRoleId(), actual.getRoleId());
        assertEquals(role1.getRoleId(), actual.getRoleId());

    }

    @Test
    public void getRoleByIdEmptyInDBTest(){

        List<Role> resultList  = Collections.EMPTY_LIST;
        JpaUserDaoImpl userDao = getUserDao(resultList);

        Role actual = userDao.getRoleById(99999L);

        assertEquals(null, actual);

    }

    @Test
    public void getRoleByIdWithNullArgumentTest(){

        List<Role> resultList  = Collections.EMPTY_LIST;
        JpaUserDaoImpl userDao = getUserDao(resultList);

        Role actual = userDao.getRoleById(null);

        assertEquals(null, actual);

    }


    @Test
    public void saveNewUserTest(){

        User user1 = new User("login1", "password1", "firstName1", "lastName1", "middleName1", null, null);

        JpaUserDaoImpl userDao = getUserDao(null);

        userDao.save(user1);

        verify(mockEntityManager).persist(user1);

    }

    @Test
    public void saveExistUserTest(){

        User user1 = new User("login1", "password1", "firstName1", "lastName1", "middleName1", null, null);
        user1.setUserId(1L);

        JpaUserDaoImpl userDao = getUserDao(null);

        userDao.save(user1);

        verify(mockEntityManager).merge(user1);

    }

    @Test
    public void deleteNullUserTest(){

        User user1 = null;

        JpaUserDaoImpl userDao = getUserDao(null);

        userDao.delete(user1);

        verify(mockEntityManager, never()).remove(user1);

    }

    @Test
    public void deleteExistUserTest(){

        User user1 = new User("login1", "password1", "firstName1", "lastName1", "middleName1", null, null);
        user1.setUserId(1L);

        JpaUserDaoImpl userDao = getUserDao(null);

        userDao.delete(user1);

        verify(mockEntityManager).remove(user1);

    }

}
