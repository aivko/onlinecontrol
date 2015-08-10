package com.vizaco.onlinecontrol.service;

import com.vizaco.onlinecontrol.model.Period;
import com.vizaco.onlinecontrol.model.Subject;
import org.springframework.dao.DataAccessException;

import java.util.List;


/**
 * Mostly used as a facade for all OnlineControl controllers
 *
 */
public interface SubjectService {

    Subject findSubjectById(Integer id) throws DataAccessException;

    List<Subject> getAllSubjects() throws DataAccessException;

    void saveSubject(Subject subject) throws DataAccessException;

    void deleteSubject(Integer id) throws DataAccessException;


}
