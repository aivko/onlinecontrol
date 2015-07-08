package com.vizaco.onlinecontrol.converters;

import com.vizaco.onlinecontrol.model.Period;
import com.vizaco.onlinecontrol.model.Role;
import com.vizaco.onlinecontrol.service.RoleService;
import com.vizaco.onlinecontrol.service.SheduleService;
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
public class StringToPeriod implements Converter<String, Period> {

    @Autowired
    private SheduleService sheduleService;

    @Override
    public Period convert(String periodId) {
        return sheduleService.findPeriodById(Long.parseLong(periodId));
    }
}