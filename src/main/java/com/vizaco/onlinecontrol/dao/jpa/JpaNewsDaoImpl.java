package com.vizaco.onlinecontrol.dao.jpa;

import com.vizaco.onlinecontrol.dao.NewsDao;
import com.vizaco.onlinecontrol.dao.RoleDao;
import com.vizaco.onlinecontrol.model.News;
import com.vizaco.onlinecontrol.model.Role;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

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
    public News findById(Integer id) {
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
    public List<News> getAllNews() {
        Query query = this.em.createQuery("SELECT DISTINCT News FROM News News");
        return query.getResultList();
    }

    @Override
    public List<News> findSome(Pageable pageable) {
        Query query = this.em.createQuery("SELECT DISTINCT News FROM News News order by News.date desc");
        query.setMaxResults(pageable.getPageSize());
        return query.getResultList();
    }

    @Override
    public News save(News news) {

        if (news == null){
            return null;
        }

    	if (news.getId() == null) {
    		this.em.persist(news);
    	}
    	else {
    		return this.em.merge(news);
    	}
        return news;
    }

    @Override
    public void delete(News news) {
        if (news == null){
            return;
        }
        this.em.remove(news);
    }

}
