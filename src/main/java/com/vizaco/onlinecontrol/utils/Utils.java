package com.vizaco.onlinecontrol.utils;

import com.vizaco.onlinecontrol.exceptions.CustomGenericException;
import com.vizaco.onlinecontrol.model.*;
import com.vizaco.onlinecontrol.representation.JournalView;
import com.vizaco.onlinecontrol.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

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

        Integer userId;

        try {
            userId = Integer.valueOf(userIdStr);
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

        Integer roleId;

        try {
            roleId = Integer.valueOf(roleIdStr);
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

        Integer studentId;

        try {
            studentId = Integer.valueOf(studentIdStr);
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

        Integer parentId;

        try {
            parentId = Integer.valueOf(parentIdStr);
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

        Integer clazzId;

        try {
            clazzId = Integer.valueOf(clazzIdStr);
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

        Integer newsId;

        try {
            newsId = Integer.valueOf(newsIdStr);
        } catch (NumberFormatException ex) {
            throw new CustomGenericException("404", "Page not found for the news with the ID " + newsIdStr);
        }

        News news = newsService.findNewsById(newsId);

        if (news == null) {
            throw new CustomGenericException("404", "Page not found for the news with the ID " + newsId);
        }
        return news;
    }

    public void convertToTreeDate(JournalView journalView, Map<Date, Object> resultData) {

        Date keyDate = journalView.getDate();

        TreeSet<JournalView> setShedule;
        if (resultData.containsKey(keyDate)) {
            setShedule = (TreeSet<JournalView>) resultData.get(keyDate);
            if (setShedule == null) setShedule = new TreeSet<>();
        } else {
            setShedule = new TreeSet<>();
        }
        setShedule.add(journalView);

        resultData.put(keyDate, setShedule);

    }

    public void convertToTreeClazzStudentDate(JournalView journalView, Map<String, Object> resultData) {

        Map<Clazz, Object> clazzShedule;
        if (resultData.containsKey("shedule")) {
            clazzShedule = (Map<Clazz, Object>) resultData.get("shedule");
            if (clazzShedule == null) clazzShedule = new TreeMap<>();
        } else {
            clazzShedule = new TreeMap<>();
        }

        Clazz keyClazz = journalView.getClazz();

        TreeMap<Student, Object> studentShedule;
        if (clazzShedule.containsKey(keyClazz)) {
            studentShedule = (TreeMap<Student, Object>) clazzShedule.get(keyClazz);
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

        Date keyDate = journalView.getDate();

        TreeSet<JournalView> setShedule;
        if (dateShedule.containsKey(keyDate)) {
            setShedule = (TreeSet<JournalView>) dateShedule.get(keyDate);
            if (setShedule == null) setShedule = new TreeSet<>();
        } else {
            setShedule = new TreeSet<>();
        }
        setShedule.add(journalView);
        dateShedule.put(keyDate, setShedule);
        studentShedule.put(keyStudent, dateShedule);
        clazzShedule.put(keyClazz, studentShedule);

        resultData.put("shedule", clazzShedule);

    }

}
