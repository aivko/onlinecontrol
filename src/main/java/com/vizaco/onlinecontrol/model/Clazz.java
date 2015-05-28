package com.vizaco.onlinecontrol.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Set;

/**
 *  Simple business object representing a class.
 *
 */
@Entity
@Table(name = "clazzes")
public class Clazz extends BaseEntity{

    @NotEmpty
    @Column(name = "name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clazz", fetch = FetchType.EAGER)
    private Set<Student> students;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clazz", fetch = FetchType.EAGER)
    private Set<Shedule> shedule;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="school_id")
    private School school;

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

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public Set<Shedule> getShedule() {
        return shedule;
    }

    public void setShedule(Set<Shedule> shedule) {
        this.shedule = shedule;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Clazz{");
        sb.append("name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
