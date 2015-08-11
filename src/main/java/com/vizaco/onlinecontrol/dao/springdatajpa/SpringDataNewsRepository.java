package com.vizaco.onlinecontrol.dao.springdatajpa;

import com.vizaco.onlinecontrol.dao.NewsDao;
import com.vizaco.onlinecontrol.dao.RoleDao;
import com.vizaco.onlinecontrol.model.News;
import com.vizaco.onlinecontrol.model.Role;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SpringDataNewsRepository extends NewsDao, JpaRepository<News, Integer> {

    @Override
    News findById(@Param("id") Integer id) throws DataAccessException;

    @Override
    @Query("SELECT news FROM News news order by news.date desc")
    List<News> findSome(Pageable pageable) throws DataAccessException;

    @Override
    @Query("SELECT news FROM News news")
    List<News> getAllNews() throws DataAccessException;

}
