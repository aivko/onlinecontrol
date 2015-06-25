package com.vizaco.onlinecontrol.service;

import com.vizaco.onlinecontrol.model.*;
import org.springframework.dao.DataAccessException;

import java.util.List;


/**
 * Mostly used as a facade for all OnlineControl controllers
 *
 */
public interface SheduleService {

    Shedule findSheduleById(Long id) throws DataAccessException;

    List<DayOfTheWeek> getAllDaysOfTheWeek() throws DataAccessException;

    List<Period> getAllPeriods() throws DataAccessException;

    List<Subject> getAllSubjects() throws DataAccessException;

    List<Teacher> getAllTeachers() throws DataAccessException;

}
