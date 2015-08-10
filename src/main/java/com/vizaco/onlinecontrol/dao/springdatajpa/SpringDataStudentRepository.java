package com.vizaco.onlinecontrol.dao.springdatajpa;

import com.vizaco.onlinecontrol.dao.StudentDao;
import com.vizaco.onlinecontrol.dao.UserDao;
import com.vizaco.onlinecontrol.model.Student;
import com.vizaco.onlinecontrol.model.Teacher;
import com.vizaco.onlinecontrol.model.User;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SpringDataStudentRepository extends StudentDao, Repository<Student, Integer> {

    @Override
    List<Student> findByLastName(@Param("lastName") String lastName) throws DataAccessException;

    @Override
    @Query("SELECT student FROM Student student WHERE student.user =:user")
    Student findStudentByUser(@Param("user") User user) throws DataAccessException;

    @Override
    Student findById(@Param("id") Integer id) throws DataAccessException;

    @Override
    @Query("SELECT student FROM Student student")
    List<Student> getAllStudents() throws DataAccessException;

}
