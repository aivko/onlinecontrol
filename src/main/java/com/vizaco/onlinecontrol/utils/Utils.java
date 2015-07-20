package com.vizaco.onlinecontrol.utils;

import com.vizaco.onlinecontrol.exceptions.CustomGenericException;
import com.vizaco.onlinecontrol.model.*;
import com.vizaco.onlinecontrol.representation.JournalView;
import com.vizaco.onlinecontrol.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.reflect.Reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

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
        } catch (NumberFormatException ex) {
            throw new CustomGenericException("404", "Page not found for the user with the ID " + userIdStr);
        }

        User user = userService.findUserById(userId);

        if (user == null) {
            throw new CustomGenericException("404", "Page not found for the user with the ID " + userId);
        }
        return user;
    }

    public Role getRole(String roleIdStr) {

        Long roleId;

        try {
            roleId = Long.valueOf(roleIdStr);
        } catch (NumberFormatException ex) {
            throw new CustomGenericException("404", "Page not found for the role with the ID " + roleIdStr);
        }

        Role role = roleService.findRoleById(roleId);

        if (role == null) {
            throw new CustomGenericException("404", "Page not found for the role with the ID " + roleId);
        }
        return role;
    }

    public Student getStudent(String studentIdStr) {

        Long studentId;

        try {
            studentId = Long.valueOf(studentIdStr);
        } catch (NumberFormatException ex) {
            throw new CustomGenericException("404", "Page not found for the student with the ID " + studentIdStr);
        }

        Student student = studentService.findStudentById(studentId);

        if (student == null) {
            throw new CustomGenericException("404", "Page not found for the student with the ID " + studentId);
        }
        return student;
    }

    public Parent getParent(String parentIdStr) {

        Long parentId;

        try {
            parentId = Long.valueOf(parentIdStr);
        } catch (NumberFormatException ex) {
            throw new CustomGenericException("404", "Page not found for the parent with the ID " + parentIdStr);
        }

        Parent parent = parentService.findParentById(parentId);

        if (parent == null) {
            throw new CustomGenericException("404", "Page not found for the parent with the ID " + parentId);
        }
        return parent;
    }

    public Clazz getClazz(String clazzIdStr) {

        Long clazzId;

        try {
            clazzId = Long.valueOf(clazzIdStr);
        } catch (NumberFormatException ex) {
            throw new CustomGenericException("404", "Page not found for the class with the ID " + clazzIdStr);
        }

        Clazz clazz = clazzService.findClazzById(clazzId);

        if (clazz == null) {
            throw new CustomGenericException("404", "Page not found for the class with the ID " + clazzId);
        }
        return clazz;
    }

    public News getNews(String newsIdStr) {

        Long newsId;

        try {
            newsId = Long.valueOf(newsIdStr);
        } catch (NumberFormatException ex) {
            throw new CustomGenericException("404", "Page not found for the news with the ID " + newsIdStr);
        }

        News news = newsService.findNewsById(newsId);

        if (news == null) {
            throw new CustomGenericException("404", "Page not found for the news with the ID " + newsId);
        }
        return news;
    }

    public void convertToTree(JournalView journalView, Map<Clazz, Object> resultData) throws NoSuchFieldException, IllegalAccessException {

        Clazz keyClazz = journalView.getClazz();

        TreeMap<Student, Object> studentShedule;
        if (resultData.containsKey(keyClazz)) {
            studentShedule = (TreeMap<Student, Object>) resultData.get(keyClazz);
            if (studentShedule == null) studentShedule = new TreeMap<>();
        } else {
            studentShedule = new TreeMap<>();
        }

        Student keyStudent = journalView.getStudent();

        TreeMap<Date, Object> dateShedule;
        if (studentShedule.containsKey(keyStudent)) {
            dateShedule = (TreeMap<Date, Object>) studentShedule.get(keyStudent);
            if (dateShedule == null) dateShedule = new TreeMap<>();
        } else {
            dateShedule = new TreeMap<>();
        }

        Period keyPeriod = journalView.getPeriod();

        TreeMap<Subject, Object> subjectShedule;
        if (dateShedule.containsKey(keyPeriod)) {
            subjectShedule = (TreeMap<Subject, Object>) dateShedule.get(keyPeriod);
            if (subjectShedule == null) subjectShedule = new TreeMap<>();
        } else {
            subjectShedule = new TreeMap<>();
        }

        Subject keySubject = journalView.getSubject();

        TreeMap<Teacher, Object> teacherShedule;
        if (subjectShedule.containsKey(keyPeriod)) {
            teacherShedule = (TreeMap<Teacher, Object>) subjectShedule.get(keyPeriod);
            if (teacherShedule == null) teacherShedule = new TreeMap<>();
        } else {
            teacherShedule = new TreeMap<>();
        }

        Teacher keyTeacher = journalView.getTeacher();

        TreeSet<JournalView> setShedule;
        if (teacherShedule.containsKey(keyTeacher)) {
            setShedule = (TreeSet<JournalView>) teacherShedule.get(keyTeacher);
            if (setShedule == null) setShedule = new TreeSet<>();
        } else {
            setShedule = new TreeSet<>();
        }
        setShedule.add(journalView);
//        dateShedule.put(keyDate, setShedule);

        resultData.put(keyClazz, dateShedule);


    }

}
