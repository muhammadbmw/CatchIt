package com.breezemaxweb.catchit;

/**
 * Created by BreezeMaxWeb on 2017-05-09.
 */

public class ContactInfo {
    String cname;
    String fname;
    String lname;
    String tel;
    String mobile;
    String email;
    String position;
    String notes;
    public ContactInfo() {
        cname = "";
        fname = "";
        lname = "";
        tel = "";
        mobile = "";
        email = "";
        position = "";
        notes = "";
    }
    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

}
