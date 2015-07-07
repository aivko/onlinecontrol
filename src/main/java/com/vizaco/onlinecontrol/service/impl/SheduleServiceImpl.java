package com.vizaco.onlinecontrol.service.impl;

import com.vizaco.onlinecontrol.dao.ClazzDao;
import com.vizaco.onlinecontrol.dao.SheduleDao;
import com.vizaco.onlinecontrol.model.*;
import com.vizaco.onlinecontrol.service.ClazzService;
import com.vizaco.onlinecontrol.service.SheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
public class SheduleServiceImpl implements SheduleService {

    @Autowired
    private SheduleDao sheduleDao;

    @Override
    @Transactional(readOnly = true)
    public Shedule findSheduleById(Long id) throws DataAccessException {
        return sheduleDao.findSheduleById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Shedule> getAllShedule() throws DataAccessException {
        return sheduleDao.getAllShedule();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Shedule> getSheduleBeetwenIntervalAndClass(Date start, Date end, Clazz clazz) throws DataAccessException {
        return sheduleDao.getSheduleBeetwenIntervalAndClass(start, end, clazz);
    }

    @Override
    @Transactional(readOnly = true)
    public Period findPeriodById(Long id) throws DataAccessException {
        return sheduleDao.findPeriodById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Period> getAllPeriods() throws DataAccessException {
        return sheduleDao.getAllPeriods();
    }

    @Override
    @Transactional(readOnly = true)
    public Subject findSubjectById(Long id) throws DataAccessException {
        return sheduleDao.findSubjectById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Subject> getAllSubjects() throws DataAccessException {
        return sheduleDao.getAllSubjects();
    }

    @Override
    @Transactional(readOnly = true)
    public Teacher findTeacherById(Long id) throws DataAccessException {
        return sheduleDao.findTeacherById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Teacher> getAllTeachers() throws DataAccessException {
        return sheduleDao.getAllTeachers();
    }

    @Override
    @Transactional(readOnly = true)
    public void saveShedule(Shedule shedule) throws DataAccessException {
        sheduleDao.saveShedule(shedule);
    }

}
