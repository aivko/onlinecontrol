package com.vizaco.onlinecontrol.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
public class Grade extends BaseEntity implements Comparable<Grade>{

    @NotNull
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "shedule_id")
    private Shedule shedule;

    @NotNull
    @JsonIgnore
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="student_id")
    private Student student;

    @Column(name = "task")
    private String task;

    @Column(name = "mark")
    private Integer mark;

    public Shedule getShedule() {
        return shedule;
    }

    public void setShedule(Shedule shedule) {
        this.shedule = shedule;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Grade grade = (Grade) o;

        if (shedule != null ? !shedule.equals(grade.shedule) : grade.shedule != null) return false;
        if (student != null ? !student.equals(grade.student) : grade.student != null) return false;
        if (task != null ? !task.equals(grade.task) : grade.task != null) return false;
        return !(mark != null ? !mark.equals(grade.mark) : grade.mark != null);

    }

    @Override
    public int hashCode() {
        int result = shedule != null ? shedule.hashCode() : 0;
        result = 31 * result + (student != null ? student.hashCode() : 0);
        result = 31 * result + (task != null ? task.hashCode() : 0);
        result = 31 * result + (mark != null ? mark.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(Grade grade) {
        int result = 0;

        if (this == grade) return 0;
        if (grade == null) return 1;

        if (mark != null && grade.mark != null) {
            result = mark.compareTo(grade.mark);
        }else if(mark != null && grade.mark == null){
            result = 1;
        }else if(mark == null && grade.mark != null){
            result = -1;
        }
        if (result != 0) return result;

        if (task != null && grade.task != null) {
            result = task.compareTo(grade.task);
        }else if(task != null && grade.task == null){
            result = 1;
        }else if(task == null && grade.task != null){
            result = -1;
        }

        return result;

    }


}
