package com.vizaco.onlinecontrol.model;

import javax.persistence.*;
import java.util.Set;

/**
 *  Simple business object representing a teacher.
 *
 */
@Entity
@Table(name = "techers")
public class Teacher {

    @Id
    @Column(name = "teacher_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String teacherId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "middle_name")
    private String middleName;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "teachers_subjects", joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id"))
    private Set<Subject> subjects;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "teachers_clazzes", joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "clazz_id"))
    private Set<Clazz> clazzes;


    public Teacher() {
    }

    public Teacher(String firstName, String lastName, String middleName, Set<Subject> subjects, Set<Clazz> clazzes) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.subjects = subjects;
        this.clazzes = clazzes;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }

    public Set<Clazz> getClazzes() {
        return clazzes;
    }

    public void setClazzes(Set<Clazz> clazzes) {
        this.clazzes = clazzes;
    }
}
