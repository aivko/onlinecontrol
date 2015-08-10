package com.vizaco.onlinecontrol.dao.springdatajpa;

import com.vizaco.onlinecontrol.dao.ParentDao;
import com.vizaco.onlinecontrol.dao.TeacherDao;
import com.vizaco.onlinecontrol.model.Parent;
import com.vizaco.onlinecontrol.model.Teacher;
import com.vizaco.onlinecontrol.model.User;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SpringDataParentRepository extends ParentDao, Repository<Parent, Integer> {

    @Override
    @Query("SELECT parent FROM Parent parent WHERE parent.user =:user")
    Parent findParentByUser(@Param("user") User user) throws DataAccessException;

    @Override
    Parent findById(@Param("id") Integer id) throws DataAccessException;

    @Override
    @Query("SELECT parent FROM Parent parent")
    List<Parent> getAllParents() throws DataAccessException;

}
