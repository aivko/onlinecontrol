package com.vizaco.onlinecontrol.representation;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 *  Simple business object representing a gradles.
 *
 */
public class DelJournalView implements Comparable<DelJournalView>{

    private Integer sheduleId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private Date date;

    private Integer periodId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "hh:mm")
    @DateTimeFormat(pattern = "HH:mm")
    private Date periodStartTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "hh:mm")
    @DateTimeFormat(pattern = "HH:mm")
    private Date periodEndTime;

    private Integer studentId;

    protected String studentFirstName;

    protected String studentLastName;

    protected String studentMiddleName;

    private Integer subjectId;

    private String subjectName;

    private Integer clazzId;

    private String clazzNumber;

    private String clazzLetter;

    private Integer teacherId;

    protected String teacherFirstName;

    protected String teacherLastName;

    protected String teacherMiddleName;

    private String job;

    private Integer gradeId;

    private Integer gradeMark;

    private String gradeTask;

    public DelJournalView() {
    }

    public DelJournalView(Object[] object) {
        this.sheduleId          = (Integer) object[0];
        this.date               = (Date) object[1];
        this.periodId           = (Integer) object[2];
        this.periodStartTime    = (Date) object[3];
        this.periodEndTime      = (Date) object[4];
        this.studentId          = (Integer) object[5];
        this.studentFirstName   = (String) object[6];
        this.studentLastName    = (String) object[7];
        this.studentMiddleName  = (String) object[8];
        this.subjectId          = (Integer) object[9];
        this.subjectName        = (String) object[10];
        this.clazzId            = (Integer) object[11];
        this.clazzNumber        = (String) object[12];
        this.clazzLetter        = (String) object[13];
        this.teacherId          = (Integer) object[14];
        this.teacherFirstName   = (String) object[15];
        this.teacherLastName    = (String) object[16];
        this.teacherMiddleName  = (String) object[17];
        this.job                = (String) object[18];
        this.gradeId            = (Integer) object[19];
        this.gradeMark          = (Integer) object[20];
        this.gradeTask          = (String) object[21];
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

    public Integer getPeriodId() {
        return periodId;
    }

    public void setPeriodId(Integer periodId) {
        this.periodId = periodId;
    }

    public Date getPeriodStartTime() {
        return periodStartTime;
    }

    public void setPeriodStartTime(Date periodStartTime) {
        this.periodStartTime = periodStartTime;
    }

    public Date getPeriodEndTime() {
        return periodEndTime;
    }

    public void setPeriodEndTime(Date periodEndTime) {
        this.periodEndTime = periodEndTime;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getStudentFirstName() {
        return studentFirstName;
    }

    public void setStudentFirstName(String studentFirstName) {
        this.studentFirstName = studentFirstName;
    }

    public String getStudentLastName() {
        return studentLastName;
    }

    public void setStudentLastName(String studentLastName) {
        this.studentLastName = studentLastName;
    }

    public String getStudentMiddleName() {
        return studentMiddleName;
    }

    public void setStudentMiddleName(String studentMiddleName) {
        this.studentMiddleName = studentMiddleName;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Integer getClazzId() {
        return clazzId;
    }

    public void setClazzId(Integer clazzId) {
        this.clazzId = clazzId;
    }

    public String getClazzNumber() {
        return clazzNumber;
    }

    public void setClazzNumber(String clazzNumber) {
        this.clazzNumber = clazzNumber;
    }

    public String getClazzLetter() {
        return clazzLetter;
    }

    public void setClazzLetter(String clazzLetter) {
        this.clazzLetter = clazzLetter;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherFirstName() {
        return teacherFirstName;
    }

    public void setTeacherFirstName(String teacherFirstName) {
        this.teacherFirstName = teacherFirstName;
    }

    public String getTeacherLastName() {
        return teacherLastName;
    }

    public void setTeacherLastName(String teacherLastName) {
        this.teacherLastName = teacherLastName;
    }

    public String getTeacherMiddleName() {
        return teacherMiddleName;
    }

    public void setTeacherMiddleName(String teacherMiddleName) {
        this.teacherMiddleName = teacherMiddleName;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    public Integer getGradeMark() {
        return gradeMark;
    }

    public void setGradeMark(Integer gradeMark) {
        this.gradeMark = gradeMark;
    }

    public String getGradeTask() {
        return gradeTask;
    }

    public void setGradeTask(String gradeTask) {
        this.gradeTask = gradeTask;
    }

    @Override
    public int compareTo(DelJournalView o) {
        return 0;
    }
}
