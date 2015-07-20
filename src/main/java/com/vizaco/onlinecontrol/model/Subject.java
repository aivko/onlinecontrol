package com.vizaco.onlinecontrol.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *  Simple business object representing a attendance.
 *
 */
@Entity
@Table(name = "subjects")
public class Subject extends BaseEntity implements Comparable<Subject>{

    @Column(name = "name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Subject subject = (Subject) o;

        return !(name != null ? !name.equals(subject.name) : subject.name != null);

    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int compareTo(Subject subject) {
        int result = 0;

        if (this == subject) return 0;
        if (subject == null) return 1;

        if (name != null && subject.name != null) {
            result = name.compareTo(subject.name);
        }else if(name != null && subject.name == null){
            result = 1;
        }else if(name == null && subject.name != null){
            result = -1;
        }

        return result;

    }
}
