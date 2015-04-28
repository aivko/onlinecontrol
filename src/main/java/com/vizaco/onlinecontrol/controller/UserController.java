package com.vizaco.onlinecontrol.controller;

import com.vizaco.onlinecontrol.model.Role;
import com.vizaco.onlinecontrol.model.Student;
import com.vizaco.onlinecontrol.model.User;
import com.vizaco.onlinecontrol.service.StudentService;
import com.vizaco.onlinecontrol.service.UserService;
import com.vizaco.onlinecontrol.utils.UsersUtils;
import com.vizaco.onlinecontrol.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.*;

@Controller
public class UserController {

    @Autowired
    ConversionService conversionService;

    private UsersUtils usersUtils = new UsersUtils();

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
        User attributeValue = new User();
        attributeValue.setRoles(new ArrayList(userService.getAllRoles()));
        attributeValue.setStudents(new ArrayList(studentService.getAllStudents()));
        model.addAttribute("user", attributeValue);
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

    //READ USER

    @RequestMapping(value = "/users/{userId}")
    public ModelAndView initAccountForm(@PathVariable("userId") String userIdStr) {

        User user = usersUtils.getUser(userIdStr, userService);

        User principal = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user.getUserId() != principal.getUserId()){
            return new ModelAndView("redirect:/exception/403");
        }

        ModelAndView mav = new ModelAndView("/users/account");

        mav.addObject("user", user);

        return mav;
    }

    //UPDATE USER

    @RequestMapping(value = "/users/{userId}/edit", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable("userId") String userIdStr) {

        User user = usersUtils.getUser(userIdStr, userService);
        ModelAndView mav = new ModelAndView("/users/createOrUpdateUserForm");

        mav.addObject("user", user);
        mav.addObject("roles", user.getRoles());
        mav.addObject("students", user.getStudents());

        return mav;
    }

    @RequestMapping(value = "/users/{userId}/edit", method = RequestMethod.PUT)
    public String edit(@PathVariable("userId") String userIdStr, @ModelAttribute("user") User user, BindingResult result, Model model) {

        User userEdit = usersUtils.getUser(userIdStr, userService);

        new UserValidator(userService).validateEdit(user, result);

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

        User user = usersUtils.getUser(userIdStr, userService);
        userService.deleteUser(user.getUserId());

        return new ModelAndView("redirect:/users");

    }

}
