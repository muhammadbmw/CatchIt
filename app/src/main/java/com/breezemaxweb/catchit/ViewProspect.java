package com.breezemaxweb.catchit;

/**
 * Created by BreezeMaxWeb on 2017-03-27.
 */

public class ViewProspect {
    String company, meet,status,date;
    public ViewProspect(String company,String meet, String status, String date){
        this.company=company;
        this.meet = meet;
        this.status = status;
        this.date =date;
    }

    public String getCompany() {
        return company;
    }

    public String getDate() {
        return date;
    }

    public String getMeet() {
        return meet;
    }

    public String getStatus() {
        return status;
    }
}
