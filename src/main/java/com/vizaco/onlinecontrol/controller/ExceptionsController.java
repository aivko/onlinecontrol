package com.vizaco.onlinecontrol.controller;

import com.vizaco.onlinecontrol.exceptions.CustomGenericException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

@ControllerAdvice
public class ExceptionsController {

    @ExceptionHandler(CustomGenericException.class)
    public ModelAndView handlePageNotFoundException(CustomGenericException ex) {

        ModelAndView model = new ModelAndView("errors/exception");
        model.addObject("exception", ex);
        return model;

    }

}
