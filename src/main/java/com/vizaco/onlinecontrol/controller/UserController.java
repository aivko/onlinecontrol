package com.vizaco.onlinecontrol.controller;

import com.vizaco.onlinecontrol.exceptions.CustomGenericException;
import com.vizaco.onlinecontrol.model.User;
import com.vizaco.onlinecontrol.service.StudentService;
import com.vizaco.onlinecontrol.service.UserService;
import com.vizaco.onlinecontrol.utils.UsersUtils;
import com.vizaco.onlinecontrol.validators.UserValidatorEdit;
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

    @RequestMapping(value = "/users/{userId}")
    public ModelAndView initAccountForm(@PathVariable("userId") String userIdStr) {

        User user = UsersUtils.getUser(userIdStr, userService);

        User principal = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user.getUserId() != principal.getUserId()){
            return new ModelAndView("redirect:/exception/403");
        }

        ModelAndView mav = new ModelAndView("/users/account");

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

    //CREATE USER

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String register(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", userService.getAllRoles());
        model.addAttribute("students", studentService.getAllStudents());
        return "/users/createOrUpdateUserForm";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute("user") @Valid @Validated User user, BindingResult result, Model model) {

        if(result.hasErrors()){
            model.addAttribute("roles", userService.getAllRoles());
            model.addAttribute("students", studentService.getAllStudents());
            return "/users/createOrUpdateUserForm";
        }

        userService.saveUser(user);
        User principal = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return "redirect:/users/" + principal.getUserId();
    }

    //EDIT USER

    @RequestMapping(value = "/users/{userId}/edit", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable("userId") String userIdStr) {

        User user = UsersUtils.getUser(userIdStr, userService);
        ModelAndView mav = new ModelAndView("/users/createOrUpdateUserForm");

        mav.addObject("user", user);
        mav.addObject("roles", user.getRoles());
        mav.addObject("students", user.getStudents());

        return mav;
    }

    @RequestMapping(value = "/users/{userId}/edit", method = RequestMethod.PUT)
    public String edit(@PathVariable("userId") String userIdStr, @ModelAttribute("user") User user, BindingResult result, Model model) {

        User userEdit = UsersUtils.getUser(userIdStr, userService);

        new UserValidatorEdit().validate(user, result);

        if(result.hasErrors()){
            model.addAttribute("roles", userEdit.getRoles());
            model.addAttribute("students", userEdit.getStudents());
            return "/users/createOrUpdateUserForm";
        }

        user.setUserId(userEdit.getUserId());
        userService.saveUser(user);
        User principal = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return "redirect:/users/" + principal.getUserId();
    }

    //DELETE USER

    @RequestMapping(value = "/users/{userId}/delete", method = RequestMethod.DELETE)
    public ModelAndView deleteUser(@PathVariable("userId") String userIdStr) {

        User user = UsersUtils.getUser(userIdStr, userService);
        userService.deleteUser(user.getUserId());

        User principal = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return new ModelAndView("redirect:/users");

    }


}
