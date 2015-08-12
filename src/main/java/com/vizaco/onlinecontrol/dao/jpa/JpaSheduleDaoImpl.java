package com.vizaco.onlinecontrol.dao.jpa;

import com.vizaco.onlinecontrol.dao.SheduleDao;
import com.vizaco.onlinecontrol.model.*;
import com.vizaco.onlinecontrol.representation.JournalView;
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
    public List<Shedule> getSheduleByCriteria(Date start, Date end, Clazz clazz, Period period, Subject subject, Teacher teacher) {

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
    public List<JournalView> getJournalByCriteria(Date start, Date end, Student student, Clazz clazz, Period period, Subject subject, Teacher teacher) {

        CriteriaBuilder cb = this.em.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = cb.createQuery();
        Root<Shedule> shedule = criteriaQuery.from(Shedule.class);
        Join clazzJoin = shedule.join("clazz", JoinType.INNER);
        Join studentJoin = clazzJoin.join("students", JoinType.INNER);
        criteriaQuery.select(cb.construct(JournalView.class,
                shedule.get("id"),
                shedule.get("date"),
                shedule.get("period"),
                shedule.get("subject"),
                shedule.get("clazz"),
                shedule.get("teacher"),
                shedule.get("job"),
                studentJoin));

        List<Predicate> predicates = new ArrayList<Predicate>();

        if (start != null | end != null) {
            predicates.add(cb.between(shedule.get("date"), start, end));
        }

        if (clazz != null) {
            predicates.add(cb.equal(shedule.get("clazz"), clazz));
        }

        if (student != null) {
            predicates.add(cb.equal(studentJoin, student));
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
        criteriaQuery.orderBy(cb.asc(shedule.get("clazz")), cb.asc(studentJoin), cb.asc(shedule.get("date")), cb.asc(shedule.get("period")));
        Query query = em.createQuery(criteriaQuery);
        List<JournalView> resultList = query.getResultList();

        return resultList;
    }

    @Override
    public Shedule save(Shedule shedule) {

        if (shedule == null) {
            return null;
        }

        if (shedule.getId() == null) {
            this.em.persist(shedule);
        } else {
            return this.em.merge(shedule);
        }
        return shedule;
    }

    @Override
    public void delete(Shedule shedule) {

        if (shedule == null) {
            return;
        }
        this.em.remove(shedule);

    }

}
