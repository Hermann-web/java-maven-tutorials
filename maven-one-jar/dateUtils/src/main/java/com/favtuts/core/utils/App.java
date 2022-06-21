package com.favtuts.core.utils;

import org.joda.time.LocalDate;

public class App {

    public static void main(String[] args) {

        System.out.println(getLocalCurrentDate());
    }

    //Print current date with JodaTime
    private static String getLocalCurrentDate() {
        
        LocalDate date = new LocalDate();
        return date.toString();
        
    }

}