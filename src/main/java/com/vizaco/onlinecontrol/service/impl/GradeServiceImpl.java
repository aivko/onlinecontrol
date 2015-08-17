package com.vizaco.onlinecontrol.service.impl;

import com.vizaco.onlinecontrol.dao.GradeDao;
import com.vizaco.onlinecontrol.dao.RoleDao;
import com.vizaco.onlinecontrol.model.Grade;
import com.vizaco.onlinecontrol.model.Role;
import com.vizaco.onlinecontrol.service.GradeService;
import com.vizaco.onlinecontrol.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GradeServiceImpl implements GradeService {

    @Autowired
    private GradeDao gradeDao;

    public GradeServiceImpl() {
    }

    @Override
    @Transactional(readOnly = true)
    public Grade findGradeById(Integer id) throws DataAccessException {
        return gradeDao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Grade> getAllGrades() throws DataAccessException {
        return gradeDao.getAllGrades();
    }

    @Override
    @Transactional
    public Grade saveGrade(Grade grade) throws DataAccessException {
        return gradeDao.save(grade);
    }

    @Override
    @Transactional
    public void deleteGrade(Integer id) throws DataAccessException {
        Grade grade = gradeDao.findById(id);
        if (grade == null){
            return;
        }
        gradeDao.delete(grade);
    }

}
