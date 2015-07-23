package com.vizaco.onlinecontrol.service;

import com.vizaco.onlinecontrol.model.Person;
import com.vizaco.onlinecontrol.model.Role;
import com.vizaco.onlinecontrol.model.User;
import org.springframework.dao.DataAccessException;

import java.util.Collection;
import java.util.List;


/**
 * Mostly used as a facade for all OnlineControl controllers
 *
 */
public interface BusinessService {

    Person getCurrentPerson(User user) throws DataAccessException;

}
