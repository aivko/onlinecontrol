package com.vizaco.onlinecontrol.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 *  Simple business object representing a gradles.
 *
 */
@Entity
@Table(name = "grades")
public class Grade {

    @Id
    @Column(name = "grade_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gradeId;

    @Column(name = "date")
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private Date date;

    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="subject_id")
    private Subject subject;

    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="student_id")
    private Student student;

    @Column(name = "mark")
    private Integer mark;

    public Grade() {
    }

    public Grade(Date date, Subject subject, Student student, Integer mark) {
        this.date = date;
        this.subject = subject;
        this.student = student;
        this.mark = mark;
    }

    public Long getGradeId() {
        return gradeId;
    }

    public void setGradeId(Long gradeId) {
        this.gradeId = gradeId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
