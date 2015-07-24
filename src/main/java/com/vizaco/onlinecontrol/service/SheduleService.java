package com.vizaco.onlinecontrol.service;

import com.vizaco.onlinecontrol.model.*;
import com.vizaco.onlinecontrol.representation.JournalView;
import org.springframework.dao.DataAccessException;

import java.time.DayOfWeek;
import java.util.Date;
import java.util.List;


/**
 * Mostly used as a facade for all OnlineControl controllers
 *
 */
public interface SheduleService {

    Shedule findSheduleById(Long id) throws DataAccessException;

    List<Shedule> getSheduleBeetwenAnyCriteria(Date start, Date end, Clazz clazz, Period period, Subject subject, Teacher teacher) throws DataAccessException;

    List<JournalView> getJournalByCriteria(Date start, Date end) throws DataAccessException;

    Period findPeriodById(Long id) throws DataAccessException;

    List<Period> getAllPeriods() throws DataAccessException;

    Subject findSubjectById(Long id) throws DataAccessException;

    List<Subject> getAllSubjects() throws DataAccessException;

    Teacher findTeacherById(Long id) throws DataAccessException;

    List<Teacher> getAllTeachers() throws DataAccessException;

    void saveShedule(Shedule shedule) throws DataAccessException;

    void deleteShedule(Long id) throws DataAccessException;

}
