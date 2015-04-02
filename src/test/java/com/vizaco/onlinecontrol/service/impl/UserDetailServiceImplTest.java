package com.vizaco.onlinecontrol.service.impl;

import com.vizaco.onlinecontrol.dao.UserDao;
import com.vizaco.onlinecontrol.model.Role;
import com.vizaco.onlinecontrol.model.User;
import com.vizaco.onlinecontrol.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created by super on 3/10/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/application-context.xml"})
public class UserDetailServiceImplTest {

    @Autowired
    private UserService userService;

    @Before
    public void setUp() throws Exception {
        userService = mock(UserService.class);
    }

    private UserDetailsServiceImpl getDetailsUserService() {
        return new UserDetailsServiceImpl(userService);
    }

    @Test
    public void UserServiceImplConstructorWith1ArgTest(){

        UserDetailsServiceImpl userDetailsService = new UserDetailsServiceImpl(userService);

        UserService actual = userDetailsService.getUserService();

        assertEquals(userService, actual);

    }

    @Test
    public void UserServiceImplConstructorWithNoArgTest(){

        UserDetailsServiceImpl userDetailsService = new UserDetailsServiceImpl();

        UserService actual = userDetailsService.getUserService();

        assertEquals(null, actual);

    }

    @Test
    public void loadUserByUsername1InDBTest(){

        User user1 = new User("login1", "password1", "firstName1", "lastName1", "middleName1", null, null);
        user1.setUserId(1L);

        when(userService.findUserByLogin("login1")).thenReturn(user1);
        UserDetailsServiceImpl userService = getDetailsUserService();

        UserDetails actual = userService.loadUserByUsername("login1");

        assertEquals(user1.getUsername(), actual.getUsername());
        assertEquals(user1.getAuthorities(), actual.getAuthorities());

    }

    @Test(expected = UsernameNotFoundException.class)
    public void loadUserByUsernameEmptyInDBTest(){

        when(userService.findUserByLogin("empty")).thenReturn(null);
        UserDetailsServiceImpl userService = getDetailsUserService();

        UserDetails actual = userService.loadUserByUsername("empty");

    }

    @Test(expected = UsernameNotFoundException.class)
    public void loadUserByUsernameWithNullArgumentTest(){

        when(userService.findUserByLogin(null)).thenReturn(null);
        UserDetailsServiceImpl userService = getDetailsUserService();

        UserDetails actual = userService.loadUserByUsername(null);

    }

}
