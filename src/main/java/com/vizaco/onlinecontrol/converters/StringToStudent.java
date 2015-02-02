package com.vizaco.onlinecontrol.converters;

import com.vizaco.onlinecontrol.model.Student;
import com.vizaco.onlinecontrol.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import java.util.Collections;
import java.util.Set;

/**
 * Converter for Student control.
 *
 * Created: 01.02.2015
 *
 * @author Oleksandr Zamkovyi
 * @since ???
 */
public class StringToStudent implements Converter<String, Student> {
    @Autowired
    StudentService studentService;

    @Override
    public Student convert(String studentId) {
        return studentService.findStudentById(studentId);
    }
}