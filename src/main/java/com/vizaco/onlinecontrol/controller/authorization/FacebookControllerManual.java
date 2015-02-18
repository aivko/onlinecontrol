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
public class FacebookControllerManual implements Authorization {

//    @RequestMapping(value = "/registration/facebook", method = RequestMethod.GET)
//    public ModelAndView facebookRegistration() {
//        return new ModelAndView(new RedirectView(FACEBOOK_URL + "?client_id=" + FACEBOOK_API_KEY +
//                "&redirect_uri=" + FACEBOOK_URL_CALLBACK_REGISTRATION +
//                "&scope=email,user_location&state=registration", true, true, true));
//    }
//
//    @RequestMapping(value = "/callback/facebook/registration", params = "code")
//    public String registrationAccessCode(@RequestParam("code") String code) {
//
//        String authRequest = null;
//        Map<String, Object> userData = null;
//        try {
//            authRequest = OAuthUtil.sendHttpRequest("GET", FACEBOOK_URL_ACCESS_TOKEN,
//                    new String[]{"client_id", "redirect_uri", "client_secret", "code"},
//                    new String[]{FACEBOOK_API_KEY, FACEBOOK_URL_CALLBACK_REGISTRATION, FACEBOOK_API_SECRET, code});
//
//            String token = OAuthUtil.parseURLQuery(authRequest).get("access_token");
//            String tokenRequest = OAuthUtil.sendHttpRequest("GET", FACEBOOK_URL_ME, new String[]{"access_token"}, new String[]{token});
//
//            userData = JsonUtil.getMapFromJsonElement(tokenRequest);
//
//        } catch (Exception e) {
//            //TODO: return client to registration page with errorMessage
//            return "redirect:/auth";
//        }
//
//        //TODO: verifying ... is new? is email in DB?
//        return "redirect:/users/account";
//    }
//
//    @RequestMapping(value = "/callback/facebook/registration", params = "error_code")
//    public String registrationError(@RequestParam("error_message") String errorMessage) {
//
//        //TODO: return client to registration page with errorMessage
//        return "redirect:/auth";
//
//    }

}
