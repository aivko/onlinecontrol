package com.vizaco.onlinecontrol.converters;

import com.vizaco.onlinecontrol.model.Subject;
import com.vizaco.onlinecontrol.model.Teacher;
import com.vizaco.onlinecontrol.service.SheduleService;
import com.vizaco.onlinecontrol.service.SubjectService;
import com.vizaco.onlinecontrol.service.TeacherService;
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
public class StringToTeacher implements Converter<String, Teacher> {

    @Autowired
    private TeacherService teacherService;

    @Override
    public Teacher convert(String teacherId) {
        return teacherService.findTeacherById(Integer.parseInt(teacherId));
    }
}