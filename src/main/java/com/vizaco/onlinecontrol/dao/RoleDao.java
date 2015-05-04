package com.vizaco.onlinecontrol.dao;

import com.vizaco.onlinecontrol.model.Role;
import com.vizaco.onlinecontrol.model.Student;
import com.vizaco.onlinecontrol.model.User;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface RoleDao {

    Role findById(Long id) throws DataAccessException;

    List<Role> findByName(String name) throws DataAccessException;

    List<Role> getAllRoles() throws DataAccessException;

    void save(Role role) throws DataAccessException;

    void delete(Role role) throws DataAccessException;

}
