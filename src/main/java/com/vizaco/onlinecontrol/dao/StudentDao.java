package com.vizaco.onlinecontrol.dao;

import com.vizaco.onlinecontrol.model.Role;
import com.vizaco.onlinecontrol.model.Student;
import org.springframework.dao.DataAccessException;

import java.util.Collection;
import java.util.List;

public interface StudentDao {

    Collection<Student> findByLastName(String lastName) throws DataAccessException;

    Student findById(String id) throws DataAccessException;

    List<Student> getAllStudents() throws DataAccessException;

    void save(Student student) throws DataAccessException;

}
