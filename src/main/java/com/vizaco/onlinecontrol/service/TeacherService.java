package com.vizaco.onlinecontrol.service;

import com.vizaco.onlinecontrol.model.Student;
import com.vizaco.onlinecontrol.model.Teacher;
import com.vizaco.onlinecontrol.model.User;
import org.springframework.dao.DataAccessException;

import java.util.List;


/**
 * Mostly used as a facade for all OnlineControl controllers
 *
 */
public interface TeacherService {

    Teacher findTeacherById(Integer id) throws DataAccessException;

    List<Teacher> getAllTeachers() throws DataAccessException;

    Teacher findTeacherByUser(User user) throws DataAccessException;

    void saveTeacher(Teacher teacher) throws DataAccessException;

    void deleteTeacher(Integer id) throws DataAccessException;


}
