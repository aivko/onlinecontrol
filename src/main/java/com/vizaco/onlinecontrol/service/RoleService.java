package com.vizaco.onlinecontrol.service;

import com.vizaco.onlinecontrol.model.Role;
import org.springframework.dao.DataAccessException;

import java.util.Collection;
import java.util.List;


/**
 * Mostly used as a facade for all OnlineControl controllers
 *
 */
public interface RoleService {

    Role findRoleById(Long id) throws DataAccessException;

    Collection<Role> findRoleByName(String name) throws DataAccessException;

    List<Role> getAllRoles() throws DataAccessException;

    void saveRole(Role role) throws DataAccessException;

    void deleteRole(Long id) throws DataAccessException;

}
