package com.vizaco.onlinecontrol.controller.authorization;

import com.vizaco.onlinecontrol.constants.Authorization;
import com.vizaco.onlinecontrol.utils.JsonUtil;
import com.vizaco.onlinecontrol.utils.OAuthUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.util.Map;

@Controller
public class GoogleController implements Authorization {

    @RequestMapping(value = "/registrate/google", method = RequestMethod.GET)
    public ModelAndView googleRegistration() {

        String url = GOOGLE_URL + "?client_id=" + GOOGLE_API_KEY + "&redirect_uri=" + GOOGLE_URL_CALLBACK_REGISTRATION + "&response_type=code" +
                "&scope=https://www.googleapis.com/auth/userinfo.email+https://www.googleapis.com/auth/userinfo.profile";
        return new ModelAndView(new RedirectView(url, true, true, true));

    }

    @RequestMapping(value = "/callback/google/registration", params = "code")
    public String registrationAccessCode(@RequestParam("code") String code) {

        String authRequest = null;
        Map<String, Object> userData = null;
        try {
            authRequest = OAuthUtil.sendHttpRequest("POST", GOOGLE_URL_ACCESS_TOKEN,
                    new String[]{"client_id", "redirect_uri", "client_secret", "code", "grant_type"},
                    new String[]{GOOGLE_API_KEY, GOOGLE_URL_CALLBACK_REGISTRATION, GOOGLE_API_SECRET, code, "authorization_code"});

            String token = JsonUtil.getJsonElement(authRequest, "access_token");
            String tokenRequest = OAuthUtil.sendHttpRequest("GET", GOOGLE_URL_ME, new String[]{"access_token"}, new String[]{token});

            userData = JsonUtil.getMapFromJsonElement(tokenRequest);

        } catch (Exception e) {
            //TODO: return client to registration page with errorMessage
            return "redirect:/auth";
        }

        //TODO: verifying ... is new? is email in DB?
        return "redirect:/users/account";
    }

    @RequestMapping(value = "/callback/google/registration", params = "error")
    public String registrationError(@RequestParam("error_message") String errorMessage) {

        //TODO: return client to registration page with errorMessage
        return "redirect:/auth";

    }

}
