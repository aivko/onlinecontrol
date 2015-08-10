package com.vizaco.onlinecontrol.dao;

import com.vizaco.onlinecontrol.model.Period;
import com.vizaco.onlinecontrol.model.Teacher;
import com.vizaco.onlinecontrol.model.User;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface PeriodDao {

    Period findById(Integer id) throws DataAccessException;
    
    List<Period> getAllPeriods() throws DataAccessException;

    void save(Period period) throws DataAccessException;

    void delete(Period period) throws DataAccessException;

}
