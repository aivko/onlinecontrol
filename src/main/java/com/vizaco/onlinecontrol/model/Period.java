package com.vizaco.onlinecontrol.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.sql.Time;
import java.text.SimpleDateFormat;

/**
 *  Simple business object representing a shedule.
 *
 */
@Entity
@Table(name = "periods")
public class Period extends BaseEntity implements Comparable<Period>{

    @Transient
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    @Column(name = "start_time")
    @DateTimeFormat(pattern = "HH:mm")
    private Time startTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Period period = (Period) o;

        if (startTime != null ? !startTime.equals(period.startTime) : period.startTime != null) return false;
        return !(endTime != null ? !endTime.equals(period.endTime) : period.endTime != null);

    }

    @Override
    public int hashCode() {
        int result = startTime != null ? startTime.hashCode() : 0;
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        return result;
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

    @Override
    public int compareTo(Period period) {

        int result = 0;

        if (this == period) return 0;
        if (period == null) return 1;

        if (startTime != null) {
            result = startTime.compareTo(period.startTime);
        }else{
            result = period.startTime.compareTo(startTime);
        }

        if (result != 0) return result;

        if (endTime != null) {
            result = endTime.compareTo(period.endTime);
        }else{
            result = period.endTime.compareTo(endTime);
        }

        return result;
    }


}
