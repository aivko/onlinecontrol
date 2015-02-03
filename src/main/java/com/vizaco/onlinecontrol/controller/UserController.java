package com.vizaco.onlinecontrol.controller;

import com.vizaco.onlinecontrol.model.Role;
import com.vizaco.onlinecontrol.model.Student;
import com.vizaco.onlinecontrol.model.User;
import com.vizaco.onlinecontrol.service.StudentService;
import com.vizaco.onlinecontrol.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Set;

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

//    @InitBinder
//    protected void initBinder(WebDataBinder binder) throws Exception {
    @InitBinder("user")
    protected void initBinder(WebDataBinder binder) throws Exception {
        binder.setValidator(userValidator);
//        binder.registerCustomEditor(List.class, "roles", new CustomCollectionEditor(List.class){
//
//            @Override
//            protected Role convertElement(Object element) {
//                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1 convert element" + element);
//                String roleId = (String) element;
//                Role role = userService.getRoleById(roleId);
//                return role;
//            }
//        });
//
//        binder.registerCustomEditor(List.class, "students", new CustomCollectionEditor(List.class){
//
//            @Override
//            protected Student convertElement(Object element) {
//                String studentId = (String) element;
//                Student student = studentService.getStudentById(studentId);
//                return student;
//            }
//        });
//
//
//    }
//
//            @Override
//            public void setAsText(String text) {
//                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1 set method" + text);
//                Role role = userService.getRoleById(text);
//                setValue(role);
//            }
//
//            @Override
//            public String getAsText() {
//                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! get metod");
//                return super.getAsText();
//            }
//        });
    }

    
    @RequestMapping(value = "/users/account/{login}")
    public ModelAndView initAccountForm(@PathVariable("login") String login) {

        ModelAndView mav = new ModelAndView("/users/account");
        
        //TODO: May be using principal (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal()
        User user = userService.findUserByLogin(login);

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
    public String saveUser(@ModelAttribute("user") @Valid @Validated User user, BindingResult result, HttpServletRequest request) throws IOException {

        if(result.hasErrors()){
            return "/auth/registration";
        }

        userService.saveUser(user);
        User principal = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return "redirect:/users/account/" + principal.getLogin();
    }
}
