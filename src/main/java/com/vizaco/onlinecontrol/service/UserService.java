package com.vizaco.onlinecontrol.service;

import com.vizaco.onlinecontrol.model.Person;
import com.vizaco.onlinecontrol.model.User;
import org.springframework.dao.DataAccessException;

import java.util.List;


/**
 * Mostly used as a facade for all OnlineControl controllers
 *
 */
public interface UserService {

    User findUserById(Integer id) throws DataAccessException;

    User findUserByEmail(String email) throws DataAccessException;

    Person getCurrentPerson(User user) throws DataAccessException;

    List<User> getAllUsers() throws DataAccessException;

    void saveUser(User user) throws DataAccessException;

    void deleteUser(Integer id) throws DataAccessException;


}
