package com.vizaco.onlinecontrol.dao.jpa;

import com.vizaco.onlinecontrol.dao.BusinessDao;
import com.vizaco.onlinecontrol.dao.RoleDao;
import com.vizaco.onlinecontrol.model.Person;
import com.vizaco.onlinecontrol.model.Role;
import com.vizaco.onlinecontrol.model.User;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class JpaBusinessDaoImpl implements BusinessDao {

    @PersistenceContext
    private EntityManager em;

    public JpaBusinessDaoImpl() {
    }

    public JpaBusinessDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Person getCurrentPerson(User user) throws DataAccessException {

        //TODO: need optimisation
        Query query;
        List resultList;
        query = this.em.createQuery("SELECT DISTINCT student FROM Student student where student.user =:user");
        query.setParameter("user", user);
        resultList = query.getResultList();
        if (!resultList.isEmpty()) {
            return (Person)resultList.get(0);
        }
        query = this.em.createQuery("SELECT DISTINCT teacher FROM Teacher teacher where teacher.user =:user");
        query.setParameter("user", user);
        resultList = query.getResultList();
        if (!resultList.isEmpty()) {
            return (Person)resultList.get(0);
        }
        query = this.em.createQuery("SELECT DISTINCT parent FROM Parent parent where parent.user =:user");
        query.setParameter("user", user);
        resultList = query.getResultList();
        if (!resultList.isEmpty()) {
            return (Person)resultList.get(0);
        }
        return null;

    }

}
