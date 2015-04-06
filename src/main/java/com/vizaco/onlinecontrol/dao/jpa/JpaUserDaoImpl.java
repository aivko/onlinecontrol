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

@Repository
public class JpaUserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager em;

    public JpaUserDaoImpl() {
    }

    public JpaUserDaoImpl(EntityManager em) {
        this.em = em;
    }

    public List<User> findByLastName(String lastName) {
        Query query = this.em.createQuery("SELECT DISTINCT user FROM User user left join fetch user.students WHERE user.lastName LIKE :lastName");
        query.setParameter("lastName", lastName);
        List resultList = query.getResultList();
        return resultList;
    }

    @Override
    public User findByLogin(String login) throws DataAccessException {
        Query query = this.em.createQuery("SELECT DISTINCT user FROM User user left join fetch user.students WHERE user.login =:login");
        query.setParameter("login", login);

        List resultList = query.getResultList();
        if (resultList.isEmpty()) {
            return null; // handle no-results case
        } else {
            return (User)resultList.get(0);
        }
        
//        return (User) query.getSingleResult();
    }

    @Override
    public User findById(Long id) {
        Query query = this.em.createQuery("SELECT DISTINCT user FROM User user left join fetch user.students WHERE user.userId =:id");
        query.setParameter("id", id);

        List resultList = query.getResultList();
        if (resultList.isEmpty()) {
            return null; // handle no-results case
        } else {
            return (User)resultList.get(0);
        }
    }

    @Override
    public List<Role> getAllRoles() throws DataAccessException {
        Query query = this.em.createQuery("SELECT DISTINCT role FROM Role role");
        List resultList = query.getResultList();
        return resultList;
    }

    @Override
    public Role getRoleById(Long id) throws DataAccessException {
        Query query = this.em.createQuery("SELECT DISTINCT role FROM Role role WHERE role.roleId =:id");
        query.setParameter("id", id);

        List resultList = query.getResultList();
        if (resultList.isEmpty()) {
            return null; // handle no-results case
        } else {
            return (Role)resultList.get(0);
        }
    }

    @Override
    public List<User> getAllUsers() throws DataAccessException {
        Query query = this.em.createQuery("SELECT DISTINCT user FROM User user");
        List resultList = query.getResultList();
        return resultList;
    }

    @Override
    public void save(User user) {

        if (user == null){
            return;
        }

    	if (user.getUserId() == null) {
    		this.em.persist(user);
    	}
    	else {
    		this.em.merge(user);
    	}
    }

    @Override
    public void delete(User user) throws DataAccessException {
        if (user == null){
            return;
        }
        this.em.remove(user);
    }

}
