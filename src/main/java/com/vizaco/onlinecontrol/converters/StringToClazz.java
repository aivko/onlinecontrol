package com.vizaco.onlinecontrol.converters;

import com.vizaco.onlinecontrol.model.Clazz;
import com.vizaco.onlinecontrol.model.Role;
import com.vizaco.onlinecontrol.service.ClazzService;
import com.vizaco.onlinecontrol.service.RoleService;
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
public class StringToClazz implements Converter<String, Clazz> {

    private ClazzService clazzService;

    @Autowired
    public StringToClazz(ClazzService clazzService) {
        this.clazzService = clazzService;
    }

    @Override
    public Clazz convert(String clazzId) {
        return clazzService.findClazzById(Long.parseLong(clazzId));
    }
}