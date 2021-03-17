package com.group12.fitnics.objects;

import android.annotation.SuppressLint;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class MyDate implements Serializable {
    private Calendar dateInfo;

    public MyDate(Calendar date) {
        this.dateInfo = date;
    }

    public boolean equals(MyDate other) {
        return (other.getYear() == dateInfo.get(Calendar.YEAR)) &&
               (other.getMonth() == (dateInfo.get(Calendar.MONTH)) + 1) &&
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
        return dateInfo.get(Calendar.MONTH) + 1;
    }

    public int getDate() {
        return dateInfo.get(Calendar.DATE);
    }

    /*
    * This method returns the current date. To be used in logs classes
    * */
    public static MyDate getCurrentDate(){
        return new MyDate(new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DATE)));
    }

    @Override
    public String toString() {
        String datePattern = "yyyy-MM-dd";
        @SuppressLint("SimpleDateFormat") String str = new SimpleDateFormat(datePattern).format(dateInfo.getTime());
        return str;
    }

}
