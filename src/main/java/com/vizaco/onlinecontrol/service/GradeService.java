package com.vizaco.onlinecontrol.service;

import com.vizaco.onlinecontrol.model.Grade;
import com.vizaco.onlinecontrol.model.Role;
import org.springframework.dao.DataAccessException;

import java.util.List;


/**
 * Mostly used as a facade for all OnlineControl controllers
 *
 */
public interface GradeService {

    Grade findGradeById(Integer id) throws DataAccessException;

    List<Grade> getAllGrades() throws DataAccessException;

    Grade saveGrade(Grade grade) throws DataAccessException;

    void deleteGrade(Integer id) throws DataAccessException;

    void flush() throws DataAccessException;

}
