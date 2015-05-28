//package com.vizaco.onlinecontrol.model;
//
//import com.vizaco.onlinecontrol.enumeration.Gender;
//import org.junit.Ignore;
//import org.junit.Test;
//
//import java.util.*;
//
//import static org.junit.Assert.assertEquals;
//
///**
// * Created by super on 3/10/15.
// */
//@Ignore
//public class StudentTest {
//
//    @Test
//    public void oneTimeSetFirstNameTest(){
//        Student student = new Student();
//        student.setFirstName("John");
//        assertEquals("John", student.getFirstName());
//    }
//
//    @Test
//    public void twoTimeSetFirstNameTest(){
//        Student student = new Student();
//        student.setFirstName("John");
//        student.setFirstName("Smith");
//        assertEquals("Smith", student.getFirstName());
//    }
//
//    @Test
//    public void setFirstNameThroughConstructorTest(){
//        Student student = new Student("John", "Travolta", "Middle", new GregorianCalendar(1985, 10, 10).getTime(), Gender.MALE, null, null);
//        assertEquals("John", student.getFirstName());
//    }
//
//    @Test
//    public void setFirstNameNullThroughConstructorTest(){
//        Student student = new Student(null, null, null, null, null, null, null);
//        assertEquals(null, student.getFirstName());
//    }
//
//    @Test
//    public void setFirstNameNullThroughNoArgConstructorTest(){
//        Student student = new Student();
//        assertEquals(null, student.getFirstName());
//    }
//
//    @Test
//    public void oneTimeSetMiddleNameTest(){
//        Student student = new Student();
//        student.setMiddleName("John");
//        assertEquals("John", student.getMiddleName());
//    }
//
//    @Test
//    public void twoTimeSetMiddleNameTest(){
//        Student student = new Student();
//        student.setMiddleName("John");
//        student.setMiddleName("Smith");
//        assertEquals("Smith", student.getMiddleName());
//    }
//
//    @Test
//    public void setMiddleNameThroughConstructorTest(){
//        Student student = new Student("John", "Travolta", "Middle", new GregorianCalendar(1985, 10, 10).getTime(), Gender.MALE, null, null);
//        assertEquals("Middle", student.getMiddleName());
//    }
//
//    @Test
//    public void setMiddleNameNullThroughConstructorTest(){
//        Student student = new Student(null, null, null, null, null, null, null);
//        assertEquals(null, student.getMiddleName());
//    }
//
//    @Test
//    public void setMiddleNameNullThroughNoArgConstructorTest(){
//        Student student = new Student();
//        assertEquals(null, student.getMiddleName());
//    }
//
//    @Test
//    public void oneTimeSetLastNameTest(){
//        Student student = new Student();
//        student.setLastName("John");
//        assertEquals("John", student.getLastName());
//    }
//
//    @Test
//    public void twoTimeSetLastNameTest(){
//        Student student = new Student();
//        student.setLastName("John");
//        student.setLastName("Smith");
//        assertEquals("Smith", student.getLastName());
//    }
//
//    @Test
//    public void setLastNameThroughConstructorTest(){
//        Student student = new Student("John", "Travolta", "Middle", new GregorianCalendar(1985, 10, 10).getTime(), Gender.MALE, null, null);
//        assertEquals("Travolta", student.getLastName());
//    }
//
//    @Test
//    public void setLastNameNullThroughConstructorTest(){
//        Student student = new Student(null, null, null, null, null, null, null);
//        assertEquals(null, student.getLastName());
//    }
//
//    @Test
//    public void setLastNameNullThroughNoArgConstructorTest(){
//        Student student = new Student();
//        assertEquals(null, student.getLastName());
//    }
//
//    @Test
//    public void oneTimeSetDateOfBirthTest(){
//        Date dateOfBirth = new GregorianCalendar(1985, 10, 10).getTime();
//        Student student = new Student();
//        student.setDateOfBirth(new GregorianCalendar(1985, 10, 10).getTime());
//        assertEquals(dateOfBirth, student.getDateOfBirth());
//    }
//
//    @Test
//    public void twoTimeSetDateOfBirthTest(){
//        Date dateOfBirth = new GregorianCalendar(1985, 10, 20).getTime();
//        Student student = new Student();
//        student.setDateOfBirth(new GregorianCalendar(1985, 10, 10).getTime());
//        student.setDateOfBirth(new GregorianCalendar(1985, 10, 20).getTime());
//        assertEquals(dateOfBirth, student.getDateOfBirth());
//    }
//
//    @Test
//    public void setDateOfBirthThroughConstructorTest(){
//        Date dateOfBirth = new GregorianCalendar(1985, 10, 11).getTime();
//        Student student = new Student("John", "Travolta", "Middle", new GregorianCalendar(1985, 10, 11).getTime(), Gender.MALE, null, null);
//        assertEquals(dateOfBirth, student.getDateOfBirth());
//    }
//
//    @Test
//    public void setDateOfBirthNullThroughConstructorTest(){
//        Student student = new Student(null, null, null, null, null, null, null);
//        assertEquals(null, student.getDateOfBirth());
//    }
//
//    @Test
//    public void setDateOfBirthNullThroughNoArgConstructorTest(){
//        Student student = new Student();
//        assertEquals(null, student.getDateOfBirth());
//    }
//
//    @Test
//    public void oneTimeSetGenderTest(){
//        Student student = new Student();
//        student.setGender(Gender.MALE);
//        assertEquals(Gender.MALE, student.getGender());
//    }
//
//    @Test
//    public void twoTimeSetGenderTest(){
//        Student student = new Student();
//        student.setGender(Gender.MALE);
//        student.setGender(Gender.FEMALE);
//        assertEquals(Gender.FEMALE, student.getGender());
//    }
//
//    @Test
//    public void setGenderThroughConstructorTest(){
//        Student student = new Student(null, null, null, null, Gender.MALE, null, null);
//        assertEquals(Gender.MALE, student.getGender());
//    }
//
//    @Test
//    public void setGenderNullThroughConstructorTest(){
//        Student student = new Student(null, null, null, null, null, null, null);
//        assertEquals(null, student.getGender());
//    }
//
//    @Test
//    public void setGenderNullThroughNoArgConstructorTest(){
//        Student student = new Student();
//        assertEquals(null, student.getGender());
//    }
//
//    @Test
//    public void oneTimeSetStudentIdTest(){
//        Long expected = 1L;
//        Student student = new Student();
//        student.setStudentId(1L);
//        assertEquals(expected, student.getStudentId());
//    }
//
//    @Test
//    public void twoTimeSetStudentIdTest(){
//        Long expected = 2L;
//        Student student = new Student();
//        student.setStudentId(1L);
//        student.setStudentId(2L);
//        assertEquals(expected, student.getStudentId());
//    }
//
//    @Test
//    public void oneTimeSetClazzTest(){
//        Clazz expected = new Clazz();
//        expected.setName("1-A");
//        Student student = new Student();
//        Clazz actual = new Clazz();
//        actual.setName("1-A");
//        student.setClazz(actual);
//        assertEquals(expected.getName(), student.getClazz().getName());
//    }
//
//    @Test
//    public void twoTimeSetClazzTest(){
//        Clazz expected = new Clazz();
//        expected.setName("1-B");
//        Student student = new Student();
//        Clazz temp = new Clazz();
//        temp.setName("1-A");
//        Clazz actual = new Clazz();
//        actual.setName("1-B");
//        student.setClazz(temp);
//        student.setClazz(actual);
//        assertEquals(expected.getName(), student.getClazz().getName());
//    }
//
//    @Test
//    public void setClazzNullTest(){
//        Student student = new Student();
//        assertEquals(null, student.getClazz());
//    }
//
//    @Test
//    public void setClazzNullThroughConstructorTest(){
//        Student student = new Student(null, null, null, null, null, null, null);
//        assertEquals(null, student.getClazz());
//    }
//
//    @Test
//    public void setClazzNullThroughNoArgConstructorTest(){
//        Student student = new Student();
//        assertEquals(null, student.getClazz());
//    }
//
//    @Test
//    public void setClazzThroughConstructorTest(){
//        Clazz expected = new Clazz();
//        expected.setName("1-A");
//        Clazz actual = new Clazz();
//        actual.setName("1-A");
//        Student student = new Student(null, null, null, null, null, null, actual);
//        assertEquals(expected.getName(), student.getClazz().getName());
//    }
//
//    @Test
//    public void addTwoUserTest(){
//        List<User> users = new ArrayList<User>();
//        User user1 = new User();
//        user1.setUserId(1L);
//        User user2 = new User();
//        user2.setUserId(2L);
//
//        users.add(user1);
//        users.add(user2);
//
//        Student student = new Student();
//        student.setUsers(users);
//        assertEquals(users.size(), student.getUsers().size());
//    }
//
//    @Test
//    public void addZeroUserTest(){
//        ArrayList users = new ArrayList();
//
//        Student student = new Student();
//        student.setUsers(new ArrayList<User>());
//        assertEquals(users.size(), student.getUsers().size());
//    }
//
//    @Test
//    public void setUsersNullThroughConstructorTest(){
//        Student student = new Student(null, null, null, null, null, null, null);
//        assertEquals(null, student.getUsers());
//    }
//
//    @Test
//    public void setUsersNullThroughNoArgConstructorTest(){
//        Student student = new Student();
//        assertEquals(null, student.getUsers());
//    }
//
//    @Test
//    public void isNewStudentTest(){
//        Student student = new Student();
//        assertEquals(true, student.isNew());
//    }
//
//    @Test
//    public void isNotNewStudentTest(){
//        Student student = new Student();
//        student.setStudentId(1L);
//        assertEquals(false, student.isNew());
//    }
//
//}
