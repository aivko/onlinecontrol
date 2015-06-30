package com.vizaco.onlinecontrol.utils;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by super on 6/30/15.
 */
public class DateTest {

    public static void main(String[] args) {

        Locale local = new Locale("ru","RU");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE, dd MMMM yyyy HH:mm:ss", local);

        Calendar instance = Calendar.getInstance(local);
        instance.setFirstDayOfWeek(Calendar.MONDAY);

        System.out.println(simpleDateFormat.format(instance.getTime()));
        System.out.println(instance.get(Calendar.DAY_OF_WEEK));

    }

}
