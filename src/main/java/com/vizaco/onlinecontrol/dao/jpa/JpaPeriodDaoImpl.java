package com.vizaco.onlinecontrol.dao.jpa;

import com.vizaco.onlinecontrol.dao.PeriodDao;
import com.vizaco.onlinecontrol.dao.TeacherDao;
import com.vizaco.onlinecontrol.model.Period;
import com.vizaco.onlinecontrol.model.Teacher;
import com.vizaco.onlinecontrol.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class JpaPeriodDaoImpl implements PeriodDao {

    @PersistenceContext
    private EntityManager em;

    public JpaPeriodDaoImpl() {
    }

    public JpaPeriodDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Period findById(Integer id) {
        Query query = this.em.createQuery("SELECT DISTINCT period FROM Period period WHERE period.id =:id");
        query.setParameter("id", id);

        List resultList = query.getResultList();
        if (resultList.isEmpty()) {
            return null; // handle no-results case
        } else {
            return (Period)resultList.get(0);
        }
    }

    @Override
    public List<Period> getAllPeriods() {
        Query query = this.em.createQuery("SELECT DISTINCT period FROM Period period");
        return query.getResultList();
    }

    @Override
    public void save(Period period) {

        if (period == null){
            return;
        }

    	if (period.getId() == null) {
    		this.em.persist(period);
    	}
    	else {
    		this.em.merge(period);
    	}
    }

    @Override
    public void delete(Period period) {
        if (period == null){
            return;
        }
        this.em.remove(period);
    }

}
