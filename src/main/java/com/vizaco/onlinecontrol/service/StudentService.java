package com.vizaco.onlinecontrol.service;

import com.vizaco.onlinecontrol.model.Student;
import org.springframework.dao.DataAccessException;
import org.springframework.security.access.prepost.PostFilter;

import java.util.Collection;
import java.util.List;


/**
 * Mostly used as a facade for all OnlineControl controllers
 *
 */
public interface StudentService {

    Student findStudentById(Long id) throws DataAccessException;

    Collection<Student> findStudentByLastName(String lastName) throws DataAccessException;

    @PostFilter("hasRole('ROLE_GOD')")
    List<Student> getAllStudents() throws DataAccessException;

    void saveStudent(Student student) throws DataAccessException;

    void deleteStudent(Long id) throws DataAccessException;

}
