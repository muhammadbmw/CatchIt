package com.breezemaxweb.catchit;

/**
 * Created by BreezeMaxWeb on 2017-03-29.
 */

public class Schedules {
    String company,meeting,time;
    int day,month,year;
    public Schedules(int day, int month, int year, String company,String meeting,String time){
        this.day = day;
        this.month = month;
        this.year = year;
        this.company = company;
        this.meeting = meeting;
        this.time = time;

    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public String getCompany(){return company;}
    public String getMeeting(){return meeting;}
    public String getTime(){return time;}
}
