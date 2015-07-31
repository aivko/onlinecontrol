package com.vizaco.onlinecontrol.service.impl;

import com.vizaco.onlinecontrol.dao.ParentDao;
import com.vizaco.onlinecontrol.model.Parent;
import com.vizaco.onlinecontrol.service.ParentService;
import com.vizaco.onlinecontrol.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
public class ParentServiceImpl implements ParentService {

    private ParentDao parentDao;

    public ParentServiceImpl() {
    }

    @Autowired
    public ParentServiceImpl(ParentDao parentDao) {
        this.parentDao = parentDao;
    }

    public ParentDao getParentDao() {
        return parentDao;
    }

    @Override
    @Transactional(readOnly = true)
    public Parent findParentById(Integer id) throws DataAccessException {
        return parentDao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Parent> getAllParents() throws DataAccessException {
        return parentDao.getAllParents();
    }

    @Override
    @Transactional
    public void saveParent(Parent parent) throws DataAccessException {
        parentDao.save(parent);
    }

    @Override
    @Transactional
    public void deleteParent(Integer id) throws DataAccessException {
        Parent parent = parentDao.findById(id);
        if (parent == null){
            return;
        }
        parentDao.delete(parent);
    }

}
