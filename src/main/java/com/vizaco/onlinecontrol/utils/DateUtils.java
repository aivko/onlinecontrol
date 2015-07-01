package com.vizaco.onlinecontrol.utils;

import com.vizaco.onlinecontrol.exceptions.CustomGenericException;
import com.vizaco.onlinecontrol.model.*;
import com.vizaco.onlinecontrol.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.*;

/**
 * Created by super on 2/20/15.
 */
@Service
public class DateUtils {

    @Autowired
    private Locale locale;

    public Map<Integer, String> getAllDaysOfTheWeek() {
        Map<Integer, String> daysOfWeek = new HashMap<>();
        for (DayOfWeek dayOfWeek : DayOfWeek.values()) {
            daysOfWeek.put(dayOfWeek.getValue(), dayOfWeek.getDisplayName(TextStyle.FULL_STANDALONE, locale));
        }
        return daysOfWeek;
    }

    public int getNumberDayOfWeek(GregorianCalendar startDate) {
        int dayOfWeek = startDate.get(Calendar.DAY_OF_WEEK) - 1;
        if (dayOfWeek == 0){
            dayOfWeek = 7;
        }
        return dayOfWeek;
    }

}
