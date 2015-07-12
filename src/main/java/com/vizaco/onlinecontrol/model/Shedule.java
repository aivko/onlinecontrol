package com.vizaco.onlinecontrol.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *  Simple business object representing a shedule.
 *
 */
@Entity
@Table(name = "shedule")
public class Shedule extends BaseEntity implements Comparable<Shedule>{

    @NotNull
    @Column(name = "date")
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private Date date;

    @NotNull
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="period_id")
    private Period period;

    @NotNull
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="subject_id")
    private Subject subject;

    @NotNull
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="clazz_id")
    private Clazz clazz;

    @NotNull
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="teacher_id")
    private Teacher teacher;

    @Column(name = "job")
    private String job;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Clazz getClazz() {
        return clazz;
    }

    public void setClazz(Clazz clazz) {
        this.clazz = clazz;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Shedule shedule = (Shedule) o;

        if (date != null ? !date.equals(shedule.date) : shedule.date != null) return false;
        if (period != null ? !period.equals(shedule.period) : shedule.period != null) return false;
        if (subject != null ? !subject.equals(shedule.subject) : shedule.subject != null) return false;
        if (clazz != null ? !clazz.equals(shedule.clazz) : shedule.clazz != null) return false;
        return !(teacher != null ? !teacher.equals(shedule.teacher) : shedule.teacher != null);

    }

    @Override
    public int hashCode() {
        int result = date != null ? date.hashCode() : 0;
        result = 31 * result + (period != null ? period.hashCode() : 0);
        result = 31 * result + (subject != null ? subject.hashCode() : 0);
        result = 31 * result + (clazz != null ? clazz.hashCode() : 0);
        result = 31 * result + (teacher != null ? teacher.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(Shedule shedule) {

        int result = 0;

        if (this == shedule) return 0;
        if (shedule == null) return 1;

        if (date != null) {
            result = date.compareTo(shedule.date);
        }else{
            result = shedule.date.compareTo(date);
        }

        if (result != 0) return result;

        if (period != null) {
            result = period.compareTo(shedule.period);
        }else{
            result = shedule.period.compareTo(period);
        }

        return result;
    }
}
