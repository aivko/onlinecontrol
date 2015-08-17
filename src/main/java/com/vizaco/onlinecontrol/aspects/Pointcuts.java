package com.vizaco.onlinecontrol.aspects;

import com.vizaco.onlinecontrol.model.Student;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by super on 7/24/15.
 */
@Aspect
public class Pointcuts {

    @Pointcut("execution(* com.vizaco.onlinecontrol.service.SheduleService.saveShedule(..)) || execution(* com.vizaco.onlinecontrol.service.SheduleService.deleteShedule(..))")
    public void checkPermissionsSaveDeleteShedule(){};

    @Pointcut("execution(* com.vizaco.onlinecontrol.service.GradeService.saveGrade(..)) || execution(* com.vizaco.onlinecontrol.service.GradeService.deleteGrade(..))")
    public void checkPermissionsSaveDeleteGrade(){};

    @Pointcut("execution(* com.vizaco.onlinecontrol.service.*.*ByUser(..))")
    public void checkPermissionsFindByUser(){};

}
