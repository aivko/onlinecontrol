package com.vizaco.onlinecontrol.dao;

import com.vizaco.onlinecontrol.model.*;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface SheduleDao {

    Shedule findSheduleById(Long id) throws DataAccessException;

    DayOfTheWeek findDayOfTheWeekById(Long id) throws DataAccessException;

    List<DayOfTheWeek> getAllDaysOfTheWeek() throws DataAccessException;

    Period findPeriodById(Long id) throws DataAccessException;

    List<Period> getAllPeriods() throws DataAccessException;

    Subject findSubjectById(Long id) throws DataAccessException;

    List<Subject> getAllSubjects() throws DataAccessException;

    Teacher findTeacherById(Long id) throws DataAccessException;

    List<Teacher> getAllTeachers() throws DataAccessException;

}
