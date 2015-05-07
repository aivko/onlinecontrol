//package com.vizaco.onlinecontrol.model;
//
//import org.junit.Ignore;
//import org.junit.Test;
//
//import java.util.HashSet;
//
//import static org.junit.Assert.assertEquals;
//
///**
// * Created by super on 3/10/15.
// */
//@Ignore
//public class RoleTest {
//
//    @Test
//    public void oneTimeSetNameTest(){
//        Role role = new Role();
//        role.setName("Admin");
//        assertEquals("Admin", role.getName());
//    }
//
//    @Test
//    public void twoTimeSetNameTest(){
//        Role role = new Role();
//        role.setName("Admin");
//        role.setName("User");
//        assertEquals("User", role.getName());
//    }
//
//    @Test
//    public void setNameThroughConstructorTest(){
//        Role role = new Role("Admin", "Admin");
//        assertEquals("Admin", role.getName());
//    }
//
//    @Test
//    public void setNameNullThroughConstructorTest(){
//        Role role = new Role(null, null);
//        assertEquals(null, role.getName());
//    }
//
//    @Test
//    public void setNameNullThroughNoArgConstructorTest(){
//        Role role = new Role(null, null);
//        assertEquals(null, role.getName());
//    }
//
//    @Test
//    public void oneTimeSetRoleIdTest(){
//        Long expected = 1L;
//        Role role = new Role(null, null);
//        role.setRoleId(1L);
//        assertEquals(expected, role.getRoleId());
//    }
//
//    @Test
//    public void twoTimeSetRoleIdTest(){
//        Long expected = 2L;
//        Role role = new Role(null, null);
//        role.setRoleId(1L);
//        role.setRoleId(2L);
//        assertEquals(expected, role.getRoleId());
//    }
//
//    @Test
//    public void oneTimeSetAuthorityTest(){
//        Role role = new Role();
//        role.setName("Admin");
//        assertEquals("Admin", role.getAuthority());
//    }
//
//    @Test
//    public void twoTimeSetAuthorityTest(){
//        Role role = new Role();
//        role.setName("Admin");
//        role.setName("User");
//        assertEquals("User", role.getAuthority());
//    }
//
//    @Test
//    public void setAuthorityThroughConstructorTest(){
//        Role role = new Role("Admin", "Admin");
//        assertEquals("Admin", role.getAuthority());
//    }
//
//    @Test
//    public void setAuthorityNullThroughConstructorTest(){
//        Role role = new Role(null, null);
//        assertEquals(null, role.getAuthority());
//    }
//
//    @Test
//    public void setAuthorityNullThroughNoArgConstructorTest(){
//        Role role = new Role(null, null);
//        assertEquals(null, role.getAuthority());
//    }
//
//}
