package com.vizaco.onlinecontrol.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 *  Simple business object representing a gradles.
 *
 */
@Entity
@Table(name = "grades")
public class Grade extends BaseEntity{

    @Column(name = "date")
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private Date date;

    @NotNull
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="period_id")
    private Period period;

    @NotNull
    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="subject_id")
    private Subject subject;

    @NotNull
    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="student_id")
    private Student student;

    @Column(name = "mark")
    private Integer mark;

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

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }
}
