package com.vizaco.onlinecontrol.service.impl;

import com.vizaco.onlinecontrol.dao.NewsDao;
import com.vizaco.onlinecontrol.dao.RoleDao;
import com.vizaco.onlinecontrol.model.News;
import com.vizaco.onlinecontrol.model.Role;
import com.vizaco.onlinecontrol.service.NewsService;
import com.vizaco.onlinecontrol.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsDao newsDao;

    @Override
    @Transactional(readOnly = true)
    public News findNewsById(Integer id) throws DataAccessException {
        return newsDao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<News> getAllNews() throws DataAccessException {
        return newsDao.getAllNews();
    }

    @Override
    @Transactional(readOnly = true)
    public List<News> getSomeNews(int maxResult) throws DataAccessException {
        return newsDao.getSomeNews(maxResult);
    }

    @Override
    @Transactional
    public void saveNews(News news) throws DataAccessException {
        newsDao.save(news);
    }

    @Override
    @Transactional
    public void deleteNews(Integer id) throws DataAccessException {
        News news = newsDao.findById(id);
        if (news == null){
            return;
        }
        newsDao.delete(news);
    }

}
