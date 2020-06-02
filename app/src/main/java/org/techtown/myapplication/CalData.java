package org.techtown.myapplication;

public class CalData {
   // private int year;
   // private int month;
    private int day;
    private int dayofweek;

    public CalData(int d, int w){
      //  year = y;
       // month = m;
        day = d;
        dayofweek = w;
    }

    //public int getYear() {
    //    return year;
   // }
   // public int getMonth() {
   //     return month;
   // }
    public int getDay() {
        return day;
    }
    public int getDayofweek() {
        return dayofweek;
    }
}
