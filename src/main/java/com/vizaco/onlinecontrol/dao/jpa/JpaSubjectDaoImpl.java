package com.vizaco.onlinecontrol.dao.jpa;

import com.vizaco.onlinecontrol.dao.PeriodDao;
import com.vizaco.onlinecontrol.dao.SubjectDao;
import com.vizaco.onlinecontrol.model.Period;
import com.vizaco.onlinecontrol.model.Subject;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class JpaSubjectDaoImpl implements SubjectDao {

    @PersistenceContext
    private EntityManager em;

    public JpaSubjectDaoImpl() {
    }

    public JpaSubjectDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Subject findById(Integer id) {
        Query query = this.em.createQuery("SELECT DISTINCT subject FROM Subject subject WHERE subject.id =:id");
        query.setParameter("id", id);

        List resultList = query.getResultList();
        if (resultList.isEmpty()) {
            return null; // handle no-results case
        } else {
            return (Subject)resultList.get(0);
        }
    }

    @Override
    public List<Subject> getAllSubjects() {
        Query query = this.em.createQuery("SELECT DISTINCT subject FROM Subject subject");
        return query.getResultList();
    }

    @Override
    public void save(Subject subject) {

        if (subject == null){
            return;
        }

    	if (subject.getId() == null) {
    		this.em.persist(subject);
    	}
    	else {
    		this.em.merge(subject);
    	}
    }

    @Override
    public void delete(Subject subject) {
        if (subject == null){
            return;
        }
        this.em.remove(subject);
    }

}
