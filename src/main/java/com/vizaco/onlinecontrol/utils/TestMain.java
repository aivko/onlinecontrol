package com.vizaco.onlinecontrol.utils;

import com.vizaco.onlinecontrol.model.*;
import com.vizaco.onlinecontrol.representation.JournalView;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by super on 7/20/15.
 */
public class TestMain {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        TimeZone timeZone = TimeZone.getTimeZone("Europe/Kiev");
        Locale locale = new Locale("ru", "Ru");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("F/dd.MM.yyyy/EEEE", locale);
        simpleDateFormat.setTimeZone(timeZone);
        Calendar instance = Calendar.getInstance(timeZone, locale);
        instance.set(2015, 06, 01);
        System.out.println(simpleDateFormat.format(instance.getTime()));

    }
}
