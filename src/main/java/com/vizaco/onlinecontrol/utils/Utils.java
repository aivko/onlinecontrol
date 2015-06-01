package com.vizaco.onlinecontrol.utils;

import com.vizaco.onlinecontrol.exceptions.CustomGenericException;
import com.vizaco.onlinecontrol.model.*;
import com.vizaco.onlinecontrol.service.*;

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

    public Parent getParent(String parentIdStr, ParentService parentService) {

        Long parentId;

        try {
            parentId = Long.valueOf(parentIdStr);
        }catch (NumberFormatException ex){
            throw new CustomGenericException("404", "Page not found for the parent with the ID " + parentIdStr);
        }

        Parent parent = parentService.findParentById(parentId);

        if (parent == null){
            throw new CustomGenericException("404", "Page not found for the parent with the ID " + parentId);
        }
        return parent;
    }

    public Clazz getClazz(String clazzIdStr, ClazzService clazzService) {

        Long clazzId;

        try {
            clazzId = Long.valueOf(clazzIdStr);
        }catch (NumberFormatException ex){
            throw new CustomGenericException("404", "Page not found for the class with the ID " + clazzIdStr);
        }

        Clazz clazz = clazzService.findClazzById(clazzId);

        if (clazz == null){
            throw new CustomGenericException("404", "Page not found for the student with the ID " + clazzId);
        }
        return clazz;
    }

}
