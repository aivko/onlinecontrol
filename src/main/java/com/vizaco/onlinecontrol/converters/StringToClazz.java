package com.vizaco.onlinecontrol.converters;

import com.vizaco.onlinecontrol.model.Clazz;
import com.vizaco.onlinecontrol.model.Role;
import com.vizaco.onlinecontrol.service.ClazzService;
import com.vizaco.onlinecontrol.service.RoleService;
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
public class StringToClazz implements Converter<String, Clazz> {

    @Autowired
    private ClazzService clazzService;

    @Override
    public Clazz convert(String clazzId) {
        return clazzService.findClazzById(Long.parseLong(clazzId));
    }
}