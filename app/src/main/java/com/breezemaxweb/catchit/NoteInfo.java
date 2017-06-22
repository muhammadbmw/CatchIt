package com.breezemaxweb.catchit;

/**
 * Created by BreezeMaxWeb on 2017-05-08.
 */

public class NoteInfo {
    int id;
    String subject, note, date, day;


    public NoteInfo(int id, String subject, String note,String date, String day){
        this.id = id;
        this.subject = subject;
        this.note = note;
        this.date = date;
        this.day = day;

    }

    public int getId() {
        return id;
    }

    public String getSubject() {
        return subject;
    }

    public String getNote() {
        return note;
    }

    public String getDate() {
        return date;
    }

    public String getDay() {
        return day;
    }
}
