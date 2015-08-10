package com.vizaco.onlinecontrol.dao;

import com.vizaco.onlinecontrol.model.Person;
import com.vizaco.onlinecontrol.model.User;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface UserDao {

    User findByEmail(String email) throws DataAccessException;

    User findById(Integer id) throws DataAccessException;

    List<User> getAllUsers() throws DataAccessException;

    void save(User user) throws DataAccessException;

    void delete(User user) throws DataAccessException;

}
