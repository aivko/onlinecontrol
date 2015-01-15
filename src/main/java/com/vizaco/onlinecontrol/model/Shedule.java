package com.vizaco.onlinecontrol.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 *  Simple business object representing a shedule.
 *
 */
@Entity
@Table(name = "shedule")
public class Shedule {

    @Id
    @Column(name = "shedule_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String sheduleId;

    @Column(name = "date")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date date;


}
