/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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

/**
 * @author Juergen Hoeller
 * @author Ken Krebs
 * @author Arjen Poutsma
 * @author Michael Isvy
 */
@Controller
public class FacebookControllerManual implements Authorization {

//    @RequestMapping(value = "/registrate/facebook", method = RequestMethod.GET)
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
//        return "redirect:/users/userAccount";
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
