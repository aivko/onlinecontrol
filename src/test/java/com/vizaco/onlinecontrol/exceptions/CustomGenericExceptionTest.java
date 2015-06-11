//package com.vizaco.onlinecontrol.exceptions;
//
//import com.vizaco.onlinecontrol.model.Role;
//import org.junit.Test;
//
//import static org.junit.Assert.assertEquals;
//
///**
// * Created by super on 3/10/15.
// */
//public class CustomGenericExceptionTest {
//
//    @Test
//    public void oneTimeSetErrorCodeTest(){
//        CustomGenericException exception = new CustomGenericException("1", null);
//        exception.setErrorCode("2");
//        assertEquals("2", exception.getErrorCode());
//    }
//
//    @Test
//    public void twoTimeSetErrorCodeTest(){
//        CustomGenericException exception = new CustomGenericException("1", null);
//        exception.setErrorCode("2");
//        exception.setErrorCode("3");
//        assertEquals("3", exception.getErrorCode());
//    }
//
//    @Test
//    public void setErrorCodeThroughConstructorTest(){
//        CustomGenericException exception = new CustomGenericException("1", null);
//        assertEquals("1", exception.getErrorCode());
//    }
//
//    @Test
//    public void setErrorCodeNullThroughConstructorTest(){
//        CustomGenericException exception = new CustomGenericException(null, null);
//        assertEquals(null, exception.getErrorCode());
//    }
//
//    @Test
//    public void oneTimeSetErrorMsgTest(){
//        CustomGenericException exception = new CustomGenericException(null, "error");
//        exception.setErrorMsg("error1");
//        assertEquals("error1", exception.getErrorMsg());
//    }
//
//    @Test
//    public void twoTimeSetErrorMsgTest(){
//        CustomGenericException exception = new CustomGenericException(null, "error");
//        exception.setErrorMsg("error2");
//        exception.setErrorMsg("error3");
//        assertEquals("error3", exception.getErrorMsg());
//    }
//
//    @Test
//    public void setErrorMsgThroughConstructorTest(){
//        CustomGenericException exception = new CustomGenericException(null, "error");
//        assertEquals("error", exception.getErrorMsg());
//    }
//
//    @Test
//    public void setErrorMsgNullThroughConstructorTest(){
//        CustomGenericException exception = new CustomGenericException(null, null);
//        assertEquals(null, exception.getErrorMsg());
//    }
//
//}
