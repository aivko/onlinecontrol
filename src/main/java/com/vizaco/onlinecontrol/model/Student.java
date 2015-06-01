package com.vizaco.onlinecontrol.model;

import com.vizaco.onlinecontrol.enumeration.Gender;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Simple business object representing a schoolboy.
 */
@Entity
@Table(name = "students")
public class Student extends Person {

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "parents_students", joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "parent_id"))
    private List<Parent> parents;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "clazz_id")
    private Clazz clazz;

    public List<Parent> getUsers() {
        return parents;
    }

    public void setUsers(List<Parent> parents) {
        this.parents = parents;
    }

    public Clazz getClazz() {
        return clazz;
    }

    public void setClazz(Clazz clazz) {
        this.clazz = clazz;
    }

}

