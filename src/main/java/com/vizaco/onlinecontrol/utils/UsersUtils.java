package com.vizaco.onlinecontrol.utils;

import com.vizaco.onlinecontrol.exceptions.CustomGenericException;
import com.vizaco.onlinecontrol.model.User;
import com.vizaco.onlinecontrol.service.UserService;

/**
 * Created by super on 2/20/15.
 */
public class UsersUtils {

    public User getUser(String userIdStr, UserService userService) {

        Long userId;

        try {
            userId = Long.valueOf(userIdStr);
        }catch (NumberFormatException ex){
            throw new CustomGenericException("404", "Page not found for the user with the ID " + userIdStr);
        }

        User user = userService.findUserById(userId);

        if (user == null){
            throw new CustomGenericException("404", "Page not found for the user with the ID " + userId);
        }
        return user;
    }

}
