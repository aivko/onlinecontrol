package com.vizaco.onlinecontrol.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class ProtectService {

    @Autowired
    AccessDecisionManager accessDecisionManager;

    @Before("execution(* com.vizaco.onlinecontrol.service.ClazzService.saveClazz(..))")
    public void logServiceBefore(JoinPoint joinPoint) throws Throwable {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        accessDecisionManager.decide(authentication, null, SecurityConfig.createList("ROLE_ADMIN"));
    }

}
