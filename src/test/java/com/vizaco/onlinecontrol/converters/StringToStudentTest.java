package com.vizaco.onlinecontrol.converters;

import com.vizaco.onlinecontrol.model.Role;
import com.vizaco.onlinecontrol.model.Student;
import com.vizaco.onlinecontrol.model.User;
import com.vizaco.onlinecontrol.service.StudentService;
import com.vizaco.onlinecontrol.service.UserService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by super on 3/10/15.
 */
@Ignore
public class StringToStudentTest {

    private StudentService mockStudentService;

    private StringToStudent stringToStudent;

    @Before
    public void setUp() throws Exception {
        mockStudentService = mock(StudentService.class);
        when(mockStudentService.findStudentById(anyLong())).thenReturn(null);
        Student student1 = new Student();
        student1.setStudentId(1L);
        when(mockStudentService.findStudentById(1L)).thenReturn(student1);
        Student student2 = new Student();
        student2.setStudentId(2L);
        when(mockStudentService.findStudentById(2L)).thenReturn(student2);

        stringToStudent = new StringToStudent(mockStudentService);
    }

    @Test
    public void convertRoleWithId1Test(){
        Long expected = 1L;
        Student actual = stringToStudent.convert("1");
        assertEquals(expected, actual.getStudentId());
    }

    @Test
    public void convertRoleWithId2Test(){
        Long expected = 2L;
        Student actual = stringToStudent.convert("2");
        assertEquals(expected, actual.getStudentId());
    }

    @Test
    public void convertRoleWithId3Test(){
        Student actual = stringToStudent.convert("3");

        Long expected = null;
        assertEquals(expected, actual);
    }

}
