package com.vizaco.onlinecontrol.service.impl;

import com.vizaco.onlinecontrol.dao.ClazzDao;
import com.vizaco.onlinecontrol.dao.RoleDao;
import com.vizaco.onlinecontrol.model.Clazz;
import com.vizaco.onlinecontrol.model.Role;
import com.vizaco.onlinecontrol.service.ClazzService;
import com.vizaco.onlinecontrol.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {

    private ClazzDao clazzDao;

    public ClazzServiceImpl() {
    }

    @Autowired
    public ClazzServiceImpl(ClazzDao clazzDao) {
        this.clazzDao = clazzDao;
    }

    public ClazzDao getClazzDao() {
        return clazzDao;
    }

    @Override
    @Transactional(readOnly = true)
    public Clazz findClazzById(Long id) throws DataAccessException {
        return clazzDao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Clazz> findClazzByName(String name) throws DataAccessException {
        return clazzDao.findByName(name);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Clazz> getAllClazzes() throws DataAccessException {
        return clazzDao.getAllClazzes();
    }

    @Override
    @Transactional
    public void saveClazz(Clazz clazz) throws DataAccessException {
        clazzDao.save(clazz);
    }

    @Override
    @Transactional
    public void deleteClazz(Long id) throws DataAccessException {
        Clazz clazz = clazzDao.findById(id);
        if (clazz == null){
            return;
        }
        clazzDao.delete(clazz);
    }

}
