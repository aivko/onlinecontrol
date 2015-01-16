package com.vizaco.onlinecontrol.service;

import com.vizaco.onlinecontrol.model.Student;
import org.springframework.dao.DataAccessException;

import java.util.Collection;


/**
 * Mostly used as a facade for all OnlineControl controllers
 *
 */
public interface OnlineControlService {

    Student findStudentById(String id) throws DataAccessException;

    Collection<Student> findStudentByLastName(String lastName) throws DataAccessException;

    void saveStudent(Student student) throws DataAccessException;

}
