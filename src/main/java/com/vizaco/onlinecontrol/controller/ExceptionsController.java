package com.vizaco.onlinecontrol.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

@ControllerAdvice
public class ExceptionsController {

    @ExceptionHandler({NoHandlerFoundException.class, NoSuchRequestHandlingMethodException.class, RuntimeException.class, Exception.class})
    public ModelAndView handlePageNotFoundException(Throwable ex) {

        ModelAndView model = new ModelAndView("errors/404");
        model.addObject("exception", ex);
        return model;

    }

}
