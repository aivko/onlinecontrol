package com.vizaco.onlinecontrol.dao;

import com.vizaco.onlinecontrol.model.*;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface SheduleDao {

    Shedule findById(Long id) throws DataAccessException;

    List<DayOfTheWeek> getAllDaysOfTheWeek() throws DataAccessException;

    List<Period> getAllPeriods() throws DataAccessException;

    List<Subject> getAllSubjects() throws DataAccessException;

    List<Teacher> getAllTeachers() throws DataAccessException;

}
