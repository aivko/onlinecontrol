package com.vizaco.onlinecontrol.model;

import javax.persistence.*;

/**
 *  Simple business object representing a gradles.
 *
 */
@Entity
@Table(name = "grades")
public class Grade {

    @Id
    @Column(name = "grade_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String gradeId;


}
