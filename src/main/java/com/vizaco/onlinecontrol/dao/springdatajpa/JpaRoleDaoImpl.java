package com.vizaco.onlinecontrol.dao.springdatajpa;

import com.vizaco.onlinecontrol.dao.RoleDao;
import com.vizaco.onlinecontrol.model.Role;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class JpaRoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager em;

    public JpaRoleDaoImpl() {
    }

    public JpaRoleDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Role findById(Integer id) {
        Query query = this.em.createQuery("SELECT DISTINCT role FROM Role role WHERE role.id =:id");
        query.setParameter("id", id);

        List resultList = query.getResultList();
        if (resultList.isEmpty()) {
            return null; // handle no-results case
        } else {
            return (Role)resultList.get(0);
        }
    }

    @Override
    public List<Role> getAllRoles() throws DataAccessException {
        Query query = this.em.createQuery("SELECT DISTINCT role FROM Role role");
        return query.getResultList();
    }


    @Override
    public void save(Role role) {

        if (role == null){
            return;
        }

    	if (role.getId() == null) {
    		this.em.persist(role);
    	}
    	else {
    		this.em.merge(role);
    	}

    }

    @Override
    public void delete(Role role) throws DataAccessException {
        if (role == null){
            return;
        }
        this.em.remove(role);
    }

}
