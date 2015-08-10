package com.vizaco.onlinecontrol.service.impl;

import com.vizaco.onlinecontrol.dao.SheduleDao;
import com.vizaco.onlinecontrol.model.*;
import com.vizaco.onlinecontrol.representation.JournalView;
import com.vizaco.onlinecontrol.service.SheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Service
public class SheduleServiceImpl implements SheduleService {

    @Autowired
    private SheduleDao sheduleDao;

    @Override
    @Transactional(readOnly = true)
    public Shedule findSheduleById(Integer id) throws DataAccessException {
        return sheduleDao.findSheduleById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Shedule> getSheduleBeetwenAnyCriteria(Date start, Date end, Clazz clazz, Period period, Subject subject, Teacher teacher) throws DataAccessException {
        return sheduleDao.getSheduleBeetwenAnyCriteria(start, end, clazz, period, subject, teacher);
    }

    @Override
    @Transactional(readOnly = true)
    public List<JournalView> getJournalByCriteria(Date start, Date end, Student student, Clazz clazz, Period period, Subject subject, Teacher teacher) throws DataAccessException {
        List<JournalView> journalByCriteria = sheduleDao.getJournalByCriteria(start, end, student, clazz, period, subject, teacher);
        for (JournalView deleteJournalView : journalByCriteria) {
            TreeSet<Grade> correctGrades = new TreeSet<>();
            Set<Grade> grades;
            if (deleteJournalView.getStudent() == null || (grades = deleteJournalView.getStudent().getGrades()) == null){
                continue;
            }
            for (Grade grade : grades) {
                if (grade.getShedule() != null && deleteJournalView.getSheduleId() == grade.getShedule().getId()){
                    correctGrades.add(grade);
                }
            }
            deleteJournalView.setGrades(correctGrades);
        }
        return journalByCriteria;
    }

    @Override
    @Transactional
    public void saveShedule(Shedule shedule) throws DataAccessException {
        sheduleDao.save(shedule);
    }

    @Override
    @Transactional
    public void deleteShedule(Integer id) throws DataAccessException {
        Shedule shedule = sheduleDao.findSheduleById(id);
        if (shedule == null){
            return;
        }
        sheduleDao.delete(shedule);
    }

}
