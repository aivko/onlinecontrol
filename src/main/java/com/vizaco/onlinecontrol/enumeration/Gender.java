package com.vizaco.onlinecontrol.enumeration;

/**
 *  Simple business object representing a gender.
 *
 */
public enum Gender {
    MALE("Мужчина", 1),
    FEMALE("Женщина", 2);

    private final String mf;
    private final int number;

    Gender(String mf, int number) {
        this.mf = mf;
        this.number = number;
    }
}