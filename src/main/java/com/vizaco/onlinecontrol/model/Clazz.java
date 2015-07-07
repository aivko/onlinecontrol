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
    @Column(name = "number")
    private String number;

    @NotEmpty
    @Column(name = "letter")
    private String letter;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clazz", fetch = FetchType.EAGER)
    private Set<Student> students;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clazz", fetch = FetchType.EAGER)
    private Set<Shedule> shedule;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="school_id")
    private School school;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Clazz clazz = (Clazz) o;

        if (number != null ? !number.equals(clazz.number) : clazz.number != null) return false;
        if (letter != null ? !letter.equals(clazz.letter) : clazz.letter != null) return false;
        return !(school != null ? !school.equals(clazz.school) : clazz.school != null);

    }

    @Override
    public int hashCode() {
        int result = number != null ? number.hashCode() : 0;
        result = 31 * result + (letter != null ? letter.hashCode() : 0);
        result = 31 * result + (school != null ? school.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("");
        sb.append(number).append(" - ").append(letter);
        return sb.toString();
    }
}
