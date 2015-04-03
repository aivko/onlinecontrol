package com.vizaco.onlinecontrol.controller;

import com.vizaco.onlinecontrol.exceptions.CustomGenericException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
public class MainController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String start() {
        return "index";
    }

    // for 403 access denied page
    @RequestMapping(value = "/exception/403", method = RequestMethod.GET)
    public void accessDenied(Principal user) throws CustomGenericException{

        String errorMsg;
        if (user != null) {
            errorMsg = "Hi " + user.getName() + ", you do not have permission to access this page!";
        } else {
            errorMsg = "You do not have permission to access this page!";
        }
        throw new CustomGenericException("403", errorMsg);

    }

    @RequestMapping(value = "/**")
    public void crash(Principal user) {

        String errorMsg;
        if (user != null) {
            errorMsg = "Hi " + user.getName() + ", this page not found!";
        } else {
            errorMsg = "Page not found!";
        }
        throw new CustomGenericException("404", errorMsg);
    }

}
