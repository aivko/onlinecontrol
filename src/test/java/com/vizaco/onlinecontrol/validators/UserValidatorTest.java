//package com.vizaco.onlinecontrol.validators;
//
//import com.vizaco.onlinecontrol.model.Student;
//import com.vizaco.onlinecontrol.model.User;
//import com.vizaco.onlinecontrol.service.UserService;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.validation.BeanPropertyBindingResult;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.Errors;
//
//import static org.junit.Assert.assertEquals;
//import static org.mockito.Matchers.anyString;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
///**
// * Created by super on 3/10/15.
// */
//public class UserValidatorTest {
//
//    private UserValidator userValidator;
//    private UserService mockUserService;
//
//    @Before
//    public void setUp() throws Exception {
//        mockUserService = mock(UserService.class);
//        when(mockUserService.findUserByLogin(anyString())).thenReturn(null);
//        when(mockUserService.findUserByLogin("exist")).thenReturn(new User());
//        userValidator = new UserValidator(mockUserService);
//    }
//
//    @Test
//    public void trueSupportsTest(){
//        boolean actual = userValidator.supports(User.class);
//        assertEquals(true, actual);
//    }
//
//    @Test
//    public void falseSupportsTest(){
//        boolean actual = userValidator.supports(Student.class);
//        assertEquals(false, actual);
//    }
//
//    @Test
//    public void emptyLastNameValidateTest(){
//        User user = new User("login", "password", "firstName", null, "middleName", null, null);
//        BindingResult bindingResult = new BeanPropertyBindingResult(user, "user");
//        userValidator.validate(user, bindingResult);
//        assertEquals(1, bindingResult.getAllErrors().size());
//    }
//    @Test
//    public void emptyfirstNameValidateTest(){
//        User user = new User("login", "password", null, "lastName", "middleName", null, null);
//        BindingResult bindingResult = new BeanPropertyBindingResult(user, "user");
//        userValidator.validate(user, bindingResult);
//        assertEquals(1, bindingResult.getAllErrors().size());
//    }
//    @Test
//    public void emptyMiddleNameValidateTest(){
//        User user = new User("login", "password", "firstName", "lastName", null, null, null);
//        BindingResult bindingResult = new BeanPropertyBindingResult(user, "user");
//        userValidator.validate(user, bindingResult);
//        assertEquals(1, bindingResult.getAllErrors().size());
//    }
//    @Test
//    public void emptyPasswordNameValidateTest(){
//        User user = new User("login", null, "firstName", "lastName", "middleName", null, null);
//        BindingResult bindingResult = new BeanPropertyBindingResult(user, "user");
//        userValidator.validate(user, bindingResult);
//        assertEquals(1, bindingResult.getAllErrors().size());
//    }
//    @Test
//    public void existLoginValidateTest(){
//        User user = new User("exist", "password", "firstName", "lastName", "middleName", null, null);
//        BindingResult bindingResult = new BeanPropertyBindingResult(user, "user");
//        userValidator.validate(user, bindingResult);
//        assertEquals(1, bindingResult.getAllErrors().size());
//    }
//
//    @Test
//    public void existLoginEmptyPasswordFirstNameMiddleNameLastNameValidateTest(){
//        User user = new User("exist", null, null, null, null, null, null);
//        BindingResult bindingResult = new BeanPropertyBindingResult(user, "user");
//        userValidator.validate(user, bindingResult);
//        assertEquals(5, bindingResult.getAllErrors().size());
//    }
//
//    @Test
//    public void emptyLastNameValidateEditTest(){
//        User user = new User("login", "password", "firstName", null, "middleName", null, null);
//        BindingResult bindingResult = new BeanPropertyBindingResult(user, "user");
//        userValidator.validateEdit(user, bindingResult);
//        assertEquals(1, bindingResult.getAllErrors().size());
//    }
//    @Test
//    public void emptyfirstNameValidateEditTest(){
//        User user = new User("login", "password", null, "lastName", "middleName", null, null);
//        BindingResult bindingResult = new BeanPropertyBindingResult(user, "user");
//        userValidator.validateEdit(user, bindingResult);
//        assertEquals(1, bindingResult.getAllErrors().size());
//    }
//    @Test
//    public void emptyMiddleNameValidateEditTest(){
//        User user = new User("login", "password", "firstName", "lastName", null, null, null);
//        BindingResult bindingResult = new BeanPropertyBindingResult(user, "user");
//        userValidator.validateEdit(user, bindingResult);
//        assertEquals(1, bindingResult.getAllErrors().size());
//    }
//    @Test
//    public void emptyPasswordNameValidateEditTest(){
//        User user = new User("login", null, "firstName", "lastName", "middleName", null, null);
//        BindingResult bindingResult = new BeanPropertyBindingResult(user, "user");
//        userValidator.validateEdit(user, bindingResult);
//        assertEquals(1, bindingResult.getAllErrors().size());
//    }
//    @Test
//    public void emptyLoginValidateEditTest(){
//        User user = new User(null, "password", "firstName", "lastName", "middleName", null, null);
//        BindingResult bindingResult = new BeanPropertyBindingResult(user, "user");
//        userValidator.validateEdit(user, bindingResult);
//        assertEquals(1, bindingResult.getAllErrors().size());
//    }
//
//    @Test
//    public void existLoginEmptyPasswordFirstNameMiddleNameLastNameValidateEditTest(){
//        User user = new User(null, null, null, null, null, null, null);
//        BindingResult bindingResult = new BeanPropertyBindingResult(user, "user");
//        userValidator.validateEdit(user, bindingResult);
//        assertEquals(5, bindingResult.getAllErrors().size());
//    }
//
//}
