package com.vizaco.onlinecontrol.converters;

import com.vizaco.onlinecontrol.model.Student;
import com.vizaco.onlinecontrol.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

/**
 * Converter for Student control.
 *
 * Created: 01.02.2015
 *
 * @author Oleksandr Zamkovyi
 * @since ???
 */
@Service
public class StringToStudent implements Converter<String, Student> {

    @Autowired
    private StudentService studentService;

    @Override
    public Student convert(String studentId) {
        return studentService.findStudentById(Long.parseLong(studentId));
    }
}