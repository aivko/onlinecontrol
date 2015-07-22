package com.vizaco.onlinecontrol.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

/**
 * Simple business object representing a schoolboy.
 */
@Entity
@Table(name = "students")
public class Student extends Person {

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "parents_students", joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "parent_id"))
    private Set<Parent> parents;

    @NotNull
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "clazz_id")
    private Clazz clazz;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "student", fetch = FetchType.EAGER)
    private Set<Grade> grades;

    public Set<Parent> getParents() {
        return parents;
    }

    public void setParents(Set<Parent> parents) {
        this.parents = parents;
    }

    public Clazz getClazz() {
        return clazz;
    }

    public void setClazz(Clazz clazz) {
        this.clazz = clazz;
    }

    public Set<Grade> getGrades() {
        return grades;
    }

    public void setGrades(Set<Grade> grades) {
        this.grades = grades;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("");
        sb.append(lastName).append(" ").append(firstName).append(" ").append(middleName);
        return sb.toString();
    }
}

