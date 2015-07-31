package com.vizaco.onlinecontrol.dao.jpa;

import com.vizaco.onlinecontrol.dao.ClazzDao;
import com.vizaco.onlinecontrol.dao.RoleDao;
import com.vizaco.onlinecontrol.model.Clazz;
import com.vizaco.onlinecontrol.model.Role;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class JpaClazzDaoImpl implements ClazzDao {

    @PersistenceContext
    private EntityManager em;

    public JpaClazzDaoImpl() {
    }

    public JpaClazzDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Clazz findById(Integer id) {
        Query query = this.em.createQuery("SELECT DISTINCT clazz FROM Clazz clazz WHERE clazz.id =:id");
        query.setParameter("id", id);

        List resultList = query.getResultList();
        if (resultList.isEmpty()) {
            return null; // handle no-results case
        } else {
            return (Clazz)resultList.get(0);
        }
    }

    @Override
    public List<Clazz> getAllClazzes() throws DataAccessException {
        Query query = this.em.createQuery("SELECT DISTINCT clazz FROM Clazz clazz");
        return query.getResultList();
    }


    @Override
    public void save(Clazz clazz) {

        if (clazz == null){
            return;
        }

    	if (clazz.getId() == null) {
    		this.em.persist(clazz);
    	}
    	else {
    		this.em.merge(clazz);
    	}

    }

    @Override
    public void delete(Clazz clazz) throws DataAccessException {
        if (clazz == null){
            return;
        }
        this.em.remove(clazz);
    }

}
