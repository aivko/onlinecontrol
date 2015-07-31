package com.vizaco.onlinecontrol.converters;

import com.vizaco.onlinecontrol.model.Clazz;
import com.vizaco.onlinecontrol.model.User;
import com.vizaco.onlinecontrol.service.ClazzService;
import com.vizaco.onlinecontrol.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

/**
 * Converter for Student control.
 *
 * Created: 01.02.2015
 *
 * @author Oleksandr Zamkovyi
 * @since ???
 */
@Service
public class StringToUser implements Converter<String, User> {

    @Autowired
    private UserService userService;

    @Override
    public User convert(String userId) {
        return userService.findUserById(Integer.parseInt(userId));
    }
}