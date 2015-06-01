package com.vizaco.onlinecontrol.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

/**
 * Simple business object representing a user.
 *
 */
@Entity
@Table(name = "parents")
public class Parent extends Person {

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "parents_students", joinColumns = @JoinColumn(name = "parent_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    private List<Student> students;

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

}
