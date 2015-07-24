package com.vizaco.onlinecontrol.utils;

import com.vizaco.onlinecontrol.model.*;
import com.vizaco.onlinecontrol.representation.JournalView;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by super on 7/20/15.
 */
public class TestMain {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {

        A a = new A();
        a.item1 = 10;
        a.item2 = 100;

        A returnVal = mathodA(a);

        System.out.println(a == returnVal);

    }

    public static A mathodA(A b) {
        b.item1 = 1;
        return b;
    }

}

class A {
    int item1;
    Integer item2;
}
