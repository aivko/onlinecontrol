package com.vizaco.onlinecontrol.dao.springdatajpa;

import com.vizaco.onlinecontrol.dao.GradeDao;
import com.vizaco.onlinecontrol.dao.RoleDao;
import com.vizaco.onlinecontrol.model.Grade;
import com.vizaco.onlinecontrol.model.Role;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SpringDataGradeRepository extends GradeDao, JpaRepository<Grade, Integer> {

    @Override
    Grade findById(@Param("id") Integer id) throws DataAccessException;

    @Override
    @Query("SELECT grade FROM Grade grade")
    List<Grade> getAllGrades() throws DataAccessException;

}
