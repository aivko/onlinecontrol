package com.vizaco.onlinecontrol.service.impl;

import com.vizaco.onlinecontrol.dao.BusinessDao;
import com.vizaco.onlinecontrol.dao.RoleDao;
import com.vizaco.onlinecontrol.model.Person;
import com.vizaco.onlinecontrol.model.Role;
import com.vizaco.onlinecontrol.model.User;
import com.vizaco.onlinecontrol.service.BusinessService;
import com.vizaco.onlinecontrol.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
public class BusinessServiceImpl implements BusinessService {

    @Autowired
    private BusinessDao businessDao;

    @Override
    @Transactional(readOnly = true)
    public Person getCurrentPerson(User user) throws DataAccessException {
        return businessDao.getCurrentPerson(user);
    }

}
