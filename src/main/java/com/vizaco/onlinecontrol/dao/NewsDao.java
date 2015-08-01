package com.vizaco.onlinecontrol.dao;

import com.vizaco.onlinecontrol.model.News;
import com.vizaco.onlinecontrol.model.Role;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface NewsDao {

    News findById(Integer id) throws DataAccessException;

    List<News> getAllNews() throws DataAccessException;

    List<News> getSomeNews(int maxResult) throws DataAccessException;

    void save(News news) throws DataAccessException;

    void delete(News news) throws DataAccessException;

}
