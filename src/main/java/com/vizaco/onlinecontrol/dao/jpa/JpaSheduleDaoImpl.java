package com.vizaco.onlinecontrol.dao.jpa;

import com.vizaco.onlinecontrol.dao.ClazzDao;
import com.vizaco.onlinecontrol.dao.SheduleDao;
import com.vizaco.onlinecontrol.model.*;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.DayOfWeek;
import java.util.List;

@Repository
public class JpaSheduleDaoImpl implements SheduleDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Shedule findSheduleById(Long id) {
        Query query = this.em.createQuery("SELECT DISTINCT shedule FROM Shedule shedule WHERE shedule.id =:id");
        query.setParameter("id", id);

        List resultList = query.getResultList();
        if (resultList.isEmpty()) {
            return null; // handle no-results case
        } else {
            return (Shedule)resultList.get(0);
        }
    }

//    @Override
//    public DayOfWeek findDayOfWeekById(Long id) throws DataAccessException {
//        Query query = this.em.createQuery("SELECT DISTINCT DayOfWeek FROM DayOfWeek DayOfWeek WHERE DayOfWeek.id =:id");
//        query.setParameter("id", id);
//
//        List resultList = query.getResultList();
//        if (resultList.isEmpty()) {
//            return null; // handle no-results case
//        } else {
//            return (DayOfWeek)resultList.get(0);
//        }
//    }
//
//    @Override
//    public List<DayOfWeek> getAllDaysOfWeek() throws DataAccessException {
//        Query query = this.em.createQuery("SELECT DISTINCT dayOfWeek FROM DayOfWeek dayOfWeek");
//        return query.getResultList();
//    }

    @Override
    public Period findPeriodById(Long id) throws DataAccessException {
        Query query = this.em.createQuery("SELECT DISTINCT Period FROM Period Period WHERE Period.id =:id");
        query.setParameter("id", id);

        List resultList = query.getResultList();
        if (resultList.isEmpty()) {
            return null; // handle no-results case
        } else {
            return (Period)resultList.get(0);
        }

    }

    @Override
    public List<Period> getAllPeriods() throws DataAccessException {
        Query query = this.em.createQuery("SELECT DISTINCT period FROM Period period");
        return query.getResultList();
    }

    @Override
    public Subject findSubjectById(Long id) throws DataAccessException {
        Query query = this.em.createQuery("SELECT DISTINCT Subject FROM Subject Subject WHERE Subject.id =:id");
        query.setParameter("id", id);

        List resultList = query.getResultList();
        if (resultList.isEmpty()) {
            return null; // handle no-results case
        } else {
            return (Subject)resultList.get(0);
        }

    }

    @Override
    public List<Subject> getAllSubjects() throws DataAccessException {
        Query query = this.em.createQuery("SELECT DISTINCT subject FROM Subject subject");
        return query.getResultList();
    }

    @Override
    public Teacher findTeacherById(Long id) throws DataAccessException {
        Query query = this.em.createQuery("SELECT DISTINCT Teacher FROM Teacher Teacher WHERE Teacher.id =:id");
        query.setParameter("id", id);

        List resultList = query.getResultList();
        if (resultList.isEmpty()) {
            return null; // handle no-results case
        } else {
            return (Teacher)resultList.get(0);
        }

    }

    @Override
    public List<Teacher> getAllTeachers() throws DataAccessException {
        Query query = this.em.createQuery("SELECT DISTINCT teacher FROM Teacher teacher");
        return query.getResultList();
    }

}
