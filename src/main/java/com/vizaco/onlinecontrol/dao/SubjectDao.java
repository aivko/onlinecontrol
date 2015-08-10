package com.vizaco.onlinecontrol.dao;

import com.vizaco.onlinecontrol.model.Period;
import com.vizaco.onlinecontrol.model.Subject;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface SubjectDao {

    Subject findById(Integer id) throws DataAccessException;
    
    List<Subject> getAllSubjects() throws DataAccessException;

    void save(Subject subject) throws DataAccessException;

    void delete(Subject subject) throws DataAccessException;

}
