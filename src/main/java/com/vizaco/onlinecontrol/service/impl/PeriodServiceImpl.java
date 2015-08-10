package com.vizaco.onlinecontrol.service.impl;

import com.vizaco.onlinecontrol.dao.PeriodDao;
import com.vizaco.onlinecontrol.dao.TeacherDao;
import com.vizaco.onlinecontrol.model.Period;
import com.vizaco.onlinecontrol.model.Teacher;
import com.vizaco.onlinecontrol.model.User;
import com.vizaco.onlinecontrol.service.PeriodService;
import com.vizaco.onlinecontrol.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PeriodServiceImpl implements PeriodService {

    @Autowired
    private PeriodDao periodDao;

    @Override
    @Transactional(readOnly = true)
    public Period findPeriodById(Integer id) throws DataAccessException {
        return periodDao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Period> getAllPeriods() throws DataAccessException {
        return periodDao.getAllPeriods();
    }

    @Override
    @Transactional
    public void savePeriod(Period period) throws DataAccessException {
        periodDao.save(period);
    }

    @Override
    @Transactional
    public void deletePeriod(Integer id) throws DataAccessException {
        Period period = periodDao.findById(id);
        if (period == null){
            return;
        }
        periodDao.delete(period);
    }

}
