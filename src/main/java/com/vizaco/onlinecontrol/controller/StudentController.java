package com.vizaco.onlinecontrol.controller;

import com.vizaco.onlinecontrol.model.Role;
import com.vizaco.onlinecontrol.model.Student;
import com.vizaco.onlinecontrol.service.ClazzService;
import com.vizaco.onlinecontrol.service.StudentService;
import com.vizaco.onlinecontrol.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class StudentController {

    private final StudentService studentService;
    private final ClazzService clazzService;

    private Utils utils = new Utils();

    @Autowired
    public StudentController(StudentService studentService, ClazzService clazzService) {
        this.studentService = studentService;
        this.clazzService = clazzService;
    }

//    @InitBinder
//    public void initBinder(WebDataBinder binder) {
//        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
//        sdf.setLenient(true);
//        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
//    }

    @RequestMapping(value = "/students/find", method = RequestMethod.GET)
    public String initFindForm(Map<String, Object> model) {
        model.put("student", new Student());
        return "students/findStudents";
    }

    @RequestMapping(value = "/students/find", method = RequestMethod.POST)
    public String processFindForm(Student student, Map<String, Object> model) {

        String lastName;
        // allow parameterless GET       request for /student to return all records
        if (student != null) {
            lastName = student.getLastName(); // empty string signifies broadest possible search
        }else {
            return "students/findStudents";
        }

        // find students by last name
        Collection<Student> results = this.studentService.findStudentByLastName(lastName);
        if (results.size() < 1) {
            // no owners found
            return "students/findStudents";
        }
        if (results.size() > 1) {
            // multiple students found
            model.put("students", results);
            return "students/students";
        } else {
            // 1 owner found
            student = results.iterator().next();
            return "redirect:/students/" + student.getStudentId();
        }
    }

    @RequestMapping(value = "/students")
    public ModelAndView initStudentsForm() {

        ModelAndView mav = new ModelAndView("students/students");

        List<Student> students = studentService.getAllStudents();

        mav.addObject("students", students);

        return mav;
    }

    @RequestMapping(value = "/students/select")
    public ModelAndView testForm() {

        ModelAndView mav = new ModelAndView("students/select");

        List<Student> students = studentService.getAllStudents();

        mav.addObject("students", students);

        return mav;
    }

    //<editor-fold desc="CRUD STUDENT">
    //CREATE STUDENT

    @RequestMapping(value = "/students/new", method = RequestMethod.GET)
    public String register(Model model) {
        Student student = new Student();
        model.addAttribute(student);
        return "/students/createOrUpdateStudentForm";
    }

    @RequestMapping(value = "/students/new", method = RequestMethod.POST)
    public String saveRole(@ModelAttribute("student") @Valid Student student, BindingResult result, Model model) {

        if(result.hasErrors()){
            model.addAttribute(student);
            return "/students/createOrUpdateStudentForm";
        }

        studentService.saveStudent(student);
        return "redirect:/students/";
    }

    //READ STUDENT

    @RequestMapping(value = "/students/{studentsId}")
    public ModelAndView initAccountForm(@PathVariable("studentsId") String studentIdStr) {

        Student student = utils.getStudent(studentIdStr, studentService);

        ModelAndView mav = new ModelAndView("/students/studentDetails");

        mav.addObject("student", student);

        return mav;
    }

    //UPDATE STUDENT

    @RequestMapping(value = "/students/{studentId}/edit", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable("studentId") String studentIdStr) {

        Student student = utils.getStudent(studentIdStr, studentService);
        ModelAndView mav = new ModelAndView("/students/createOrUpdateStudentForm");

        mav.addObject("student", student);

        return mav;
    }

    @RequestMapping(value = "/students/{studentId}/edit", method = RequestMethod.PUT)
    public String edit(@PathVariable("studentId") String studentIdStr, @ModelAttribute("student") @Valid Student student, BindingResult result, Model model) {

        Student studentEdit = utils.getStudent(studentIdStr, studentService);

        if(result.hasErrors()){
            model.addAttribute("student", student);
            return "/students/createOrUpdateStudentForm";
        }

        student.setStudentId(studentEdit.getStudentId());
        studentService.saveStudent(student);
        return "redirect:/students/";
    }

    //DELETE STUDENT

    @RequestMapping(value = "/students/{studentId}/delete", method = RequestMethod.DELETE)
    public ModelAndView deleteUser(@PathVariable("studentId") String studentIdStr) {

        Student student = utils.getStudent(studentIdStr, studentService);
        studentService.deleteStudent(student.getStudentId());

        return new ModelAndView("redirect:/students");

    }
    //</editor-fold>

}
