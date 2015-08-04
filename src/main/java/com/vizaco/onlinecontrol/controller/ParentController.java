package com.vizaco.onlinecontrol.controller;

import com.vizaco.onlinecontrol.model.*;
import com.vizaco.onlinecontrol.security.PasswordHandler;
import com.vizaco.onlinecontrol.service.ParentService;
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
import java.util.List;
import java.util.Set;

@Controller
public class ParentController extends BaseController{

    @Autowired
    private Utils utils;

    private final ParentService parentService;

    private final StudentService studentService;

    private final UserService userService;

    @Autowired
    public ParentController(ParentService parentService, StudentService studentService, UserService userService) {
        this.parentService = parentService;
        this.studentService = studentService;
        this.userService = userService;
    }

    @RequestMapping(value = "/parents")
    public ModelAndView getAllParents() {

        ModelAndView mav = new ModelAndView("parents/parents");

        List<Parent> allParents = parentService.getAllParents();

        mav.addObject("parents", allParents);

        return mav;
    }

    //<editor-fold desc="CRUD PARENTS">
    //CREATE PARENT

    @RequestMapping(value = "/parents/new", method = RequestMethod.GET)
    public String createParent(Model model) {
        Parent attributeValue = new Parent();
        model.addAttribute("parent", attributeValue);
        model.addAttribute("users", userService.getAllUsers());
        return "/parents/createOrUpdateParentForm";
    }

    @RequestMapping(value = "/parents/new", method = RequestMethod.POST)
    public String createParent(@ModelAttribute("parent") @Valid Parent parent, BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("parent", parent);
            model.addAttribute("users", userService.getAllUsers());
            return "/parents/createOrUpdateParentForm";
        }

        parentService.saveParent(parent);
        return "redirect:/parents/" + parent.getId();
    }

    //READ PARENT

    @RequestMapping(value = "/parents/{parentId}")
    public ModelAndView readUser(@PathVariable("parentId") String parentIdStr) {

        Parent parent = utils.getParent(parentIdStr, null);

        ModelAndView mav = new ModelAndView("/parents/parentDetails");

        mav.addObject("parent", parent);

        return mav;
    }

    //UPDATE PARENT

    @RequestMapping(value = "/parents/{parentId}/edit", method = RequestMethod.GET)
    public ModelAndView editParent(@PathVariable("parentId") String parentIdStr) {

        Parent parent = utils.getParent(parentIdStr, null);
        ModelAndView mav = new ModelAndView("/parents/createOrUpdateParentForm");

        mav.addObject("parent", parent);
        mav.addObject("users", userService.getAllUsers());

        return mav;
    }

    @RequestMapping(value = "/parents/{parentId}/edit", method = RequestMethod.PUT)
    public String editParent(@PathVariable("parentId") String parentIdStr, @ModelAttribute("parent") @Valid Parent parent, BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("parent", parent);
            model.addAttribute("users", userService.getAllUsers());
            return "/parents/createOrUpdateParentForm";
        }

        Parent parentEdit = utils.getParent(parentIdStr, null);

        parent.setStudents(parentEdit.getStudents());
        parentService.saveParent(parent);
        return "redirect:/parents/" + parent.getId();
    }

    //DELETE PARENT

    @RequestMapping(value = "/parents/{parentId}/delete", method = RequestMethod.DELETE)
    public ModelAndView deleteParent(@PathVariable("parentId") String parentIdStr) {

        Parent parent = utils.getParent(parentIdStr, null);
        parentService.deleteParent(parent.getId());

        return new ModelAndView("redirect:/parents");

    }
    //</editor-fold>

    @RequestMapping(value = "/parents/{parentId}/students/add", method = RequestMethod.GET)
    public String addStudent(@PathVariable("parentId") String parentIdStr, Model model) {
        Parent parent = utils.getParent(parentIdStr, null);
        model.addAttribute("parent", parent);
        model.addAttribute("students", studentService.getAllStudents());
        return "/students/selectStudent";
    }

    @RequestMapping(value = "/parents/{parentId}/students/{studentId}/add", method = RequestMethod.POST)
    public String addStudent(@PathVariable("parentId") String parentIdStr, @PathVariable("studentId") String studentIdStr) {

        Parent parent = utils.getParent(parentIdStr, null);
        Student student = utils.getStudent(studentIdStr, null);
        Set<Student> students = parent.getStudents();
        if (!students.contains(student)) {
            students.add(student);
        }

        parentService.saveParent(parent);
        return "redirect:/parents/" + parent.getId();
    }

    @RequestMapping(value = "/parents/{parentId}/students/{studentId}/delete", method = RequestMethod.DELETE)
    public String deleteStudent(@PathVariable("parentId") String parentIdStr, @PathVariable("studentId") String studentIdStr) {

        Parent parent = utils.getParent(parentIdStr, null);
        Student student = utils.getStudent(studentIdStr, null);
        Set<Student> students = parent.getStudents();
        students.remove(student);

        parentService.saveParent(parent);
        return "redirect:/parents/" + parent.getId();
    }

}
