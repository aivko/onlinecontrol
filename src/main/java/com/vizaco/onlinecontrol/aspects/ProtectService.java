package com.vizaco.onlinecontrol.aspects;

import com.vizaco.onlinecontrol.exceptions.CustomGenericException;
import com.vizaco.onlinecontrol.model.*;
import com.vizaco.onlinecontrol.representation.JournalView;
import com.vizaco.onlinecontrol.service.UserService;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

//TODO: refactor
@Component
@Aspect
public class ProtectService {

    @Autowired
    AccessDecisionManager accessDecisionManager;

    @Autowired
    UserService userService;


    @Before("(execution(* com.vizaco.onlinecontrol.service.*.save*(..)) " +
            "|| execution(* com.vizaco.onlinecontrol.service.*.delete*(..))) " +
            "&& !com.vizaco.onlinecontrol.aspects.Pointcuts.checkPermissionsSaveDeleteShedule()" +
            "&& !com.vizaco.onlinecontrol.aspects.Pointcuts.checkPermissionsSaveDeleteGrade()")
    public void checkPermissionsSaveDelete() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        accessDecisionManager.decide(authentication, null, SecurityConfig.createList("ROLE_ADMIN"));
    }

    @AfterReturning(pointcut = "execution(java.util.List<com.vizaco.onlinecontrol.representation.JournalView> com.vizaco.onlinecontrol.service..*(..))" +
            "&& !com.vizaco.onlinecontrol.aspects.Pointcuts.checkPermissionsFindByUser()", returning = "retVal")
    public void filterJournalViewList(List<JournalView> retVal) {

        if (retVal == null) {
            return;
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!correctAuthentication(authentication)) {
            retVal = null;
            return;
        }

        if (haveFullAccess(authentication)) return;

        Person currentPerson = userService.getCurrentPerson((User) authentication.getPrincipal());

        if (currentPerson == null) {
            retVal = null;
            return;
        }

        filterJournalResult(retVal, currentPerson);

    }

    @AfterReturning(pointcut = "execution(java.util.List<com.vizaco.onlinecontrol.model.Student> com.vizaco.onlinecontrol.service..*(..))" +
            "&& !com.vizaco.onlinecontrol.aspects.Pointcuts.checkPermissionsFindByUser()", returning = "retVal")
    public void filterStudentsList(List<Student> retVal) {

        if (retVal == null) {
            return;
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!correctAuthentication(authentication)) {
            retVal = null;
            return;
        }

        if (haveFullAccess(authentication)) return;

        Person currentPerson = userService.getCurrentPerson((User) authentication.getPrincipal());

        if (currentPerson == null) {
            retVal = null;
            return;
        }

        filterStudentsListResult(retVal, currentPerson);

    }

    @AfterReturning(pointcut = "execution(com.vizaco.onlinecontrol.model.Student com.vizaco.onlinecontrol.service..*(..))" +
            "&& !com.vizaco.onlinecontrol.aspects.Pointcuts.checkPermissionsFindByUser()", returning = "retVal")
    public void filterStudent(Student retVal) {

        if (retVal == null) {
            return;
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!correctAuthentication(authentication)) {
            retVal = null;
            return;
        }

        if (haveFullAccess(authentication)) return;

        Person currentPerson = userService.getCurrentPerson((User) authentication.getPrincipal());

        if (currentPerson == null) {
            retVal = null;
            return;
        }

        retVal = filterStudentResult(retVal, currentPerson);

    }

    @AfterReturning(pointcut = "execution(java.util.List<com.vizaco.onlinecontrol.model.User> com.vizaco.onlinecontrol.service..*(..))" +
            "&& !com.vizaco.onlinecontrol.aspects.Pointcuts.checkPermissionsFindByUser()", returning = "retVal")
    public void filterUsersList(List<User> retVal) {

        if (retVal == null) {
            return;
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!correctAuthentication(authentication)) {
            retVal = null;
            return;
        }

        if (haveFullAccess(authentication)) return;

        Person currentPerson = userService.getCurrentPerson((User) authentication.getPrincipal());

        if (currentPerson == null) {
            retVal = null;
            return;
        }

        filterUsersListResult(retVal, currentPerson);

    }

    @AfterReturning(pointcut = "execution(com.vizaco.onlinecontrol.model.User com.vizaco.onlinecontrol.service..*(..))" +
            "&& !com.vizaco.onlinecontrol.aspects.Pointcuts.checkPermissionsFindByUser()", returning = "retVal")
    public void filterUser(User retVal) {

        if (retVal == null) {
            return;
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!correctAuthentication(authentication)) {
            retVal = null;
            return;
        }

        if (haveFullAccess(authentication)) return;

        Person currentPerson = userService.getCurrentPerson((User) authentication.getPrincipal());

        if (currentPerson == null) {
            retVal = null;
            return;
        }

        retVal = filterUserResult(retVal, currentPerson);

    }

    private void filterStudentsListResult(List<Student> currentVal, Person currentPerson) {

        if (currentPerson instanceof Student) {

            Student student = (Student) currentPerson;

            Iterator<Student> iterator = currentVal.iterator();
            while (iterator.hasNext()) {
                Student studentData = iterator.next();
                if (studentData == null || !studentData.equals(student)) {
                    iterator.remove();
                }
            }

        } else if (currentPerson instanceof Teacher) {

            Teacher teacher = (Teacher) currentPerson;

            Iterator<Student> iterator = currentVal.iterator();
            while (iterator.hasNext()) {
                Student studentData = iterator.next();
                if (studentData == null || !teacher.getClazzes().contains(studentData.getClazz())) {
                    iterator.remove();
                }
            }

        } else if (currentPerson instanceof Parent) {

            Parent parent = (Parent) currentPerson;
            Set<Student> accessStudents = parent.getStudents();
            if (accessStudents == null) {
                currentVal = null;
            }

            Iterator<Student> iterator = currentVal.iterator();
            while (iterator.hasNext()) {
                Student student = iterator.next();
                if (student == null || !accessStudents.contains(student)) {
                    iterator.remove();
                }
            }

        }

    }

    private Student filterStudentResult(Student currentVal, Person currentPerson) {

        if (currentPerson instanceof Student) {

            Student student = (Student) currentPerson;

            if (currentVal == null || !currentVal.equals(student)) {
                return null;
            }

        } else if (currentPerson instanceof Teacher) {

            Teacher teacher = (Teacher) currentPerson;

            if (currentVal == null || !teacher.getClazzes().contains(currentVal.getClazz())) {
                return null;
            }

        } else if (currentPerson instanceof Parent) {

            Parent parent = (Parent) currentPerson;
            Set<Student> accessStudents = parent.getStudents();
            if (accessStudents == null) {
                return null;
            }

            if (currentVal == null || !accessStudents.contains(currentVal)) {
                return null;
            }
        }

        return currentVal;
    }

    private void filterUsersListResult(List<User> currentVal, Person currentPerson) {

        if (currentPerson instanceof Student) {

            Student student = (Student) currentPerson;

            Iterator<User> iterator = currentVal.iterator();
            while (iterator.hasNext()) {
                User userData = iterator.next();
                if (userData == null || !userData.equals(student.getUser())) {
                    iterator.remove();
                }
            }

        } else if (currentPerson instanceof Teacher) {

            Teacher teacher = (Teacher) currentPerson;

            Iterator<User> iterator = currentVal.iterator();
            while (iterator.hasNext()) {
                User userData = iterator.next();
                if (userData == null || !userData.equals(teacher.getUser())) {
                    iterator.remove();
                }
            }

        } else if (currentPerson instanceof Parent) {

            Parent parent = (Parent) currentPerson;
            Set<User> accessUsers = new HashSet<>();
            if (parent.getUser() != null) accessUsers.add(parent.getUser());
            if (parent.getStudents() != null){
                for (Student student : parent.getStudents()) {
                    if (student.getUser() != null) accessUsers.add(student.getUser());
                }
            }

            Iterator<User> iterator = currentVal.iterator();
            while (iterator.hasNext()) {
                User user = iterator.next();
                if (user == null || !accessUsers.contains(user)) {
                    iterator.remove();
                }
            }

        }

    }

    private User filterUserResult(User currentVal, Person currentPerson) {

        if (currentPerson instanceof Student) {

            Student student = (Student) currentPerson;

            if (currentVal == null || !currentVal.equals(student.getUser())) {
                return null;
            }

        } else if (currentPerson instanceof Teacher) {

            Teacher teacher = (Teacher) currentPerson;

            if (currentVal == null || !currentVal.equals(teacher.getUser())) {
                return null;
            }

        } else if (currentPerson instanceof Parent) {

            Parent parent = (Parent) currentPerson;
            Set<User> accessUsers = new HashSet<>();
            if (parent.getUser() != null) accessUsers.add(parent.getUser());
            if (parent.getStudents() != null){
                for (Student student : parent.getStudents()) {
                    if (student.getUser() != null) accessUsers.add(student.getUser());
                }
            }

            if (currentVal == null || !accessUsers.contains(currentVal)) {
                return null;
            }
        }

        return currentVal;
    }

    private boolean haveFullAccess(Authentication authentication) {
        try {
            accessDecisionManager.decide(authentication, null, SecurityConfig.createList("ROLE_ADMIN"));
            return true;
        } catch (AccessDeniedException ex) {
            //NOP
        }
        return false;
    }

    private boolean correctAuthentication(Authentication authentication) {
        if (authentication == null || !(authentication.getPrincipal() instanceof User)) {
            return false;
        }
        return true;
    }

    private void filterJournalResult(List<JournalView> currentVal, Person currentPerson) {

        if (currentPerson instanceof Student) {

            Student student = (Student) currentPerson;

            Iterator<JournalView> iterator = currentVal.iterator();
            while (iterator.hasNext()) {
                JournalView journalView = iterator.next();
                if (journalView.getStudent() == null || !journalView.getStudent().equals(student)) {
                    iterator.remove();
                }
            }

        } else if (currentPerson instanceof Teacher) {

            Teacher teacher = (Teacher) currentPerson;

            Iterator<JournalView> iterator = currentVal.iterator();
            while (iterator.hasNext()) {
                JournalView journalView = iterator.next();
                if (journalView.getTeacher() == null || !journalView.getTeacher().equals(teacher)) {
                    iterator.remove();
                }
            }

        } else if (currentPerson instanceof Parent) {

            Parent parent = (Parent) currentPerson;
            Set<Student> accessStudents = parent.getStudents();
            if (accessStudents == null) {
                currentVal = null;
            }

            Iterator<JournalView> iterator = currentVal.iterator();
            while (iterator.hasNext()) {
                JournalView journalView = iterator.next();
                if (journalView.getStudent() == null || !accessStudents.contains(journalView.getStudent())) {
                    iterator.remove();
                }
            }

        }

    }

}
