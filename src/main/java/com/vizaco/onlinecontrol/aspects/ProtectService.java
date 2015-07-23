package com.vizaco.onlinecontrol.aspects;

import com.vizaco.onlinecontrol.model.*;
import com.vizaco.onlinecontrol.representation.JournalView;
import com.vizaco.onlinecontrol.service.BusinessService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@Aspect
public class ProtectService {

//    @Autowired
//    AccessDecisionManager accessDecisionManager;

    @Autowired
    BusinessService businessService;

//    @Before("execution(* com.vizaco.onlinecontrol.service.ClazzService.saveClazz(..))")
//    public void logServiceBefore(JoinPoint joinPoint) throws Throwable {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        accessDecisionManager.decide(authentication, null, SecurityConfig.createList("ROLE_ADMIN"));
//    }

    @AfterReturning(pointcut = "execution(* com.vizaco.onlinecontrol.service.SheduleService.getSheduleByCriteria(..))", returning = "retVal")
    public void logServiceBefore(Object retVal) throws Throwable {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null | !(authentication.getPrincipal() instanceof User)){
            retVal = null;
            return;
        }
        List<JournalView> currentVal = (List<JournalView>) retVal;
        User user = (User) authentication.getPrincipal();

        Person currentPerson = businessService.getCurrentPerson(user);

        if (currentPerson instanceof Student){

        }else if(currentPerson instanceof Teacher){

        }else if(currentPerson instanceof Parent){

        }



    }

}
