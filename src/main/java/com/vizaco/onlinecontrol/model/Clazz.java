package com.vizaco.onlinecontrol.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 *  Simple business object representing a class.
 *
 */
@Entity
@Table(name = "clazzes")
public class Clazz {

    @Id
    @Column(name = "clazz_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String clazzId;

    @Column(name = "name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clazz", fetch = FetchType.EAGER)
    private Set<Student> students;

    public Clazz() {
    }

    public Clazz(String name, Set<Student> students) {
        this.name = name;
        this.students = students;
    }

    public String getClazzId() {
        return clazzId;
    }

    public void setClazzId(String clazzId) {
        this.clazzId = clazzId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }
}
