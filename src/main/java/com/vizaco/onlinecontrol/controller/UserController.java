package com.vizaco.onlinecontrol.controller;

import com.vizaco.onlinecontrol.exceptions.CustomGenericException;
import com.vizaco.onlinecontrol.model.User;
import com.vizaco.onlinecontrol.service.StudentService;
import com.vizaco.onlinecontrol.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    ConversionService conversionService;

    private final UserService userService;

    private final StudentService studentService;

    @Autowired
    @Qualifier("userValidator")
    private Validator userValidator;
    
    @Autowired
    public UserController(UserService userService, StudentService studentService) {
        this.userService = userService;
        this.studentService = studentService;
    }

    @InitBinder("user")
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(userValidator);
    }

    
    @RequestMapping(value = "/users/{userId}/account")
    public ModelAndView initAccountForm(@PathVariable("userId") String userIdStr) {

        ModelAndView mav = new ModelAndView("/users/account");

        Long userId = null;

        try {
            userId = Long.valueOf(userIdStr);
        }catch (NumberFormatException ex){
            throw new CustomGenericException("404", "Page not found for the user with the ID " + userIdStr);
        }

        User user = userService.findUserById(userId);

        if (user == null){
            throw new CustomGenericException("404", "Page not found for the user with the ID " + userId);
        }

        mav.addObject("user", user);

        return mav;
    }
    
    @RequestMapping(value = "/users")
    public ModelAndView initAccountForm() {

        ModelAndView mav = new ModelAndView("users/users");

        List<User> allUsers = userService.getAllUsers();

        mav.addObject("users", allUsers);

        return mav;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String register(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", userService.getAllRoles());
        model.addAttribute("students", studentService.getAllStudents());
        return "/auth/registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute("user") @Valid @Validated User user, BindingResult result, Model model) {

        if(result.hasErrors()){
            model.addAttribute("roles", userService.getAllRoles());
            model.addAttribute("students", studentService.getAllStudents());
            return "/auth/registration";
        }

        userService.saveUser(user);
        User principal = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return "redirect:/users/" + principal.getUserId() + "/account/";
    }

}
