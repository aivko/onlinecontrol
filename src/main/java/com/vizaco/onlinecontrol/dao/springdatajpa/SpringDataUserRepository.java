package com.vizaco.onlinecontrol.dao.springdatajpa;

import com.vizaco.onlinecontrol.dao.UserDao;
import com.vizaco.onlinecontrol.model.User;
import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SpringDataUserRepository extends UserDao, Repository<User, Integer> {

    @Override
    User findByEmail(@Param("email") String email) throws DataAccessException;

    @Override
    User findById(@Param("id") Integer id) throws DataAccessException;

    @Override
    @Query("SELECT user FROM User user")
    List<User> getAllUsers() throws DataAccessException;

}
