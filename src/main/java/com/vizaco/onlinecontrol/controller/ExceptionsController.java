package com.vizaco.onlinecontrol.controller;

import com.vizaco.onlinecontrol.exceptions.CustomGenericException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

import java.security.Principal;

@ControllerAdvice
public class ExceptionsController {

    @ExceptionHandler(CustomGenericException.class)
    public ModelAndView handleCustomGenericException(CustomGenericException ex) {

        ModelAndView model = new ModelAndView("errors/exception");
        model.addObject("exception", ex);
        return model;

    }

    @ExceptionHandler(AccessDeniedException.class)
    public ModelAndView handleAccessDeniedException(Principal user, AccessDeniedException ex) {

        String errorMsg;
        if (user != null) {
            errorMsg = "Hi " + user.getName() + ", this page not access!";
        } else {
            errorMsg = "Page not access!";
        }
        throw new CustomGenericException("403", errorMsg);

    }

}
