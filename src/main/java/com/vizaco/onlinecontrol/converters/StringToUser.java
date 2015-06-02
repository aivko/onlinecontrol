package com.vizaco.onlinecontrol.converters;

import com.vizaco.onlinecontrol.model.Clazz;
import com.vizaco.onlinecontrol.model.User;
import com.vizaco.onlinecontrol.service.ClazzService;
import com.vizaco.onlinecontrol.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

/**
 * Converter for Student control.
 *
 * Created: 01.02.2015
 *
 * @author Oleksandr Zamkovyi
 * @since ???
 */
public class StringToUser implements Converter<String, User> {

    private UserService userService;

    @Autowired
    public StringToUser(UserService userService) {
        this.userService = userService;
    }

    @Override
    public User convert(String userId) {
        return userService.findUserById(Long.parseLong(userId));
    }
}