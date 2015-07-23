package com.vizaco.onlinecontrol.dao.jpa;

import com.vizaco.onlinecontrol.dao.SheduleDao;
import com.vizaco.onlinecontrol.model.*;
import com.vizaco.onlinecontrol.representation.JournalView;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.Date;
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
            return (Shedule) resultList.get(0);
        }
    }

    @Override
    public List<Shedule> getAllShedule() throws DataAccessException {
        Query query = this.em.createQuery("SELECT DISTINCT Shedule FROM Shedule Shedule");
        return query.getResultList();
    }

    @Override
    public List<Shedule> getSheduleBeetwenIntervalAndClass(Date start, Date end, Clazz clazz) throws DataAccessException {
        Query query = this.em.createQuery("SELECT DISTINCT Shedule FROM Shedule Shedule WHERE Shedule.clazz =:clazz AND Shedule.date between :startDate and :endDate");
        query.setParameter("clazz", clazz);
        query.setParameter("startDate", start);
        query.setParameter("endDate", end);
        return query.getResultList();
    }

    @Override
    public List<Shedule> getSheduleBeetwenInterval(Date start, Date end) throws DataAccessException {
        Query query = this.em.createQuery("SELECT DISTINCT Shedule FROM Shedule Shedule WHERE Shedule.date between :startDate and :endDate");
        query.setParameter("startDate", start);
        query.setParameter("endDate", end);
        return query.getResultList();
    }

    @Override
    public List getSheduleByCriteria(Date start, Date end) throws DataAccessException {

        //Similarly:
        //  SELECT DISTINCT
        //      Shedule, Clazz.students, Grade
        //      FROM Shedule shedule
        //          INNER JOIN shedule.clazz clazz
        //          INNER JOIN clazz.students students
        //          LEFT JOIN shedule.grades grade
        //  WHERE (grade.id IS NULL OR grade.shedule.id = shedule.id) and (shedule.date BETWEEN :startDate and :endDate)
        CriteriaBuilder cb = this.em.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = cb.createQuery();
        Root<Shedule> shedule = criteriaQuery.from(Shedule.class);
        Join clazz = shedule.join("clazz", JoinType.INNER);
        Join student = clazz.join("students", JoinType.INNER);
        Join grade = student.join("grades", JoinType.LEFT);
        criteriaQuery.select(cb.construct(JournalView.class,
                shedule.get("date"),
                shedule.get("period"),
                shedule.get("subject"),
                shedule.get("clazz"),
                shedule.get("teacher"),
                shedule.get("job"),
                student,
                grade));
        criteriaQuery.where(cb.or(cb.isNull(grade), cb.equal(shedule.get("id"), grade.get("shedule"))), cb.between(shedule.get("date"), start, end));
        Query query = em.createQuery(criteriaQuery);
        List<JournalView> resultList = query.getResultList();

        return resultList;
    }

    @Override
    public Period findPeriodById(Long id) throws DataAccessException {
        Query query = this.em.createQuery("SELECT DISTINCT Period FROM Period Period WHERE Period.id =:id");
        query.setParameter("id", id);

        List resultList = query.getResultList();
        if (resultList.isEmpty()) {
            return null; // handle no-results case
        } else {
            return (Period) resultList.get(0);
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
            return (Subject) resultList.get(0);
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
            return (Teacher) resultList.get(0);
        }

    }

    @Override
    public List<Teacher> getAllTeachers() throws DataAccessException {
        Query query = this.em.createQuery("SELECT DISTINCT teacher FROM Teacher teacher");
        return query.getResultList();
    }

    @Override
    public void saveShedule(Shedule shedule) {

        if (shedule == null) {
            return;
        }

        if (shedule.getId() == null) {
            this.em.persist(shedule);
        } else {
            this.em.merge(shedule);
        }

    }


}
