package com.vizaco.onlinecontrol.dao;

import com.vizaco.onlinecontrol.model.*;
import org.springframework.dao.DataAccessException;

import java.time.DayOfWeek;
import java.util.Date;
import java.util.List;

public interface SheduleDao {

    Shedule findSheduleById(Long id) throws DataAccessException;

    List<Shedule> getAllShedule() throws DataAccessException;

    List<Shedule> getSheduleBeetwenIntervalAndClass(Date start, Date end, Clazz clazz) throws DataAccessException;

    List<Shedule> getSheduleBeetwenInterval(Date start, Date end) throws DataAccessException;

    Period findPeriodById(Long id) throws DataAccessException;

    List<Period> getAllPeriods() throws DataAccessException;

    Subject findSubjectById(Long id) throws DataAccessException;

    List<Subject> getAllSubjects() throws DataAccessException;

    Teacher findTeacherById(Long id) throws DataAccessException;

    List<Teacher> getAllTeachers() throws DataAccessException;

    void saveShedule(Shedule shedule) throws DataAccessException;

}
