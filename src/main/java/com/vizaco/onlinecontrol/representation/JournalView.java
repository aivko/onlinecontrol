package com.vizaco.onlinecontrol.representation;

import com.vizaco.onlinecontrol.model.*;

import java.util.Date;

/**
 *  Simple business object representing a gradles.
 *
 */
public class JournalView {

    private Date date;

    private Period period;

    private Subject subject;

    private Clazz clazz;

    private Teacher teacher;

    private String job;

    private Student student;

    private Grade grade;

    public JournalView() {
    }

    public JournalView(Date date, Period period, Subject subject, Clazz clazz, Teacher teacher, String job, Student student, Grade grade) {
        this.date = date;
        this.period = period;
        this.subject = subject;
        this.clazz = clazz;
        this.teacher = teacher;
        this.job = job;
        this.student = student;
        this.grade = grade;
    }

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

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }
}
