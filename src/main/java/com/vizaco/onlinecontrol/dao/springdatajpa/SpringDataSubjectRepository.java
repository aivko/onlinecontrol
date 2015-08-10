package com.vizaco.onlinecontrol.dao.springdatajpa;

import com.vizaco.onlinecontrol.dao.PeriodDao;
import com.vizaco.onlinecontrol.dao.SubjectDao;
import com.vizaco.onlinecontrol.model.Period;
import com.vizaco.onlinecontrol.model.Subject;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SpringDataSubjectRepository extends SubjectDao, Repository<Subject, Integer> {

    @Override
    Subject findById(@Param("id") Integer id) throws DataAccessException;

    @Override
    @Query("SELECT subject FROM Subject subject")
    List<Subject> getAllSubjects() throws DataAccessException;

}
