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

    @NotNull
    @Column(name = "date_of_birth")
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private Date dateOfBirth;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_students", joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "clazz_id")
    private Clazz clazz;

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Clazz getClazz() {
        return clazz;
    }

    public void setClazz(Clazz clazz) {
        this.clazz = clazz;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        return (id != null ? !id.equals(student.id) : student.id != null);

    }

}

