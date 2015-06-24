package com.vizaco.onlinecontrol.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 *  Simple business object representing a shedule.
 *
 */
@Entity
@Table(name = "timetable")
public class TimeTable extends BaseEntity{

    @Column(name = "number_lesson")
    private byte numberLesson;

    @Column(name = "start_time")
    @DateTimeFormat(pattern = "HH:mm")
    private Date startTime;

    @Column(name = "end_time")
    @DateTimeFormat(pattern = "HH:mm")
    private Date endTime;

    public byte getNumberLesson() {
        return numberLesson;
    }

    public void setNumberLesson(byte numberLesson) {
        this.numberLesson = numberLesson;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
