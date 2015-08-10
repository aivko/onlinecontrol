package com.vizaco.onlinecontrol.dao.springdatajpa;

import com.vizaco.onlinecontrol.dao.PeriodDao;
import com.vizaco.onlinecontrol.dao.RoleDao;
import com.vizaco.onlinecontrol.model.Period;
import com.vizaco.onlinecontrol.model.Role;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SpringDataPeriodRepository extends PeriodDao, Repository<Period, Integer> {

    @Override
    Period findById(@Param("id") Integer id) throws DataAccessException;

    @Override
    @Query("SELECT period FROM Period period")
    List<Period> getAllPeriods() throws DataAccessException;

}
