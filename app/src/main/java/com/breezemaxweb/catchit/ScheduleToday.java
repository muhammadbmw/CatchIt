package com.breezemaxweb.catchit;

/**
 * Created by BreezeMaxWeb on 2017-05-05.
 */

public class ScheduleToday {
    public String getCompanyName() {
        return companyName;
    }

    public String getMeetPersonName() {
        return meetPersonName;
    }

    public String getmName() {
        return mName;
    }

    public String getmType() {
        return mType;
    }

    public String getTime() {
        return time;
    }

    String companyName, meetPersonName,mName,mType, time;
    public ScheduleToday(String cn,String mpn, String mn,String mt,String t){
        companyName = cn;
        meetPersonName = mpn;
        mName = mn;
        mType = mt;
        time = t;
    }
}
