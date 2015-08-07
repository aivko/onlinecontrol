package com.vizaco.onlinecontrol.dao.jpa;

import com.vizaco.onlinecontrol.dao.UserDao;
import com.vizaco.onlinecontrol.model.User;
import com.vizaco.onlinecontrol.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
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

    @Override
    public User findByEmail(String email) {
        Query query = this.em.createQuery("SELECT DISTINCT user FROM User user WHERE user.email =:email");
        query.setParameter("email", email);

        List resultList = query.getResultList();
        if (resultList.isEmpty()) {
            return null; // handle no-results case
        } else {
            return (User)resultList.get(0);
        }
        
//        return (User) query.getSingleResult();
    }

    @Override
    public User findById(Integer id) {
        Query query = this.em.createQuery("SELECT DISTINCT user FROM User user WHERE user.id =:id");
        query.setParameter("id", id);

        List resultList = query.getResultList();
        if (resultList.isEmpty()) {
            return null; // handle no-results case
        } else {
            return (User)resultList.get(0);
        }
    }

    @Override
    public List<User> getAllUsers() {
        Query query = this.em.createQuery("SELECT DISTINCT user FROM User user");
        return query.getResultList();
    }

    @Override
    public void save(User user) {

        if (user == null){
            return;
        }

    	if (user.getId() == null) {
    		this.em.persist(user);
    	}
    	else {
    		this.em.merge(user);
        }
    }

    @Override
    public void delete(User user) {
        if (user == null){
            return;
        }
        this.em.remove(user);
    }

}
