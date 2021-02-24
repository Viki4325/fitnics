package com.group12.fitnics.objects;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MyDate {
    private Calendar dateInfo;

    public MyDate(Calendar date) {
        this.dateInfo = date;
    }

    public boolean equals(MyDate other) {
        return (other.getYear() == dateInfo.get(Calendar.YEAR)) &&
               (other.getMonth() == dateInfo.get(Calendar.MONTH)) &&
               (other.getDate() == dateInfo.get(Calendar.DATE));
    }

    public void setDateInfo(Calendar newDateInfo) {
        this.dateInfo = newDateInfo;
    }

    public Calendar getDateInfo() {
        return dateInfo;
    }

    public int getYear() {
        return dateInfo.get(Calendar.YEAR);
    }

    public int getMonth() {
        return dateInfo.get(Calendar.MONTH);
    }

    public int getDate() {
        return dateInfo.get(Calendar.DATE);
    }

    @Override
    public String toString() {
        String datePattern = "yyyy-MM-dd";
        String str = new SimpleDateFormat(datePattern).format(dateInfo.getTime());
        return str;
    }

}
