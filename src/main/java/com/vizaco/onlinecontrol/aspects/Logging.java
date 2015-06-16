package com.vizaco.onlinecontrol.aspects;

import com.vizaco.onlinecontrol.model.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class Logging {

    private final Log log = LogFactory.getLog(this.getClass());

    @Before("execution(* com.vizaco.onlinecontrol.controller..*(..))")
    public void logControllerBefore(JoinPoint joinPoint) throws Throwable {
        log.info(getLogMessage(joinPoint, "Before execution: "));
    }

    @After("execution(* com.vizaco.onlinecontrol.controller..*(..))")
    public void logControllerAfter(JoinPoint joinPoint) throws Throwable {
        log.info(getLogMessage(joinPoint, "After execution: "));
    }

    @Before("execution(* com.vizaco.onlinecontrol.service..*(..))")
    public void logServiceBefore(JoinPoint joinPoint) throws Throwable {
        log.info(getLogMessage(joinPoint, "Before execution: "));
    }

    @After("execution(* com.vizaco.onlinecontrol.service..*(..))")
    public void logServiceAfter(JoinPoint joinPoint) throws Throwable {
        log.info(getLogMessage(joinPoint, "After execution: "));
    }

    @Before("execution(* com.vizaco.onlinecontrol.dao..*(..))")
    public void logDaoBefore(JoinPoint joinPoint) throws Throwable {
        log.info(getLogMessage(joinPoint, "Before execution: "));
    }

    @After("execution(* com.vizaco.onlinecontrol.dao..*(..))")
    public void logDaoAfter(JoinPoint joinPoint) throws Throwable {
        log.info(getLogMessage(joinPoint, "After execution: "));
    }

    @Before("execution(* com.vizaco.onlinecontrol.converters..*(..))")
    public void logConvertersBefore(JoinPoint joinPoint) throws Throwable {
        log.info(getLogMessage(joinPoint, "Before execution: "));
    }

    @After("execution(* com.vizaco.onlinecontrol.converters..*(..))")
    public void logConvertersAfter(JoinPoint joinPoint) throws Throwable {
        log.info(getLogMessage(joinPoint, "After execution: "));
    }

    @Before("execution(* com.vizaco.onlinecontrol.utils..*(..))")
    public void logUtilsBefore(JoinPoint joinPoint) throws Throwable {
        log.info(getLogMessage(joinPoint, "Before execution: "));
    }

    @After("execution(* com.vizaco.onlinecontrol.utils..*(..))")
    public void logUtilsAfter(JoinPoint joinPoint) throws Throwable {
        log.info(getLogMessage(joinPoint, "After execution: "));
    }

    @Before("execution(* com.vizaco.onlinecontrol.validators..*(..))")
    public void logValidatorsBefore(JoinPoint joinPoint) throws Throwable {
        log.info(getLogMessage(joinPoint, "Before execution: "));
    }

    @After("execution(* com.vizaco.onlinecontrol.validators..*(..))")
    public void logValidatorsAfter(JoinPoint joinPoint) throws Throwable {
        log.info(getLogMessage(joinPoint, "After execution: "));
    }

    private String getLogMessage(JoinPoint joinPoint, String start) {
        StringBuffer logMessage = new StringBuffer(start);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication == null ? "anonymous":authentication.getPrincipal();
        logMessage.append("#auth ").append(principal).append(" # ");
        logMessage.append(joinPoint.getTarget().getClass().getName());
        logMessage.append(".");
        logMessage.append(joinPoint.getSignature().getName());
        logMessage.append("(");
        // append args
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            logMessage.append(args[i]).append(",");
        }
        if (args.length > 0) {
            logMessage.deleteCharAt(logMessage.length() - 1);
        }

        logMessage.append(")");
        return logMessage.toString();
    }

}
