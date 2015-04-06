package com.vizaco.onlinecontrol.dao.jpa;

import com.vizaco.onlinecontrol.dao.StudentDao;
import com.vizaco.onlinecontrol.enumeration.Gender;
import com.vizaco.onlinecontrol.model.Clazz;
import com.vizaco.onlinecontrol.model.Role;
import com.vizaco.onlinecontrol.model.Student;
import com.vizaco.onlinecontrol.model.User;
import com.vizaco.onlinecontrol.service.StudentService;
import com.vizaco.onlinecontrol.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Created by super on 3/10/15.
 */
public class JpaStudentDaoImplTest {

    @PersistenceContext
    private EntityManager mockEntityManager;

    @Before
    public void setUp() throws Exception {
        mockEntityManager = mock(EntityManager.class);
    }

    private JpaStudentDaoImpl getStudentDao(List<Student> resultList) {
        Query spyQuery = spy(Query.class);
        when(spyQuery.getResultList()).thenReturn(resultList);

        when(mockEntityManager.createQuery(anyString())).thenReturn(spyQuery);
        when(mockEntityManager.createNamedQuery(anyString())).thenReturn(spyQuery);
        when(mockEntityManager.createNativeQuery(anyString())).thenReturn(spyQuery);

        return new JpaStudentDaoImpl(mockEntityManager);
    }

    @Test
    public void findByLastName1InDBTest(){

        Student student1 = new Student("firstName1", "lastName1", "middleName1", null, null, null, null);

        List<Student> resultList  = Arrays.asList(student1);
        JpaStudentDaoImpl studentDao = getStudentDao(resultList);

        Collection<Student> actual = studentDao.findByLastName("lastName1");

        assertEquals(1, actual.size());

    }

    @Test
    public void findByLastName2InDBTest(){

        Student student1 = new Student("firstName1", "lastName1", "middleName1", null, null, null, null);
        Student student2 = new Student("firstName2", "lastName1", "middleName2", null, null, null, null);

        List<Student> resultList  = Arrays.asList(student1, student2);
        JpaStudentDaoImpl studentDao = getStudentDao(resultList);

        Collection<Student> actual = studentDao.findByLastName("lastName1");

        assertEquals(2, actual.size());

    }

    @Test
    public void findByLastNameWithNullArgumentTest(){

        List<Student> resultList  = Collections.EMPTY_LIST;
        JpaStudentDaoImpl studentDao = getStudentDao(resultList);

        Collection<Student> actual = studentDao.findByLastName(null);

        assertEquals(0, actual.size());

    }

    @Test
    public void findById1InDBTest(){

        Student student1 = new Student("firstName1", "lastName1", "middleName1", null, null, null, null);
        student1.setStudentId(1L);

        List<Student> resultList  = Arrays.asList(student1);
        JpaStudentDaoImpl studentDao = getStudentDao(resultList);

        Student actual = studentDao.findById(1L);

        assertEquals(student1.getStudentId(), actual.getStudentId());
        assertEquals(student1.getFirstName(), actual.getFirstName());
        assertEquals(student1.getMiddleName(), actual.getMiddleName());
        assertEquals(student1.getLastName(), actual.getLastName());

    }

    @Test
    public void findById2InDBTest(){

        Student student1 = new Student("firstName1", "lastName1", "middleName1", null, null, null, null);
        student1.setStudentId(1L);
        Student student2 = new Student("firstName2", "lastName2", "middleName2", null, null, null, null);
        student2.setStudentId(1L);

        List<Student> resultList  = Arrays.asList(student1, student2);
        JpaStudentDaoImpl studentDao = getStudentDao(resultList);

        Student actual = studentDao.findById(1L);

        assertEquals(student1.getStudentId(), actual.getStudentId());
        assertEquals(student1.getFirstName(), actual.getFirstName());
        assertEquals(student1.getMiddleName(), actual.getMiddleName());
        assertEquals(student1.getLastName(), actual.getLastName());

    }

    @Test
    public void findByIdEmptyInDBTest(){

        List<Student> resultList  = Collections.EMPTY_LIST;
        JpaStudentDaoImpl studentDao = getStudentDao(resultList);

        Student actual = studentDao.findById(99999L);

        assertEquals(null, actual);

    }

    @Test
    public void findByIdWithNullArgumentTest(){

        List<Student> resultList  = Collections.EMPTY_LIST;
        JpaStudentDaoImpl studentDao = getStudentDao(resultList);

        Student actual = studentDao.findById(null);

        assertEquals(null, actual);

    }

    @Test
    public void getAllStudents1InDBTest(){

        Student student1 = new Student("firstName1", "lastName1", "middleName1", null, null, null, null);

        List<Student> resultList  = Arrays.asList(student1);
        JpaStudentDaoImpl studentDao = getStudentDao(resultList);

        Collection<Student> actual = studentDao.getAllStudents();

        assertEquals(1, actual.size());

    }

    @Test
    public void getAllStudents2InDBTest(){

        Student student1 = new Student("firstName1", "lastName1", "middleName1", null, null, null, null);
        Student student2 = new Student("firstName2", "lastName1", "middleName2", null, null, null, null);

        List<Student> resultList  = Arrays.asList(student1, student2);
        JpaStudentDaoImpl studentDao = getStudentDao(resultList);

        Collection<Student> actual = studentDao.getAllStudents();

        assertEquals(2, actual.size());

    }

    @Test
    public void saveNewStudentTest(){

        Student student1 = new Student("firstName1", "lastName1", "middleName1", null, null, null, null);

        JpaStudentDaoImpl studentDao = getStudentDao(null);

        studentDao.save(student1);

        verify(mockEntityManager).persist(student1);

    }

    @Test
    public void saveExistStudentTest(){

        Student student1 = new Student("firstName1", "lastName1", "middleName1", null, null, null, null);
        student1.setStudentId(1L);

        JpaStudentDaoImpl studentDao = getStudentDao(null);

        studentDao.save(student1);

        verify(mockEntityManager).merge(student1);

    }

    @Test
    public void saveNullStudentTest(){

        Student student1 = null;

        JpaStudentDaoImpl studentDao = getStudentDao(null);

        studentDao.save(student1);

        verify(mockEntityManager, never()).persist(student1);
        verify(mockEntityManager, never()).merge(student1);

    }

}
