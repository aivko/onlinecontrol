package com.vizaco.onlinecontrol.aspects;

import com.vizaco.onlinecontrol.model.*;
import com.vizaco.onlinecontrol.representation.JournalView;
import com.vizaco.onlinecontrol.service.BusinessService;
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

import java.util.Iterator;
import java.util.List;
import java.util.Set;


@Component
@Aspect
public class ProtectService {

    @Autowired
    AccessDecisionManager accessDecisionManager;

    @Autowired
    BusinessService businessService;


    @Before("(execution(* com.vizaco.onlinecontrol.service.*.save*(..)) " +
            "|| execution(* com.vizaco.onlinecontrol.service.*.delete*(..))) " +
            "&& !com.vizaco.onlinecontrol.aspects.Pointcuts.checkPermissionsSaveDeleteShedule()")
    public void checkPermissionsSaveDelete() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        accessDecisionManager.decide(authentication, null, SecurityConfig.createList("ROLE_ADMIN"));

    }

    @AfterReturning(pointcut = "execution(java.util.List<com.vizaco.onlinecontrol.model.Student> com.vizaco.onlinecontrol.service..*(..))", returning = "retVal")
    public void filterStudentsList(List<Student> retVal) {

        if (retVal == null){
            return;
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!correctAuthentication(authentication)) {
            retVal = null;
            return;
        }

        if (haveFullAccess(authentication)) return;

        Person currentPerson = businessService.getCurrentPerson((User) authentication.getPrincipal());

        if (currentPerson == null) {
            retVal = null;
            return;
        }

        filterStudentsListResult(retVal, currentPerson);

    }

    private void filterStudentsListResult(List<Student> currentVal, Person currentPerson) {

//        if (currentPerson instanceof Student) {
//
//            Student student = (Student) currentPerson;
//
//            Iterator<Student> iterator = currentVal.iterator();
//            while (iterator.hasNext()) {
//                Student journalView = iterator.next();
//                if (journalView.getStudent() == null || !journalView.getStudent().equals(student)) {
//                    iterator.remove();
//                }
//            }
//
//        } else if (currentPerson instanceof Teacher) {
//
//            Teacher teacher = (Teacher) currentPerson;
//
//            Iterator<Student> iterator = currentVal.iterator();
//            while (iterator.hasNext()) {
//                JournalView journalView = iterator.next();
//                if (journalView.getTeacher() == null || !journalView.getTeacher().equals(teacher)) {
//                    iterator.remove();
//                }
//            }
//
//        } else if (currentPerson instanceof Parent) {
//
//            Parent parent = (Parent) currentPerson;
//            Set<Student> accessStudents = parent.getStudents();
//            if (accessStudents == null) {
//                currentVal = null;
//            }
//
//            Iterator<Student> iterator = currentVal.iterator();
//            while (iterator.hasNext()) {
//                JournalView journalView = iterator.next();
//                if (journalView.getStudent() == null || !accessStudents.contains(journalView.getStudent())) {
//                    iterator.remove();
//                }
//            }
//
//        }

    }

    @AfterReturning(pointcut = "execution(java.util.List<com.vizaco.onlinecontrol.representation.JournalView> com.vizaco.onlinecontrol.service..*(..))", returning = "retVal")
    public void filterJournalViewList(List<JournalView> retVal) {

        if (retVal == null){
            return;
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!correctAuthentication(authentication)) {
            retVal = null;
            return;
        }

        if (haveFullAccess(authentication)) return;

        Person currentPerson = businessService.getCurrentPerson((User) authentication.getPrincipal());

        if (currentPerson == null) {
            retVal = null;
            return;
        }

        filterJournalResult(retVal, currentPerson);

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
