package com.vizaco.onlinecontrol.dao;

import com.vizaco.onlinecontrol.model.Person;
import com.vizaco.onlinecontrol.model.Role;
import com.vizaco.onlinecontrol.model.User;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface BusinessDao {

    Person getCurrentPerson(User user) throws DataAccessException;

}
