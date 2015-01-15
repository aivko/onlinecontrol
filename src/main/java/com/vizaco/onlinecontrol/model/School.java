package com.vizaco.onlinecontrol.model;

import javax.persistence.*;

/**
 *  Simple business object representing a school.
 *
 */
@Entity
@Table(name = "schools")
public class School {

    @Id
    @Column(name = "school_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String schoolId;

    @Column(name = "name")
    private String name;

    @Column(name = "director")
    private String director;

    @Column(name = "description")
    private String description;

    public School() {
    }

    public School(String name, String director, String description) {
        this.name = name;
        this.director = director;
        this.description = description;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
