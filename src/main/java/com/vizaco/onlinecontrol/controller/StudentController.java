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
package com.vizaco.onlinecontrol.controller;

import com.vizaco.onlinecontrol.model.Student;
import com.vizaco.onlinecontrol.service.OnlineControlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;
import java.util.Map;

/**
 * @author Juergen Hoeller
 * @author Ken Krebs
 * @author Arjen Poutsma
 * @author Michael Isvy
 */
@Controller
@SessionAttributes(types = Student.class)
public class StudentController {

    private final OnlineControlService onlineControlService;


    @Autowired
    public StudentController(OnlineControlService onlineControlService) {
        this.onlineControlService = onlineControlService;
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
        Collection<Student> results = this.onlineControlService.findStudentByLastName(student.getLastName());
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

    @RequestMapping("/students/{studentId}")
    public ModelAndView showOwner(@PathVariable("studentId") String studentId) {
        ModelAndView mav = new ModelAndView("students/studentDetails");
        mav.addObject(this.onlineControlService.findStudentById(studentId));
        return mav;
    }


}
