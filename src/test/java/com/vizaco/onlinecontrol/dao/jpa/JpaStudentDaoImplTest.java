package com.vizaco.onlinecontrol.dao.jpa;

import com.vizaco.onlinecontrol.dao.StudentDao;
import com.vizaco.onlinecontrol.enumeration.Gender;
import com.vizaco.onlinecontrol.model.Clazz;
import com.vizaco.onlinecontrol.model.Role;
import com.vizaco.onlinecontrol.model.Student;
import com.vizaco.onlinecontrol.model.User;
import com.vizaco.onlinecontrol.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by super on 3/10/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/application-context.xml"})
public class JpaStudentDaoImplTest {

    @PersistenceContext
    private EntityManager mockEntityManager;

    @Before
    public void setUp() throws Exception {
        mockEntityManager = mock(EntityManager.class);
    }

    private JpaStudentDaoImpl getStudentDao(List<Student> resultList) {
        Query mockQuery = mock(Query.class);
        when(mockQuery.getResultList()).thenReturn(resultList);

        when(mockEntityManager.createQuery(anyString())).thenReturn(mockQuery);

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

}
