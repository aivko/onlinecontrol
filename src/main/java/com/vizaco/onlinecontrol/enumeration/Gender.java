package com.vizaco.onlinecontrol.enumeration;

/**
 *  Simple business object representing a gender.
 *
 */
public enum Gender {
    MALE("Мужчина"),
    FEMALE("Женщина");

    private final String mf;

    Gender(String mf) {
        this.mf = mf;
    }
}