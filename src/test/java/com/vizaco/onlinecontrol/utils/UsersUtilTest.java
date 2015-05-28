//package com.vizaco.onlinecontrol.utils;
//
//import com.vizaco.onlinecontrol.exceptions.CustomGenericException;
//import com.vizaco.onlinecontrol.model.User;
//import com.vizaco.onlinecontrol.service.UserService;
//import org.junit.Before;
//import org.junit.Test;
//
//import static org.junit.Assert.assertEquals;
//import static org.mockito.Matchers.any;
//import static org.mockito.Matchers.anyLong;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
///**
// * Created by super on 3/10/15.
// */
//public class UsersUtilTest {
//
//    private UserService mockUserService;
//
//    private Utils utils;
//
//    @Before
//    public void setUp() throws Exception {
//        mockUserService = mock(UserService.class);
//        when(mockUserService.findUserById(anyLong())).thenReturn(null);
//        User user1 = new User();
//        user1.setUserId(1L);
//        when(mockUserService.findUserById(1L)).thenReturn(user1);
//        User user2 = new User();
//        user2.setUserId(2L);
//        when(mockUserService.findUserById(2L)).thenReturn(user2);
//
//        utils = new Utils();
//    }
//
//    @Test
//    public void getUserWithId1Test(){
//        Long expected = 1L;
//        User actualUser = utils.getUser("1", mockUserService);
//        assertEquals(expected, actualUser.getUserId());
//    }
//
//    @Test
//    public void getUserWithId2Test(){
//        Long expected = 2L;
//        User actualUser = utils.getUser("2", mockUserService);
//        assertEquals(expected, actualUser.getUserId());
//    }
//
//    @Test(expected=CustomGenericException.class)
//    public void getUserWithIncorrictNumberIdTest(){
//        User actualUser = utils.getUser("abc", mockUserService);
//    }
//
//    @Test(expected=CustomGenericException.class)
//    public void getUserWithNoExistIdIdTest(){
//        User actualUser = utils.getUser("100", mockUserService);
//    }
//
//}
