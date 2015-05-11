//package com.vizaco.onlinecontrol.model;
//
//import org.junit.Ignore;
//import org.junit.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.Assert.assertEquals;
//
///**
// * Created by super on 3/10/15.
// */
//@Ignore
//public class UserTest {
//
//    @Test
//    public void oneTimeSetFirstNameTest(){
//        User user = new User();
//        user.setFirstName("John");
//        assertEquals("John", user.getFirstName());
//    }
//
//    @Test
//    public void twoTimeSetFirstNameTest(){
//        User user = new User();
//        user.setFirstName("John");
//        user.setFirstName("Smith");
//        assertEquals("Smith", user.getFirstName());
//    }
//
//    @Test
//    public void setFirstNameThroughConstructorTest(){
//        User user = new User(null, null, "John", "Travolta", "Middle", null, null);
//        assertEquals("John", user.getFirstName());
//    }
//
//    @Test
//    public void setFirstNameNullThroughConstructorTest(){
//        User user = new User(null, null, null, null, null, null, null);
//        assertEquals(null, user.getFirstName());
//    }
//
//    @Test
//    public void setFirstNameNullThroughNoArgConstructorTest(){
//        User user = new User();
//        assertEquals(null, user.getFirstName());
//    }
//
//    @Test
//    public void oneTimeSetMiddleNameTest(){
//        User user = new User();
//        user.setMiddleName("John");
//        assertEquals("John", user.getMiddleName());
//    }
//
//    @Test
//    public void twoTimeSetMiddleNameTest(){
//        User user = new User();
//        user.setMiddleName("John");
//        user.setMiddleName("Smith");
//        assertEquals("Smith", user.getMiddleName());
//    }
//
//    @Test
//    public void setMiddleNameThroughConstructorTest(){
//        User user = new User(null, null, "John", "Travolta", "Middle", null, null);
//        assertEquals("Middle", user.getMiddleName());
//    }
//
//    @Test
//    public void setMiddleNameNullThroughConstructorTest(){
//        User user = new User(null, null, null, null, null, null, null);
//        assertEquals(null, user.getMiddleName());
//    }
//
//    @Test
//    public void setMiddleNameNullThroughNoArgConstructorTest(){
//        User user = new User();
//        assertEquals(null, user.getMiddleName());
//    }
//
//    @Test
//    public void oneTimeSetLastNameTest(){
//        User user = new User();
//        user.setLastName("John");
//        assertEquals("John", user.getLastName());
//    }
//
//    @Test
//    public void twoTimeSetLastNameTest(){
//        User user = new User();
//        user.setLastName("John");
//        user.setLastName("Smith");
//        assertEquals("Smith", user.getLastName());
//    }
//
//    @Test
//    public void setLastNameThroughConstructorTest(){
//        User user = new User(null, null, "John", "Travolta", "Middle", null, null);
//        assertEquals("Travolta", user.getLastName());
//    }
//
//    @Test
//    public void setLastNameNullThroughConstructorTest(){
//        User user = new User(null, null, null, null, null, null, null);
//        assertEquals(null, user.getLastName());
//    }
//
//    @Test
//    public void setLastNameNullThroughNoArgConstructorTest(){
//        User user = new User();
//        assertEquals(null, user.getLastName());
//    }
//
//    @Test
//    public void oneTimeSetLoginTest(){
//        User user = new User();
//        user.setEmail("John");
//        assertEquals("John", user.getEmail());
//    }
//
//    @Test
//    public void twoTimeSetLoginTest(){
//        User user = new User();
//        user.setEmail("John");
//        user.setEmail("Smith");
//        assertEquals("Smith", user.getEmail());
//    }
//
//    @Test
//    public void setLoginThroughConstructorTest(){
//        User user = new User("John", null, null, null, null, null, null);
//        assertEquals("John", user.getEmail());
//    }
//
//    @Test
//    public void setLoginNullThroughConstructorTest(){
//        User user = new User(null, null, null, null, null, null, null);
//        assertEquals(null, user.getEmail());
//    }
//
//    @Test
//    public void setLoginNullThroughNoArgConstructorTest(){
//        User user = new User();
//        assertEquals(null, user.getEmail());
//    }
//
//    @Test
//    public void oneTimeSetPasswordTest(){
//        User user = new User();
//        user.setPassword("John");
//        assertEquals("John", user.getPassword());
//    }
//
//    @Test
//    public void twoTimeSetPasswordTest(){
//        User user = new User();
//        user.setPassword("John");
//        user.setPassword("Smith");
//        assertEquals("Smith", user.getPassword());
//    }
//
//    @Test
//    public void setPasswordThroughConstructorTest(){
//        User user = new User(null, "aaa", null, null, null, null, null);
//        assertEquals("aaa", user.getPassword());
//    }
//
//    @Test
//    public void setPasswordNullThroughConstructorTest(){
//        User user = new User(null, null, null, null, null, null, null);
//        assertEquals(null, user.getPassword());
//    }
//
//    @Test
//    public void setPasswordNullThroughNoArgConstructorTest(){
//        User user = new User();
//        assertEquals(null, user.getPassword());
//    }
//
//    @Test
//    public void oneTimeSetUserIdTest(){
//        Long expected = 1L;
//        User user = new User();
//        user.setUserId(1L);
//        assertEquals(expected, user.getUserId());
//    }
//
//    @Test
//    public void twoTimeSetUserIdTest(){
//        Long expected = 2L;
//        User user = new User();
//        user.setUserId(1L);
//        user.setUserId(2L);
//        assertEquals(expected, user.getUserId());
//    }
//
//    @Test
//    public void addTwoStudentTest(){
//        List<Student> students = new ArrayList();
//        Student student1 = new Student();
//        student1.setStudentId(1L);
//        Student student2 = new Student();
//        student2.setStudentId(2L);
//
//        students.add(student1);
//        students.add(student2);
//
//        User user = new User();
//        user.setStudents(students);
//        assertEquals(students.size(), user.getStudents().size());
//    }
//
//    @Test
//    public void addZeroStudentTest(){
//        List<Student> students = new ArrayList();
//
//        User user = new User();
//        user.setStudents(students);
//        assertEquals(students.size(), user.getStudents().size());
//    }
//
//    @Test
//    public void setStudentsNullThroughConstructorTest(){
//        User user = new User(null, null, null, null, null, null, null);
//        assertEquals(null, user.getStudents());
//    }
//
//    @Test
//    public void setStudentsNullThroughNoArgConstructorTest(){
//        User user = new User();
//        assertEquals(null, user.getStudents());
//    }
//
//    @Test
//    public void addTwoRoleTest(){
//        List<Role> roles = new ArrayList();
//        Role role1 = new Role();
//        role1.setRoleId(1L);
//        Role role2 = new Role();
//        role2.setRoleId(2L);
//
//        roles.add(role1);
//        roles.add(role2);
//
//        User user = new User();
//        user.setRoles(roles);
//        assertEquals(roles.size(), user.getRoles().size());
//    }
//
//    @Test
//    public void addZeroRoleTest(){
//        List<Role> roles = new ArrayList();
//
//        User user = new User();
//        user.setRoles(roles);
//        assertEquals(roles.size(), user.getRoles().size());
//    }
//
//    @Test
//    public void setRolesNullThroughConstructorTest(){
//        User user = new User(null, null, null, null, null, null, null);
//        assertEquals(null, user.getRoles());
//    }
//
//    @Test
//    public void setRolesNullThroughNoArgConstructorTest(){
//        User user = new User();
//        assertEquals(null, user.getRoles());
//    }
//
//    @Test
//    public void isNewUserTest(){
//        User user = new User();
//        assertEquals(true, user.isNew());
//    }
//
//    @Test
//    public void isNotNewUserTest(){
//        User user = new User();
//        user.setUserId(1L);
//        assertEquals(false, user.isNew());
//    }
//
//    @Test
//    public void getTwoAuthoritiesTest(){
//        List<Role> roles = new ArrayList();
//        Role role1 = new Role();
//        role1.setRoleId(1L);
//        Role role2 = new Role();
//        role2.setRoleId(2L);
//
//        roles.add(role1);
//        roles.add(role2);
//
//        User user = new User();
//        user.setRoles(roles);
//        assertEquals(roles.size(), user.getAuthorities().size());
//    }
//
//    @Test
//    public void getZeroAuthoritiesTest(){
//        List<Role> roles = new ArrayList();
//
//        User user = new User();
//        user.setRoles(roles);
//        assertEquals(roles.size(), user.getAuthorities().size());
//    }
//
//    @Test
//    public void getAuthoritiesNullThroughConstructorTest(){
//        User user = new User(null, null, null, null, null, null, null);
//        assertEquals(null, user.getAuthorities());
//    }
//
//    @Test
//    public void getAuthoritiesNullThroughNoArgConstructorTest(){
//        User user = new User();
//        assertEquals(null, user.getAuthorities());
//    }
//
//    @Test
//    public void oneTimeGetUsernameTest(){
//        User user = new User();
//        user.setEmail("John");
//        assertEquals("John", user.getUsername());
//    }
//
//    @Test
//    public void getUsernameThroughConstructorTest(){
//        User user = new User("John", null, null, null, null, null, null);
//        assertEquals("John", user.getUsername());
//    }
//
//    @Test
//    public void getUsernameNullThroughConstructorTest(){
//        User user = new User(null, null, null, null, null, null, null);
//        assertEquals(null, user.getUsername());
//    }
//
//    @Test
//    public void getUsernameNullThroughNoArgConstructorTest(){
//        User user = new User();
//        assertEquals(null, user.getUsername());
//    }
//
//
//}
