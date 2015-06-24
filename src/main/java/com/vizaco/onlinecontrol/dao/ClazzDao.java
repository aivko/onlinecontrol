package com.vizaco.onlinecontrol.dao;

import com.vizaco.onlinecontrol.model.Clazz;
import com.vizaco.onlinecontrol.model.Role;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface ClazzDao {

    Clazz findById(Long id) throws DataAccessException;

    List<Clazz> findByName(String number, String letter) throws DataAccessException;

    List<Clazz> getAllClazzes() throws DataAccessException;

    void save(Clazz clazz) throws DataAccessException;

    void delete(Clazz clazz) throws DataAccessException;

}
