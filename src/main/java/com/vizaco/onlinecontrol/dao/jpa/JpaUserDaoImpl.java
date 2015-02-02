package com.vizaco.onlinecontrol.dao.jpa;

import com.vizaco.onlinecontrol.dao.UserDao;
import com.vizaco.onlinecontrol.model.Role;
import com.vizaco.onlinecontrol.model.User;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Repository
public class JpaUserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager em;

    public JpaUserDaoImpl() {
    }

    public Collection<User> findByLastName(String lastName) {
        Query query = this.em.createQuery("SELECT DISTINCT user FROM User user left join fetch user.students WHERE user.lastName LIKE :lastName");
        query.setParameter("lastName", lastName);
        List resultList = query.getResultList();
        return query.getResultList();
    }

    @Override
    public User findByLogin(String login) throws DataAccessException {
        Query query = this.em.createQuery("SELECT DISTINCT user FROM User user left join fetch user.students WHERE user.login =:login");
        query.setParameter("login", login);
        return (User) query.getSingleResult();
    }

    @Override
    public User findById(String id) {
        Query query = this.em.createQuery("SELECT DISTINCT user FROM User user left join fetch user.students WHERE user.userId =:id");
        query.setParameter("id", id);
        return (User) query.getSingleResult();
    }

    @Override
    public List<Role> getAllRoles() throws DataAccessException {
        Query query = this.em.createQuery("SELECT DISTINCT role FROM Role role");
        List resultList = query.getResultList();
        return query.getResultList();
    }

    @Override
    public Role getRoleById(String id) throws DataAccessException {
        Query query = this.em.createQuery("SELECT DISTINCT role FROM Role role WHERE role.roleId =:id");
        query.setParameter("id", id);
        return (Role) query.getSingleResult();
    }

    @Override
    public List<User> getAllUsers() throws DataAccessException {
        Query query = this.em.createQuery("SELECT DISTINCT user FROM User user");
        List resultList = query.getResultList();
        return query.getResultList();
    }


    @Override
    @Transactional
    public void save(User user) {
    	if (user.getUserId() == null) {
    		this.em.persist(user);
    	}
    	else {
    		this.em.merge(user);
    	}

    }

}
