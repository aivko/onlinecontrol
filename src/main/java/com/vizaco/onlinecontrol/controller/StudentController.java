package com.vizaco.onlinecontrol.controller;

import com.vizaco.onlinecontrol.model.Student;
import com.vizaco.onlinecontrol.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;
import java.util.Map;

@Controller
@SessionAttributes(types = Student.class)
public class StudentController {

    private final StudentService studentService;


    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping(value = "/students/find", method = RequestMethod.GET)
    public String initFindForm(Map<String, Object> model) {
        model.put("student", new Student());
        return "students/findStudents";
    }

    @RequestMapping(value = "/students", method = RequestMethod.GET)
    public String processFindForm(Student student, Map<String, Object> model) {

        // allow parameterless GET       request for /student to return all records
        if (student.getLastName() == null) {
            student.setLastName(""); // empty string signifies broadest possible search
        }

        // find students by last name
        Collection<Student> results = this.studentService.findStudentByLastName(student.getLastName());
        if (results.size() < 1) {
            // no owners found
            return "students/findStudents";
        }
        if (results.size() > 1) {
            // multiple students found
            model.put("selections", results);
            return "students/studentsList";
        } else {
            // 1 owner found
            student = results.iterator().next();
            return "redirect:/students/" + student.getStudentId();
        }
    }

    @RequestMapping(value = "/students/{studentId}/edit", method = RequestMethod.PUT)
    public String processUpdateOwnerForm(Student student) {
        this.studentService.saveStudent(student);
        return "redirect:/students/{studentId}";
    }

    @RequestMapping("/students/{studentId}")
    public ModelAndView showOwner(@PathVariable("studentId") String studentId) {
        ModelAndView mav = new ModelAndView("students/studentDetails");
        mav.addObject(this.studentService.findStudentById(studentId));
        return mav;
    }


}
