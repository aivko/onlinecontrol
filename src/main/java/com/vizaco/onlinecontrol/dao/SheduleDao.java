package com.vizaco.onlinecontrol.dao;

import com.vizaco.onlinecontrol.model.*;
import com.vizaco.onlinecontrol.representation.JournalView;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.domain.Specification;

import java.util.Date;
import java.util.List;

public interface SheduleDao {

    Shedule findSheduleById(Integer id) throws DataAccessException;

    List<Shedule> getSheduleByCriteria(Date start, Date end, Clazz clazz, Period period, Subject subject, Teacher teacher) throws DataAccessException;

    List<JournalView> getJournalByCriteria(Date start, Date end, Student student, Clazz clazz, Period period, Subject subject, Teacher teacher) throws DataAccessException;

    Shedule save(Shedule shedule) throws DataAccessException;

    void delete(Shedule shedule) throws DataAccessException;;

}
