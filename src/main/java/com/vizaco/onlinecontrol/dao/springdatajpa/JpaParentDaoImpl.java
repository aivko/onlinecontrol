package com.vizaco.onlinecontrol.dao.springdatajpa;

import com.vizaco.onlinecontrol.dao.ParentDao;
import com.vizaco.onlinecontrol.model.Parent;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class JpaParentDaoImpl implements ParentDao {

    @PersistenceContext
    private EntityManager em;

    public JpaParentDaoImpl() {
    }

    public JpaParentDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Parent findById(Integer id) {
        Query query = this.em.createQuery("SELECT DISTINCT parent FROM Parent parent left join fetch parent.students WHERE parent.id =:id");
        query.setParameter("id", id);

        List resultList = query.getResultList();
        if (resultList.isEmpty()) {
            return null; // handle no-results case
        } else {
            return (Parent)resultList.get(0);
        }
    }

    @Override
    public List<Parent> getAllParents() throws DataAccessException {
        Query query = this.em.createQuery("SELECT DISTINCT parent FROM Parent parent left join fetch parent.students");
        return query.getResultList();
    }

    @Override
    public void save(Parent parent) {

        if (parent == null){
            return;
        }

    	if (parent.getId() == null) {
    		this.em.persist(parent);
    	}
    	else {
    		this.em.merge(parent);
    	}
    }

    @Override
    public void delete(Parent parent) throws DataAccessException {
        if (parent == null){
            return;
        }
        this.em.remove(parent);
    }

}
