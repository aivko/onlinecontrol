package com.vizaco.onlinecontrol.service;

import com.vizaco.onlinecontrol.model.Period;
import com.vizaco.onlinecontrol.model.Teacher;
import com.vizaco.onlinecontrol.model.User;
import org.springframework.dao.DataAccessException;

import java.util.List;


/**
 * Mostly used as a facade for all OnlineControl controllers
 *
 */
public interface PeriodService {

    Period findPeriodById(Integer id) throws DataAccessException;

    List<Period> getAllPeriods() throws DataAccessException;

    void savePeriod(Period period) throws DataAccessException;

    void deletePeriod(Integer id) throws DataAccessException;


}
