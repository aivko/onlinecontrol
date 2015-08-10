package com.vizaco.onlinecontrol.service.impl;

import com.vizaco.onlinecontrol.dao.PeriodDao;
import com.vizaco.onlinecontrol.dao.SubjectDao;
import com.vizaco.onlinecontrol.model.Period;
import com.vizaco.onlinecontrol.model.Subject;
import com.vizaco.onlinecontrol.service.PeriodService;
import com.vizaco.onlinecontrol.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectDao subjectDao;

    @Override
    @Transactional(readOnly = true)
    public Subject findSubjectById(Integer id) throws DataAccessException {
        return subjectDao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Subject> getAllSubjects() throws DataAccessException {
        return subjectDao.getAllSubjects();
    }

    @Override
    @Transactional
    public void saveSubject(Subject subject) throws DataAccessException {
        subjectDao.save(subject);
    }

    @Override
    @Transactional
    public void deleteSubject(Integer id) throws DataAccessException {
        Subject subject = subjectDao.findById(id);
        if (subject == null){
            return;
        }
        subjectDao.delete(subject);
    }

}
