package com.vizaco.onlinecontrol.dao;

import com.vizaco.onlinecontrol.model.Student;
import com.vizaco.onlinecontrol.model.User;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface StudentDao {

    Student findById(Integer id) throws DataAccessException;

    List<Student> findByLastName(String lastName) throws DataAccessException;

    Student findStudentByUser(User user) throws DataAccessException;

    List<Student> getAllStudents() throws DataAccessException;

    void save(Student student) throws DataAccessException;

    void delete(Student student) throws DataAccessException;

}
