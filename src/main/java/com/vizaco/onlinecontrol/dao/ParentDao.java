package com.vizaco.onlinecontrol.dao;

import com.vizaco.onlinecontrol.model.Parent;
import com.vizaco.onlinecontrol.model.User;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface ParentDao {

    Parent findById(Long id) throws DataAccessException;
    
    List<Parent> getAllParents() throws DataAccessException;

    void save(Parent parent) throws DataAccessException;

    void delete(Parent parent) throws DataAccessException;

}
