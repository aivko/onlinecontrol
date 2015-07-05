package com.vizaco.onlinecontrol.dao.jpa;

import com.vizaco.onlinecontrol.dao.NewsDao;
import com.vizaco.onlinecontrol.dao.RoleDao;
import com.vizaco.onlinecontrol.model.News;
import com.vizaco.onlinecontrol.model.Role;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class JpaNewsDaoImpl implements NewsDao {

    @PersistenceContext
    private EntityManager em;

    public JpaNewsDaoImpl() {
    }

    public JpaNewsDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public News findById(Long id) {
        Query query = this.em.createQuery("SELECT DISTINCT News FROM News News WHERE News.id =:id");
        query.setParameter("id", id);

        List resultList = query.getResultList();
        if (resultList.isEmpty()) {
            return null; // handle no-results case
        } else {
            return (News)resultList.get(0);
        }
    }

    @Override
    public List<News> getAllNews() throws DataAccessException {
        Query query = this.em.createQuery("SELECT DISTINCT News FROM News News");
        return query.getResultList();
    }

    @Override
    public List<News> getSomeNews(int maxResult) throws DataAccessException {
        Query query = this.em.createQuery("SELECT DISTINCT News FROM News News order by News.date desc");
        query.setMaxResults(maxResult);
        return query.getResultList();
    }

    @Override
    public void save(News news) {

        if (news == null){
            return;
        }

    	if (news.getId() == null) {
    		this.em.persist(news);
    	}
    	else {
    		this.em.merge(news);
    	}

    }

    @Override
    public void delete(News news) throws DataAccessException {
        if (news == null){
            return;
        }
        this.em.remove(news);
    }

}
