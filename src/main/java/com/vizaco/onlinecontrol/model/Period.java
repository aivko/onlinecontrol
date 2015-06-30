package com.vizaco.onlinecontrol.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

/**
 *  Simple business object representing a shedule.
 *
 */
@Entity
@Table(name = "periods")
public class Period extends BaseEntity{

    @Transient
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");

    @Column(name = "start_time")
    @DateTimeFormat(pattern = "HH:mm")
    private Time startTime;

    @Column(name = "end_time")
    @DateTimeFormat(pattern = "HH:mm")
    private Time endTime;

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("");
        if (startTime != null){
            sb.append(simpleDateFormat.format(startTime));
        }
        sb.append(" - ");
        if (endTime != null){
            sb.append(simpleDateFormat.format(endTime));
        }
        return sb.toString();
    }
}
