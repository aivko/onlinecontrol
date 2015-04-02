package com.vizaco.onlinecontrol.service.impl;

import com.vizaco.onlinecontrol.dao.UserDao;
import com.vizaco.onlinecontrol.model.User;
import com.vizaco.onlinecontrol.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserService userService;

    public UserDetailsServiceImpl() {
    }

    @Autowired
    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    public UserService getUserService() {
        return userService;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        User userByLogin = userService.findUserByLogin(login);
        if (userByLogin == null){
            throw new UsernameNotFoundException("Login is not found in the database");
        }
        return userByLogin;

    }

}
