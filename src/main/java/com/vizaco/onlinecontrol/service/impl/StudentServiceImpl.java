package com.vizaco.onlinecontrol.service.impl;

import com.vizaco.onlinecontrol.dao.StudentDao;
import com.vizaco.onlinecontrol.model.Role;
import com.vizaco.onlinecontrol.model.Student;
import com.vizaco.onlinecontrol.model.User;
import com.vizaco.onlinecontrol.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentDao studentDao;

    public StudentServiceImpl() {
    }

    @Autowired
    public StudentServiceImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public StudentDao getStudentDao() {
        return studentDao;
    }

    @Override
    @Transactional(readOnly = true)
    public Student findStudentById(Integer id) throws DataAccessException {
        return studentDao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Student> findStudentByLastName(String lastName) throws DataAccessException {
        return studentDao.findByLastName(lastName);
    }

    @Override
    @Transactional(readOnly = true)
    public Student findStudentByUser(User user) throws DataAccessException {
        return studentDao.findStudentByUser(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Student> getAllStudents() throws DataAccessException {
        return studentDao.getAllStudents();
    }

    @Override
    @Transactional
    public void saveStudent(Student student) throws DataAccessException {
        studentDao.save(student);
    }

    @Override
    @Transactional
    public void deleteStudent(Integer id) throws DataAccessException {
        Student student = studentDao.findById(id);
        if (student == null){
            return;
        }
        studentDao.delete(student);
    }

}
