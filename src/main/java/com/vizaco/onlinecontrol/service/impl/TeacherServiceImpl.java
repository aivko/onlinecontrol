package com.vizaco.onlinecontrol.service.impl;

import com.vizaco.onlinecontrol.dao.TeacherDao;
import com.vizaco.onlinecontrol.model.User;
import com.vizaco.onlinecontrol.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import com.vizaco.onlinecontrol.model.Teacher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherDao teacherDao;

    @Override
    @Transactional(readOnly = true)
    public Teacher findTeacherById(Integer id) throws DataAccessException {
        return teacherDao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Teacher> getAllTeachers() throws DataAccessException {
        return teacherDao.getAllTeachers();
    }

    @Override
    @Transactional(readOnly = true)
    public Teacher findTeacherByUser(User user) throws DataAccessException {
        return teacherDao.findTeacherByUser(user);
    }

    @Override
    @Transactional
    public void saveTeacher(Teacher teacher) throws DataAccessException {
        teacherDao.save(teacher);
    }

    @Override
    @Transactional
    public void deleteTeacher(Integer id) throws DataAccessException {
        Teacher teacher = teacherDao.findById(id);
        if (teacher == null){
            return;
        }
        teacherDao.delete(teacher);
    }

}
