package com.vizaco.onlinecontrol.dao;

import com.vizaco.onlinecontrol.model.Role;
import com.vizaco.onlinecontrol.model.User;
import org.springframework.dao.DataAccessException;

import java.util.Collection;
import java.util.List;

public interface UserDao {

    Collection<User> findByLastName(String lastName) throws DataAccessException;
    
    User findByLogin(String login) throws DataAccessException;

    User findById(Long id) throws DataAccessException;
    
    List<Role> getAllRoles() throws DataAccessException;
    
    Role getRoleById(Long id) throws DataAccessException;
    
    List<User> getAllUsers() throws DataAccessException;

    void save(User user) throws DataAccessException;

    void delete(User user) throws DataAccessException;

}
