package com.vizaco.onlinecontrol.dao.springdatajpa;

import com.vizaco.onlinecontrol.dao.ClazzDao;
import com.vizaco.onlinecontrol.dao.RoleDao;
import com.vizaco.onlinecontrol.model.Clazz;
import com.vizaco.onlinecontrol.model.Role;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SpringDataRoleRepository extends RoleDao, Repository<Role, Integer> {

    @Override
    Role findById(@Param("id") Integer id) throws DataAccessException;

    @Override
    @Query("SELECT role FROM Role role")
    List<Role> getAllRoles() throws DataAccessException;

}
