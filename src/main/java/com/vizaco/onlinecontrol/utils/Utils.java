package com.vizaco.onlinecontrol.utils;

import com.vizaco.onlinecontrol.exceptions.CustomGenericException;
import com.vizaco.onlinecontrol.model.Role;
import com.vizaco.onlinecontrol.model.Student;
import com.vizaco.onlinecontrol.model.User;
import com.vizaco.onlinecontrol.service.RoleService;
import com.vizaco.onlinecontrol.service.StudentService;
import com.vizaco.onlinecontrol.service.UserService;

/**
 * Created by super on 2/20/15.
 */
public class Utils {

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

    public Role getRole(String roleIdStr, RoleService roleService) {

        Long roleId;

        try {
            roleId = Long.valueOf(roleIdStr);
        }catch (NumberFormatException ex){
            throw new CustomGenericException("404", "Page not found for the role with the ID " + roleIdStr);
        }

        Role role = roleService.findRoleById(roleId);

        if (role == null){
            throw new CustomGenericException("404", "Page not found for the role with the ID " + roleId);
        }
        return role;
    }

    public Student getStudent(String studentIdStr, StudentService studentService) {

        Long studentId;

        try {
            studentId = Long.valueOf(studentIdStr);
        }catch (NumberFormatException ex){
            throw new CustomGenericException("404", "Page not found for the student with the ID " + studentIdStr);
        }

        Student student = studentService.findStudentById(studentId);

        if (student == null){
            throw new CustomGenericException("404", "Page not found for the student with the ID " + studentId);
        }
        return student;
    }

}
