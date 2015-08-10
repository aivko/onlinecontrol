package com.vizaco.onlinecontrol.dao.springdatajpa;

import com.vizaco.onlinecontrol.dao.ClazzDao;
import com.vizaco.onlinecontrol.dao.TeacherDao;
import com.vizaco.onlinecontrol.model.Clazz;
import com.vizaco.onlinecontrol.model.Teacher;
import com.vizaco.onlinecontrol.model.User;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SpringDataClazzRepository extends ClazzDao, Repository<Clazz, Integer> {

    @Override
    Clazz findById(@Param("id") Integer id) throws DataAccessException;

    @Override
    @Query("SELECT clazz FROM Clazz clazz")
    List<Clazz> getAllClazzes() throws DataAccessException;

}
