package com.vizaco.onlinecontrol.service;

import com.vizaco.onlinecontrol.model.Clazz;
import com.vizaco.onlinecontrol.model.Role;
import org.springframework.dao.DataAccessException;

import java.util.Collection;
import java.util.List;


/**
 * Mostly used as a facade for all OnlineControl controllers
 *
 */
public interface ClazzService {

    Clazz findClazzById(Long id) throws DataAccessException;

    Collection<Clazz> findClazzByName(String number, String letter) throws DataAccessException;

    List<Clazz> getAllClazzes() throws DataAccessException;

    void saveClazz(Clazz clazz) throws DataAccessException;

    void deleteClazz(Long id) throws DataAccessException;

}
