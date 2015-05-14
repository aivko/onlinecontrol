package com.vizaco.onlinecontrol.service.impl;

import com.vizaco.onlinecontrol.dao.StudentDao;
import com.vizaco.onlinecontrol.model.Student;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created by super on 3/10/15.
 */
public class StudentServiceImplTest {

    private StudentDao studentDao;

    @Before
    public void setUp() throws Exception {
        studentDao = mock(StudentDao.class);
    }

    private StudentServiceImpl getStudentService() {
        return new StudentServiceImpl(studentDao);
    }

    @Test
    public void StudentServiceImplConstructorWith1ArgTest(){

        StudentServiceImpl studentService = new StudentServiceImpl(studentDao);

        StudentDao actual = studentService.getStudentDao();

        assertEquals(studentDao, actual);

    }

    @Test
    public void StudentServiceImplConstructorWithNoArgTest(){

        StudentServiceImpl studentService = new StudentServiceImpl();

        StudentDao actual = studentService.getStudentDao();

        assertEquals(null, actual);

    }

    @Test
    public void findStudentByLastName1InDBTest(){

        Student student1 = new Student("firstName1", "lastName1", "middleName1", null, null, null, null);
        student1.setStudentId(1L);
        List<Student> resultList  = Arrays.asList(student1);
        when(studentDao.findByLastName("lastName1")).thenReturn(resultList);

        StudentServiceImpl studentService = getStudentService();

        Collection<Student> actual = studentService.findStudentByLastName("lastName1");

        assertEquals(1, actual.size());

    }

    @Test
    public void findByLastName2InDBTest(){

        Student student1 = new Student("firstName1", "lastName1", "middleName1", null, null, null, null);
        student1.setStudentId(1L);
        Student student2 = new Student("firstName2", "lastName2", "middleName2", null, null, null, null);
        student2.setStudentId(2L);
        List<Student> resultList  = Arrays.asList(student1, student2);
        when(studentDao.findByLastName("lastName1")).thenReturn(resultList);

        StudentServiceImpl studentService = getStudentService();

        Collection<Student> actual = studentService.findStudentByLastName("lastName1");

        assertEquals(2, actual.size());

    }

    @Test
    public void findByLastNameWithNullArgumentTest(){

        List<Student> resultList = Collections.EMPTY_LIST;
        when(studentDao.findByLastName(null)).thenReturn(resultList);

        StudentServiceImpl studentService = getStudentService();

        Collection<Student> actual = studentService.findStudentByLastName(null);

        assertEquals(0, actual.size());

    }

    @Test
    public void findStudentById1InDBTest(){

        Student student1 = new Student("firstName1", "lastName1", "middleName1", null, null, null, null);
        student1.setStudentId(1L);
        when(studentDao.findById(1L)).thenReturn(student1);

        StudentServiceImpl studentService = getStudentService();

        Student actual = studentService.findStudentById(1L);

        assertEquals(student1.getStudentId(), actual.getStudentId());
        assertEquals(student1.getFirstName(), actual.getFirstName());
        assertEquals(student1.getMiddleName(), actual.getMiddleName());
        assertEquals(student1.getLastName(), actual.getLastName());

    }

    @Test
    public void findStudentByIdEmptyInDBTest(){

        when(studentDao.findById(99999L)).thenReturn(null);
        StudentServiceImpl studentService = getStudentService();

        Student actual = studentService.findStudentById(99999L);

        assertEquals(null, actual);

    }

    @Test
    public void findStudentByIdWithNullArgumentTest(){

        when(studentDao.findById(null)).thenReturn(null);
        StudentServiceImpl studentService = getStudentService();

        Student actual = studentService.findStudentById(null);

        assertEquals(null, actual);

    }

    @Test
    public void getAllStudents1InDBTest(){

        Student student1 = new Student("firstName1", "lastName1", "middleName1", null, null, null, null);

        List<Student> resultList  = Arrays.asList(student1);
        when(studentDao.getAllStudents()).thenReturn(resultList);
        StudentServiceImpl studentService = getStudentService();

        Collection<Student> actual = studentService.getAllStudents();

        assertEquals(1, actual.size());

    }

    @Test
    public void getAllStudents2InDBTest(){

        Student student1 = new Student("firstName1", "lastName1", "middleName1", null, null, null, null);
        student1.setStudentId(1L);
        Student student2 = new Student("firstName2", "lastName2", "middleName2", null, null, null, null);
        student2.setStudentId(2L);

        List<Student> resultList  = Arrays.asList(student1, student2);
        when(studentDao.getAllStudents()).thenReturn(resultList);
        StudentServiceImpl studentService = getStudentService();

        Collection<Student> actual = studentService.getAllStudents();

        assertEquals(2, actual.size());

    }

    @Test
    public void getAllStudentsEmptyDBTest(){

        List<Student> resultList  = Collections.EMPTY_LIST;
        when(studentDao.getAllStudents()).thenReturn(resultList);
        StudentServiceImpl studentService = getStudentService();

        Collection<Student> actual = studentService.getAllStudents();

        assertEquals(0, actual.size());

    }

    @Test
    public void saveNewStudentTest(){

        Student student1 = new Student("firstName1", "lastName1", "middleName1", null, null, null, null);

        StudentServiceImpl studentService = getStudentService();

        studentService.saveStudent(student1);

        verify(studentDao).save(student1);

    }

    @Test
    public void saveExistStudentTest(){

        Student student1 = new Student("firstName1", "lastName1", "middleName1", null, null, null, null);
        student1.setStudentId(1L);

        StudentServiceImpl studentService = getStudentService();

        studentService.saveStudent(student1);

        verify(studentDao).save(student1);

    }

}
