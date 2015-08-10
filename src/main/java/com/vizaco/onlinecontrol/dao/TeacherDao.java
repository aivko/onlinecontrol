package com.vizaco.onlinecontrol.dao;

import com.vizaco.onlinecontrol.model.Parent;
import com.vizaco.onlinecontrol.model.Teacher;
import com.vizaco.onlinecontrol.model.User;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface TeacherDao {

    Teacher findById(Integer id) throws DataAccessException;
    
    List<Teacher> getAllTeachers() throws DataAccessException;

    Teacher findTeacherByUser(User user) throws DataAccessException;

    void save(Teacher teacher) throws DataAccessException;

    void delete(Teacher teacher) throws DataAccessException;

}
