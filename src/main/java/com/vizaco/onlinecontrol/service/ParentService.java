package com.vizaco.onlinecontrol.service;

import com.vizaco.onlinecontrol.model.Parent;
import com.vizaco.onlinecontrol.model.User;
import org.springframework.dao.DataAccessException;

import java.util.Collection;
import java.util.List;


/**
 * Mostly used as a facade for all OnlineControl controllers
 *
 */
public interface ParentService {

    Parent findParentById(Integer id) throws DataAccessException;

    List<Parent> getAllParents() throws DataAccessException;

    void saveParent(Parent parent) throws DataAccessException;

    void deleteParent(Integer id) throws DataAccessException;


}
