package com.vizaco.onlinecontrol.dao;

import com.vizaco.onlinecontrol.model.Grade;
import com.vizaco.onlinecontrol.model.Role;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface GradeDao {

    Grade findById(Integer id) throws DataAccessException;

    List<Grade> getAllGrades() throws DataAccessException;

    Grade save(Grade grade) throws DataAccessException;

    void delete(Grade grade) throws DataAccessException;

}
