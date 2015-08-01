package com.vizaco.onlinecontrol.service.impl;

import com.vizaco.onlinecontrol.dao.RoleDao;
import com.vizaco.onlinecontrol.dao.StudentDao;
import com.vizaco.onlinecontrol.model.Role;
import com.vizaco.onlinecontrol.model.Student;
import com.vizaco.onlinecontrol.model.User;
import com.vizaco.onlinecontrol.service.RoleService;
import com.vizaco.onlinecontrol.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleDao roleDao;

    public RoleServiceImpl() {
    }

    @Autowired
    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    public RoleDao getRoleDao() {
        return roleDao;
    }

    @Override
    @Transactional(readOnly = true)
    public Role findRoleById(Integer id) throws DataAccessException {
        return roleDao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Role> getAllRoles() throws DataAccessException {
        return roleDao.getAllRoles();
    }

    @Override
    @Transactional
    public void saveRole(Role role) throws DataAccessException {
        roleDao.save(role);
    }

    @Override
    @Transactional
    public void deleteRole(Integer id) throws DataAccessException {
        Role role = roleDao.findById(id);
        if (role == null){
            return;
        }
        roleDao.delete(role);
    }

}
