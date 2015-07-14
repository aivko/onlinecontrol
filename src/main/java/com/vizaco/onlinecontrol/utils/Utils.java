package com.vizaco.onlinecontrol.utils;

import com.vizaco.onlinecontrol.exceptions.CustomGenericException;
import com.vizaco.onlinecontrol.model.*;
import com.vizaco.onlinecontrol.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by super on 2/20/15.
 */
@Service
public class Utils {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private ParentService parentService;
    @Autowired
    private ClazzService clazzService;
    @Autowired
    private NewsService newsService;

    public User getUser(String userIdStr) {

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

    public Role getRole(String roleIdStr) {

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

    public Student getStudent(String studentIdStr) {

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

    public Parent getParent(String parentIdStr) {

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

    public Clazz getClazz(String clazzIdStr) {

        Long clazzId;

        try {
            clazzId = Long.valueOf(clazzIdStr);
        }catch (NumberFormatException ex){
            throw new CustomGenericException("404", "Page not found for the class with the ID " + clazzIdStr);
        }

        Clazz clazz = clazzService.findClazzById(clazzId);

        if (clazz == null){
            throw new CustomGenericException("404", "Page not found for the class with the ID " + clazzId);
        }
        return clazz;
    }

    public News getNews(String newsIdStr) {

        Long newsId;

        try {
            newsId = Long.valueOf(newsIdStr);
        }catch (NumberFormatException ex){
            throw new CustomGenericException("404", "Page not found for the news with the ID " + newsIdStr);
        }

        News news = newsService.findNewsById(newsId);

        if (news == null){
            throw new CustomGenericException("404", "Page not found for the news with the ID " + newsId);
        }
        return news;
    }

}
