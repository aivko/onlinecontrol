package com.vizaco.onlinecontrol.controller;

import com.vizaco.onlinecontrol.model.Role;
import com.vizaco.onlinecontrol.model.Student;
import com.vizaco.onlinecontrol.model.User;
import com.vizaco.onlinecontrol.model.ChangePassword;
import com.vizaco.onlinecontrol.security.PasswordHandler;
import com.vizaco.onlinecontrol.service.RoleService;
import com.vizaco.onlinecontrol.service.StudentService;
import com.vizaco.onlinecontrol.service.UserService;
import com.vizaco.onlinecontrol.utils.JsonUtil;
import com.vizaco.onlinecontrol.utils.Utils;
import com.vizaco.onlinecontrol.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.IOException;
import java.util.*;

@Controller
public class UserController {

    @Autowired
    ConversionService conversionService;

    @Autowired
    PasswordHandler passwordHandler;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private Utils utils = new Utils();

    private final UserService userService;

    private final StudentService studentService;

    private final RoleService roleService;

    @Autowired
    @Qualifier("userValidator")
    private Validator userValidator;

    @Autowired
    public UserController(UserService userService, StudentService studentService, RoleService roleService) {
        this.userService = userService;
        this.studentService = studentService;
        this.roleService = roleService;
    }

    @InitBinder("user")
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(userValidator);
    }

    @RequestMapping(value = "/users/{userId}/account")
    public ModelAndView initAccountForm(@PathVariable("userId") String userIdStr) {

        ModelAndView mav = new ModelAndView("account/account");

        User user = utils.getUser(userIdStr, userService);

        mav.addObject("user", user);

        return mav;
    }

    @RequestMapping(value = "/users")
    public ModelAndView initUsersForm() {

        ModelAndView mav = new ModelAndView("users/users");

        List<User> allUsers = userService.getAllUsers();

        mav.addObject("users", allUsers);

        return mav;
    }

    //<editor-fold desc="CRUD USER">
    //CREATE USER

    @RequestMapping(value = "/users/new", method = RequestMethod.GET)
    public String createUser(Model model) {
        User attributeValue = new User();
//        attributeValue.setRoles(new ArrayList(roleService.getAllRoles()));
//        attributeValue.setStudents(new ArrayList(studentService.getAllStudents()));
        model.addAttribute("user", attributeValue);
        return "/users/createOrUpdateUserForm";
    }

    @RequestMapping(value = "/users/new", method = RequestMethod.POST)
    public String createUser(@ModelAttribute("user") @Valid @Validated User user, BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "/users/createOrUpdateUserForm";
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);

        userService.saveUser(user);
        return "redirect:/users/" + user.getUserId();
    }

    //READ USER

    @RequestMapping(value = "/users/{userId}")
    public ModelAndView readUser(@PathVariable("userId") String userIdStr) {

        User user = utils.getUser(userIdStr, userService);

        ModelAndView mav = new ModelAndView("/users/userDetails");

        mav.addObject("user", user);

        return mav;
    }

    //UPDATE USER

    @RequestMapping(value = "/users/{userId}/edit", method = RequestMethod.GET)
    public ModelAndView editUser(@PathVariable("userId") String userIdStr) {

        User user = utils.getUser(userIdStr, userService);
        ModelAndView mav = new ModelAndView("/users/createOrUpdateUserForm");

        mav.addObject("user", user);

        return mav;
    }

    @RequestMapping(value = "/users/{userId}/edit", method = RequestMethod.PUT)
    public String editUser(@PathVariable("userId") String userIdStr, @ModelAttribute("user") User user, BindingResult result, Model model) {

        User userEdit = utils.getUser(userIdStr, userService);

        new UserValidator(userService).validateEdit(user, result);

        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "/users/createOrUpdateUserForm";
        }

        user.setPassword(userEdit.getPassword());
        user.setRoles(userEdit.getRoles());
        user.setStudents(userEdit.getStudents());
        userService.saveUser(user);
        return "redirect:/users/" + user.getUserId();
    }

    //DELETE USER

    @RequestMapping(value = "/users/{userId}/delete", method = RequestMethod.DELETE)
    public ModelAndView deleteUser(@PathVariable("userId") String userIdStr) {

        User user = utils.getUser(userIdStr, userService);
        userService.deleteUser(user.getUserId());

        return new ModelAndView("redirect:/users");

    }
    //</editor-fold>


    @RequestMapping(value = "/users/{userId}/students/add", method = RequestMethod.GET)
    public String addStudent(@PathVariable("userId") String userIdStr, Model model) {
        User user = utils.getUser(userIdStr, userService);
        model.addAttribute("user", user);
        model.addAttribute("students", studentService.getAllStudents());
        return "/students/selectStudent";
    }

    @RequestMapping(value = "/users/{userId}/students/{studentId}/add", method = RequestMethod.POST)
    public String addStudent(@PathVariable("userId") String userIdStr, @PathVariable("studentId") String studentIdStr) {

        User user = utils.getUser(userIdStr, userService);
        Student student = utils.getStudent(studentIdStr, studentService);
        List<Student> students = user.getStudents();
        students.add(student);

        userService.saveUser(user);
        return "redirect:/users/" + user.getUserId();
    }

    @RequestMapping(value = "/users/{userId}/students/{studentId}/delete", method = RequestMethod.DELETE)
    public String deleteStudent(@PathVariable("userId") String userIdStr, @PathVariable("studentId") String studentIdStr) {

        User user = utils.getUser(userIdStr, userService);
        Student student = utils.getStudent(studentIdStr, studentService);
        List<Student> students = user.getStudents();
        students.remove(student);

        userService.saveUser(user);
        return "redirect:/users/" + user.getUserId();
    }

    @RequestMapping(value = "/users/{userId}/roles/add", method = RequestMethod.GET)
    public String addRole(@PathVariable("userId") String userIdStr, Model model) {
        User user = utils.getUser(userIdStr, userService);
        model.addAttribute("user", user);
        model.addAttribute("roles", roleService.getAllRoles());
        return "/roles/selectRole";
    }

    @RequestMapping(value = "/users/{userId}/roles/{roleId}/add", method = RequestMethod.POST)
    public String addRole(@PathVariable("userId") String userIdStr, @PathVariable("roleId") String roleIdStr) {

        User user = utils.getUser(userIdStr, userService);
        Role role = utils.getRole(roleIdStr, roleService);
        List<Role> roles = user.getRoles();
        roles.add(role);

        userService.saveUser(user);
        return "redirect:/users/" + user.getUserId();
    }

    @RequestMapping(value = "/users/{userId}/roles/{roleId}/delete", method = RequestMethod.DELETE)
    public String deleteRole(@PathVariable("userId") String userIdStr, @PathVariable("roleId") String roleIdStr) {

        User user = utils.getUser(userIdStr, userService);
        Role role = utils.getRole(roleIdStr, roleService);
        List<Role> roles = user.getRoles();
        roles.remove(role);

        userService.saveUser(user);
        return "redirect:/users/" + user.getUserId();
    }

    @RequestMapping(value = "/users/{userId}/changePassword", method = RequestMethod.GET)
    public ModelAndView showChangePasswordPage(@PathVariable("userId") String userIdStr) {
        User user = utils.getUser(userIdStr, userService);

        ModelAndView mav = new ModelAndView("auth/changePassword");

        mav.addObject("user", user);
        mav.addObject("changePassword", new ChangePassword());

        return mav;
    }

    @RequestMapping(value = "/users/{userId}/changePassword", method = RequestMethod.POST)
    public String submitChangePasswordPage(@PathVariable("userId") String userIdStr,
                                           @ModelAttribute("changePassword") ChangePassword changePassword,
                                           BindingResult result, Model model) {

        UserValidator userValidator = new UserValidator(userService);
        User user = utils.getUser(userIdStr, userService);

        //TODO: Study encoding
//        if(!passwordEncoder.matches(user.getPassword(), passwordEncoder.encode(changePassword.getPasswordOld()))){
//            result.rejectValue("passwordOld", "user.passwordOld.passwordDiff");
//            model.addAttribute("user", user);
//            return "/auth/changePassword";
//        }

        ValidationUtils.rejectIfEmptyOrWhitespace(result, "password", "user.password.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(result, "passwordConfirm", "user.passwordConfirm.required");
        if ((changePassword.getPassword() != null && !changePassword.getPassword().equals(changePassword.getPasswordConfirm())
                || (changePassword.getPasswordConfirm() != null && !changePassword.getPasswordConfirm().equals(changePassword.getPassword())))) {
            result.rejectValue("password", "user.password.passwordDiff");
            result.rejectValue("passwordConfirm", "user.passwordConfirm.passwordDiff");
        }

        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "/auth/changePassword";
        }

        passwordHandler.changePassword(user.getUsername(), changePassword.getPassword());
        return "redirect:/users/" + userIdStr + "/account";
    }

    @RequestMapping(value = "/users/checkEmail", method = RequestMethod.POST)
    @ResponseBody
    public String checkEmail(@RequestBody String json) throws IOException {
        JsonUtil jsonUtil = new JsonUtil();
        String email = jsonUtil.getJsonElement(json, "email");
        User userByEmail = userService.findUserByEmail(email);
        if (userByEmail == null) {
            return "{\"result\":\"false\"}";
        }
        return "{\"result\":\"true\"}";
    }

}
