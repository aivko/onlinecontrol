package com.vizaco.onlinecontrol.service;

import com.vizaco.onlinecontrol.model.News;
import com.vizaco.onlinecontrol.model.Role;
import org.springframework.dao.DataAccessException;

import java.util.Collection;
import java.util.List;


/**
 * Mostly used as a facade for all OnlineControl controllers
 *
 */
public interface NewsService {

    News findNewsById(Integer id) throws DataAccessException;

    List<News> getAllNews() throws DataAccessException;

    List<News> getSomeNews(int maxResult) throws DataAccessException;

    void saveNews(News news) throws DataAccessException;

    void deleteNews(Integer id) throws DataAccessException;

}
