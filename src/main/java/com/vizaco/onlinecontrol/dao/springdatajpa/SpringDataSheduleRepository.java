package com.vizaco.onlinecontrol.dao.springdatajpa;

import com.vizaco.onlinecontrol.dao.SheduleDao;
import com.vizaco.onlinecontrol.model.*;
import com.vizaco.onlinecontrol.representation.JournalView;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface SpringDataSheduleRepository extends SheduleDao, Repository<Shedule, Integer> {

    @Override
    Shedule findSheduleById(@Param("id") Integer id) throws DataAccessException;

    @Override
//    @Query("SELECT shedule FROM Shedule shedule")
    List<Shedule> getSheduleBeetwenAnyCriteria(Date start, Date end, Clazz clazz, Period period, Subject subject, Teacher teacher) throws DataAccessException;

    @Override
//    @Query("SELECT shedule FROM Shedule shedule")
    List<JournalView> getJournalByCriteria(Date start, Date end, Student student, Clazz clazz, Period period, Subject subject, Teacher teacher) throws DataAccessException;

}
