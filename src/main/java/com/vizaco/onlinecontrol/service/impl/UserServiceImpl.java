package com.vizaco.onlinecontrol.service.impl;

import com.vizaco.onlinecontrol.dao.UserDao;
import com.vizaco.onlinecontrol.model.Role;
import com.vizaco.onlinecontrol.model.User;
import com.vizaco.onlinecontrol.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public UserServiceImpl() {
    }

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional(readOnly = true)
    public User findUserById(String id) throws DataAccessException {
        return userDao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<User> findUserByLastName(String lastName) throws DataAccessException {
        return userDao.findByLastName(lastName);
    }

    @Override
    @Transactional(readOnly = true)
    public User findUserByLogin(String login) throws DataAccessException {
        return userDao.findByLogin(login);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Role> getAllRoles() throws DataAccessException {
        return userDao.getAllRoles();
    }

    @Override
    public Role getRoleById(String id) throws DataAccessException {
        return userDao.getRoleById(id);
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
    }

}
