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

    Student findStudentById(Integer id) throws DataAccessException;

    List<Student> findStudentByLastName(String lastName) throws DataAccessException;

    List<Student> getAllStudents() throws DataAccessException;

    void saveStudent(Student student) throws DataAccessException;

    void deleteStudent(Integer id) throws DataAccessException;

}
