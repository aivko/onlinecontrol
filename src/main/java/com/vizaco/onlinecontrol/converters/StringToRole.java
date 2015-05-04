package com.vizaco.onlinecontrol.converters;

import com.vizaco.onlinecontrol.model.Role;
import com.vizaco.onlinecontrol.service.RoleService;
import com.vizaco.onlinecontrol.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

/**
 * Converter for Student control.
 *
 * Created: 01.02.2015
 *
 * @author Oleksandr Zamkovyi
 * @since ???
 */
public class StringToRole implements Converter<String, Role> {

    private RoleService roleService;

    @Autowired
    public StringToRole(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public Role convert(String roleId) {
        return roleService.findRoleById(Long.parseLong(roleId));
    }
}