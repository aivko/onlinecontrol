package com.vizaco.onlinecontrol.representation;

import com.vizaco.onlinecontrol.model.*;
import com.vizaco.onlinecontrol.utils.DateUtils;

import java.util.*;

/**
 *  Simple business object representing a gradles.
 *
 */
public class JournalView implements Comparable<JournalView>{

    private Integer sheduleId;

    private Date date;

    private Integer dayOfWeek;

    private Period period;

    private Subject subject;

    private Clazz clazz;

    private Teacher teacher;

    private Student student;

    private String job;

    private TreeSet<Grade> grades;

    private DateUtils dateUtils = new DateUtils();

    public JournalView() {
    }

    public JournalView(Integer sheduleId, Date date, Period period, Subject subject, Clazz clazz, Teacher teacher, String job, Student student, TreeSet<Grade> grades) {
        this.sheduleId = sheduleId;
        this.date = date;
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        this.dayOfWeek = dateUtils.getNumberDayOfWeek(instance);
        this.period = period;
        this.subject = subject;
        this.clazz = clazz;
        this.teacher = teacher;
        this.job = job;
        this.student = student;
        this.grades = grades;
    }

    public JournalView(Integer sheduleId, Date date, Period period, Subject subject, Clazz clazz, Teacher teacher, String job, Student student) {
        this.sheduleId = sheduleId;
        this.date = date;
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        this.dayOfWeek = dateUtils.getNumberDayOfWeek(instance);
        this.period = period;
        this.subject = subject;
        this.clazz = clazz;
        this.teacher = teacher;
        this.student = student;
        this.job = job;
    }

    public Integer getSheduleId() {
        return sheduleId;
    }

    public void setSheduleId(Integer sheduleId) {
        this.sheduleId = sheduleId;
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

    public TreeSet<Grade> getGrades() {
        return grades;
    }

    public void setGrades(TreeSet<Grade> grades) {
        this.grades = grades;
    }

    public Integer getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(Integer dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JournalView that = (JournalView) o;

        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (period != null ? !period.equals(that.period) : that.period != null) return false;
        if (subject != null ? !subject.equals(that.subject) : that.subject != null) return false;
        if (clazz != null ? !clazz.equals(that.clazz) : that.clazz != null) return false;
        if (teacher != null ? !teacher.equals(that.teacher) : that.teacher != null) return false;
        if (student != null ? !student.equals(that.student) : that.student != null) return false;
        return !(grades != null ? !grades.equals(that.grades) : that.grades != null);

    }

    @Override
    public int hashCode() {
        int result = date != null ? date.hashCode() : 0;
        result = 31 * result + (period != null ? period.hashCode() : 0);
        result = 31 * result + (subject != null ? subject.hashCode() : 0);
        result = 31 * result + (clazz != null ? clazz.hashCode() : 0);
        result = 31 * result + (teacher != null ? teacher.hashCode() : 0);
        result = 31 * result + (student != null ? student.hashCode() : 0);
        result = 31 * result + (grades != null ? grades.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(JournalView journalView) {
        int result = 0;

        if (this == journalView) return 0;
        if (journalView == null) return 1;

        if (date != null && journalView.date != null) {
            result = date.compareTo(journalView.date);
        }else if(date != null && journalView.date == null){
            result = 1;
        }else if(date == null && journalView.date != null){
            result = -1;
        }
        if (result != 0) return result;

        if (period != null && journalView.period != null) {
            result = period.compareTo(journalView.period);
        }else if(period != null && journalView.period == null){
            result = 1;
        }else if(period == null && journalView.period != null){
            result = -1;
        }
        if (result != 0) return result;

        if (subject != null && journalView.subject != null) {
            result = subject.compareTo(journalView.subject);
        }else if(subject != null && journalView.subject == null){
            result = 1;
        }else if(subject == null && journalView.subject != null){
            result = -1;
        }
        if (result != 0) return result;

        if (teacher != null && journalView.teacher != null) {
            result = teacher.compareTo(journalView.teacher);
        }else if(teacher != null && journalView.teacher == null){
            result = 1;
        }else if(teacher == null && journalView.teacher != null){
            result = -1;
        }
        if (result != 0) return result;

        if (student != null && journalView.student != null) {
            result = student.compareTo(journalView.student);
        }else if(student != null && journalView.student == null){
            result = 1;
        }else if(student == null && journalView.student != null){
            result = -1;
        }
        if (result != 0) return result;

        if (clazz != null && journalView.clazz != null) {
            result = clazz.compareTo(journalView.clazz);
        }else if(clazz != null && journalView.clazz == null){
            result = 1;
        }else if(clazz == null && journalView.clazz != null){
            result = -1;
        }
        if (result != 0) return result;

        if (grades != null && journalView.grades != null) {
            if (grades.size() > journalView.grades.size()){
                result = 1;
            }else if (grades.size() < journalView.grades.size()){
                result = -1;
            }else {
                Grade[] gradesLeft = (Grade[]) grades.toArray();
                Grade[] gradesRight = (Grade[]) journalView.grades.toArray();
                for (int i = 0; i < gradesLeft.length; i++) {
                    result = gradesLeft[i].compareTo(gradesRight[i]);
                    if (result != 0) break;
                }
            }
        }else if(grades != null && journalView.grades == null){
            result = 1;
        }else if(grades == null && journalView.grades != null){
            result = -1;
        }
        if (result != 0) return result;

        if (job != null && journalView.job != null) {
            result = job.compareTo(journalView.job);
        }else if(job != null && journalView.job == null){
            result = 1;
        }else if(job == null && journalView.job != null){
            result = -1;
        }

        return result;

    }
}
