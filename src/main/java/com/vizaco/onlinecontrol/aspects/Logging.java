package com.vizaco.onlinecontrol.aspects;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class Logging {

    private final Log log = LogFactory.getLog(this.getClass());

    @Before("execution(* com.vizaco.onlinecontrol.controller..*(..))")
    public void logControllerBefore(JoinPoint joinPoint) throws Throwable {

        String logMessage = getLogMessage(joinPoint);
        log.info(logMessage);
    }

    private String getLogMessage(JoinPoint joinPoint) {

        StringBuffer logMessage = new StringBuffer("Before execution: ");
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

    @After("execution(* com.vizaco.onlinecontrol.controller..*(..))")
    public void logControllerAfter(JoinPoint joinPoint) throws Throwable {

        StringBuffer logMessage = new StringBuffer("After execution: ");
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
        log.info(logMessage.toString());
    }

    @Before("execution(* com.vizaco.onlinecontrol.service..*(..))")
    public void logServiceBefore(JoinPoint joinPoint) throws Throwable {

        String logMessage = getLogMessage(joinPoint);
        log.info(logMessage);
    }

    @After("execution(* com.vizaco.onlinecontrol.service..*(..))")
    public void logServiceAfter(JoinPoint joinPoint) throws Throwable {

        StringBuffer logMessage = new StringBuffer("After execution: ");
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
        log.info(logMessage.toString());
    }

    @Before("execution(* com.vizaco.onlinecontrol.dao..*(..))")
    public void logDaoBefore(JoinPoint joinPoint) throws Throwable {

        String logMessage = getLogMessage(joinPoint);
        log.info(logMessage);
    }

    @After("execution(* com.vizaco.onlinecontrol.dao..*(..))")
    public void logDaoAfter(JoinPoint joinPoint) throws Throwable {

        StringBuffer logMessage = new StringBuffer("After execution: ");
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
        log.info(logMessage.toString());
    }

}
