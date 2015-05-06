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
 *  Simple business object representing a schoolboy.
 *
 */
@Entity
@Table(name = "students")
public class Student {

    @Id
    @Column(name = "student_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;

    @NotEmpty
    @Column(name = "first_name")
    private String firstName;

    @NotEmpty
    @Column(name = "last_name")
    private String lastName;

    @NotEmpty
    @Column(name = "middle_name")
    private String middleName;

    @NotNull
    @Column(name = "date_of_birth")
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private Date dateOfBirth;

    @NotNull
    @Column(name = "gender")
    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_students", joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users;

    @ManyToOne
    @JoinColumn(name = "clazz_id")
    private Clazz clazz;

    public Student(String firstName, String lastName, String middleName, Date dateOfBirth, Gender gender, List<User> users, Clazz clazz) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.users = users;
        this.clazz = clazz;
    }

    public Student() {
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
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

    public boolean isNew() {
        return (this.studentId == null);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append(lastName).append(' ');
        sb.append(firstName).append(' ');
        sb.append(middleName);
        return sb.toString();
    }
}

