package com.vizaco.onlinecontrol.service.impl;

import com.vizaco.onlinecontrol.dao.UserDao;
import com.vizaco.onlinecontrol.model.User;
import com.vizaco.onlinecontrol.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional(readOnly = true)
    public User findUserById(Long id) throws DataAccessException {
        return userDao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public User findUserByEmail(String email) throws DataAccessException {
        return userDao.findByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() throws DataAccessException {
        return userDao.getAllUsers();
    }

    @Override
    @Transactional
    public void saveUser(User user) throws DataAccessException {
        userDao.save(user);

        //refresh authentication
        SecurityContext context = SecurityContextHolder.getContext();
        if (user.getId() == ((User) context.getAuthentication().getPrincipal()).getId()){
            context.setAuthentication(new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities()));
        }

    }

    @Override
    @Transactional
    public void deleteUser(Long id) throws DataAccessException {
        User user = userDao.findById(id);
        if (user == null){
            return;
        }
        //check authentication
        SecurityContext context = SecurityContextHolder.getContext();
        if (user.getId() != ((User) context.getAuthentication().getPrincipal()).getId()){
            userDao.delete(user);
        }

    }

}
