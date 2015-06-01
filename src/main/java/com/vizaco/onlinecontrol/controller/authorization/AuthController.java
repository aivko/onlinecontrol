package com.vizaco.onlinecontrol.controller.authorization;

import com.vizaco.onlinecontrol.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AuthController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String initAuthForm() {
        return "auth/login";
    }

}
