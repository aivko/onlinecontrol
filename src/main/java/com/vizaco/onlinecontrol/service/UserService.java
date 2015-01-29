package com.vizaco.onlinecontrol.service;

import com.vizaco.onlinecontrol.model.Role;
import com.vizaco.onlinecontrol.model.User;
import org.springframework.dao.DataAccessException;

import java.util.Collection;
import java.util.List;


/**
 * Mostly used as a facade for all OnlineControl controllers
 *
 */
public interface UserService {

    User findUserById(String id) throws DataAccessException;

    Collection<User> findUserByLastName(String lastName) throws DataAccessException;
    
    User findUserByLogin(String login) throws DataAccessException;

    List<Role> getAllRoles() throws DataAccessException;

    Role getRoleById(String id) throws DataAccessException;

    List<User> getAllUsers() throws DataAccessException;

    void saveUser(User user) throws DataAccessException;

}
