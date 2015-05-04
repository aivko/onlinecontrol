package com.vizaco.onlinecontrol.converters;

import com.vizaco.onlinecontrol.model.Role;
import com.vizaco.onlinecontrol.model.User;
import com.vizaco.onlinecontrol.service.RoleService;
import com.vizaco.onlinecontrol.service.UserService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by super on 3/10/15.
 */
@Ignore
public class StringToRoleTest {

    private UserService mockUserService;
    private RoleService mockRoleService;

    private StringToRole stringToRole;

    @Before
    public void setUp() throws Exception {
        mockUserService = mock(UserService.class);
        when(mockRoleService.findRoleById(anyLong())).thenReturn(null);
        Role role1 = new Role();
        role1.setRoleId(1L);
        when(mockRoleService.findRoleById(1L)).thenReturn(role1);
        Role role2 = new Role();
        role2.setRoleId(2L);
        when(mockRoleService.findRoleById(2L)).thenReturn(role2);

        stringToRole = new StringToRole(mockRoleService);
    }

    @Test
    public void convertRoleWithId1Test(){
        Long expected = 1L;
        Role actual = stringToRole.convert("1");
        assertEquals(expected, actual.getRoleId());
    }

    @Test
    public void convertRoleWithId2Test(){
        Long expected = 2L;
        Role actual = stringToRole.convert("2");
        assertEquals(expected, actual.getRoleId());
    }

    @Test
    public void convertRoleWithId3Test(){
        Role actual = stringToRole.convert("3");

        Long expected = null;
        assertEquals(expected, actual);
    }

}
