package com.vizaco.onlinecontrol.service;

import com.vizaco.onlinecontrol.model.Student;
import org.springframework.dao.DataAccessException;

import java.util.Collection;
import java.util.List;


/**
 * Mostly used as a facade for all OnlineControl controllers
 *
 */
public interface StudentService {

    Student findStudentById(Long id) throws DataAccessException;

    Collection<Student> findStudentByLastName(String lastName) throws DataAccessException;

    List<Student> getAllStudents() throws DataAccessException;

    void saveStudent(Student student) throws DataAccessException;

}
