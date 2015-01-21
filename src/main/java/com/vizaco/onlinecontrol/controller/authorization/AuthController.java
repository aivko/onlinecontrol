package com.vizaco.onlinecontrol.controller.authorization;

import com.vizaco.onlinecontrol.service.OnlineControlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * @author Juergen Hoeller
 * @author Ken Krebs
 * @author Arjen Poutsma
 * @author Michael Isvy
 */
@Controller
public class AuthController {

    @RequestMapping(value = "/auth", method = RequestMethod.GET)
    public String initAuthForm() {
        return "auth/auth";
    }

}
