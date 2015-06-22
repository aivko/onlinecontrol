//package com.vizaco.onlinecontrol.converters;
//
//import com.vizaco.onlinecontrol.model.Student;
//import com.vizaco.onlinecontrol.service.StudentService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.convert.converter.Converter;
//import org.springframework.stereotype.Service;
//
///**
// * Converter for Student control.
// *
// * Created: 01.02.2015
// *
// * @author Oleksandr Zamkovyi
// * @since ???
// */
//public class StringToStudent implements Converter<String, Student> {
//
//    private StudentService studentService;
//
//    @Autowired
//    public StringToStudent(StudentService studentService) {
//        this.studentService = studentService;
//    }
//
//    @Override
//    public Student convert(String studentId) {
//        return studentService.findStudentById(Long.parseLong(studentId));
//    }
//}