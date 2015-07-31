package com.vizaco.onlinecontrol.dao.jpa;

import com.vizaco.onlinecontrol.dao.SheduleDao;
import com.vizaco.onlinecontrol.model.*;
import com.vizaco.onlinecontrol.representation.JournalView;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class JpaSheduleDaoImpl implements SheduleDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Shedule findSheduleById(Integer id) {
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
    public List<Shedule> getSheduleBeetwenAnyCriteria(Date start, Date end, Clazz clazz, Period period, Subject subject, Teacher teacher) throws DataAccessException {

        CriteriaBuilder cb = this.em.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = cb.createQuery();
        Root<Shedule> shedule = criteriaQuery.from(Shedule.class);
        criteriaQuery.select(shedule);

        List<Predicate> predicates = new ArrayList<Predicate>();

        if (start != null | end != null) {
            predicates.add(cb.between(shedule.get("date"), start, end));
        }

        if (clazz != null) {
            predicates.add(cb.equal(shedule.get("clazz"), clazz));
        }

        if (period != null) {
            predicates.add(cb.equal(shedule.get("period"), period));
        }

        if (subject != null) {
            predicates.add(cb.equal(shedule.get("subject"), subject));
        }

        if (teacher != null) {
            predicates.add(cb.equal(shedule.get("teacher"), teacher));
        }

        criteriaQuery.where(predicates.toArray(new Predicate[]{}));
        Query query = em.createQuery(criteriaQuery);
        List<Shedule> resultList = query.getResultList();

        return resultList;

    }

    @Override
    public List<JournalView> getJournalByCriteria(Date start, Date end) throws DataAccessException {

        //Similarly:
//          SELECT DISTINCT
//              Shedule, Clazz.students, Grade
//              FROM Shedule shedule
//                  INNER JOIN shedule.clazz clazz
//                  INNER JOIN clazz.students students
//                  LEFT JOIN shedule.grades grade and grade.student = student.id
//          WHERE /*(grade.id IS NULL OR grade.shedule.id = shedule.id) and*/ (shedule.date BETWEEN :startDate and :endDate)

        CriteriaBuilder cb = this.em.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = cb.createQuery();
        Root<Shedule> shedule = criteriaQuery.from(Shedule.class);
        Join clazz = shedule.join("clazz", JoinType.INNER);
        Join student = clazz.join("students", JoinType.INNER);
        criteriaQuery.select(cb.construct(JournalView.class,
                shedule.get("id"),
                shedule.get("date"),
                shedule.get("period"),
                shedule.get("subject"),
                shedule.get("clazz"),
                shedule.get("teacher"),
                shedule.get("job"),
                student));
        criteriaQuery.where(cb.between(shedule.get("date"), start, end));
        criteriaQuery.orderBy(cb.asc(shedule.get("clazz")), cb.asc(student), cb.asc(shedule.get("date")), cb.asc(shedule.get("period")));
        Query query = em.createQuery(criteriaQuery);
        List<JournalView> resultList = query.getResultList();

//        Query nativeQuery = this.em.createNativeQuery("" +
//                "SELECT\n" +
//                "  shedule.id         AS shedule_id,\n" +
//                "  shedule.date       AS date,\n" +
//                "  shedule.period_id  AS period_id,\n" +
//                "  period.START_TIME AS period_start_time,\n" +
//                "  period.END_TIME  AS period_end_time,\n" +
//                "  student.id        AS student_id,\n" +
//                "  student.LAST_NAME AS student_last_name,\n" +
//                "  student.FIRST_NAME AS student_first_name,\n" +
//                "  student.MIDDLE_NAME AS student_middle_name,\n" +
//                "  subject.id         AS subject_id,\n" +
//                "  subject.NAME         AS subject,\n" +
//                "  clazz.id   AS clazz_id,\n" +
//                "  clazz.NUMBER   AS clazz_number,\n" +
//                "  clazz.LETTER   AS clazz_letter,\n" +
//                "  teacher.id AS teacher_id,\n" +
//                "  teacher.LAST_NAME AS teacher_last_name,\n" +
//                "  teacher.FIRST_NAME AS teacher_first_name,\n" +
//                "  teacher.MIDDLE_NAME AS teacher_middle_name,\n" +
//                "  shedule.job        AS job,\n" +
//                "  grade.id          AS grade_id,\n" +
//                "  grade.MARK          AS grade_mark,\n" +
//                "  grade.TASK          AS grade_task\n" +
//                "FROM shedule shedule\n" +
//                "  INNER JOIN clazzes clazz ON shedule.clazz_id = clazz.id\n" +
//                "  INNER JOIN students student ON shedule.clazz_id = student.clazz_id\n" +
//                "  INNER JOIN periods period ON shedule.period_id = period.id\n" +
//                "  INNER JOIN subjects subject ON shedule.subject_id = subject.id\n" +
//                "  INNER JOIN teachers teacher ON shedule.teacher_id = teacher.id\n" +
//                "  LEFT OUTER JOIN grades grade ON shedule.id = grade.shedule_id AND (student.id = grade.STUDENT_ID)\n" +
//                "WHERE shedule.date BETWEEN :startDate and :endDate\n" +
//                "ORDER BY shedule.clazz_id ASC, student.id ASC, shedule.date ASC, shedule.period_id ASC");
//        nativeQuery.setParameter("startDate", start);
//        nativeQuery.setParameter("endDate", end);
//
//        List<Object[]> listObjects = nativeQuery.getResultList();
//
//        ArrayList<JournalView> resultList = new ArrayList<>();
//        for (Object[] objects : listObjects) {
//            resultList.add(new JournalView(objects));
//        }

        return resultList;
    }

    @Override
    public Period findPeriodById(Integer id) throws DataAccessException {
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
    public Subject findSubjectById(Integer id) throws DataAccessException {
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
    public Teacher findTeacherById(Integer id) throws DataAccessException {
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
    public void saveShedule(Shedule shedule) throws DataAccessException {

        if (shedule == null) {
            return;
        }

        if (shedule.getId() == null) {
            this.em.persist(shedule);
        } else {
            this.em.merge(shedule);
        }

    }

    @Override
    public void deleteShedule(Shedule shedule) throws DataAccessException {

        if (shedule == null) {
            return;
        }
        this.em.remove(shedule);

    }

}
