package com.vizaco.onlinecontrol.model;

import javax.persistence.*;

/**
 *  Simple business object representing a attendance.
 *
 */
@Entity
@Table(name = "subjects")
public class Subject extends BaseEntity{

    @Column(name = "name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
