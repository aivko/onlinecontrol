package com.vizaco.onlinecontrol.converters;

import com.vizaco.onlinecontrol.model.Role;
import com.vizaco.onlinecontrol.service.RoleService;
import com.vizaco.onlinecontrol.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

/**
 * Converter for Student control.
 *
 * Created: 01.02.2015
 *
 * @author Oleksandr Zamkovyi
 * @since ???
 */
@Service
public class StringToRole implements Converter<String, Role> {

    @Autowired
    private RoleService roleService;

    @Override
    public Role convert(String roleId) {
        return roleService.findRoleById(Long.parseLong(roleId));
    }
}