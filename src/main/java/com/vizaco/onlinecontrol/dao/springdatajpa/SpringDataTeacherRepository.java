package com.vizaco.onlinecontrol.dao.springdatajpa;

import com.vizaco.onlinecontrol.dao.TeacherDao;
import com.vizaco.onlinecontrol.dao.UserDao;
import com.vizaco.onlinecontrol.model.Teacher;
import com.vizaco.onlinecontrol.model.User;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SpringDataTeacherRepository extends TeacherDao, Repository<Teacher, Integer> {

    @Override
    @Query("SELECT teacher FROM Teacher teacher WHERE teacher.user =:user")
    Teacher findTeacherByUser(@Param("user") User user) throws DataAccessException;

    @Override
    Teacher findById(@Param("id") Integer id) throws DataAccessException;

    @Override
    @Query("SELECT teacher FROM Teacher teacher")
    List<Teacher> getAllTeachers() throws DataAccessException;

}
