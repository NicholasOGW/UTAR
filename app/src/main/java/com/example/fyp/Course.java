package com.example.fyp;

public class Course implements java.io.Serializable {
    private long id;
    private String title;
    private String mon;
    private String tue;
    private String wed;
    private String thu;
    private String fri;
    private String location;

    public Course(){

    }
    public Course(String title, String mon, String tue, String wed, String thu, String fri) {
        this.id = 0;
        this.title = title;
        this.mon = mon;
        this.tue = tue;
        this.wed = wed;
        this.thu = thu;
        this.fri = fri;
//        this.location = location;
    }

    public Course(long id, String title, String mon, String tue, String wed, String thu, String fri) {
        this.id = id;
        this.title = title;
        this.mon = mon;
        this.tue = tue;
        this.wed = wed;
        this.thu = thu;
        this.fri = fri;
//        this.location = location;
    }

    public long getId() {return id;}
    public String getTitle() {return title;}
    public String getMon() {return mon;}
    public String getTue() {return tue;}
    public String getWed() {return wed;}
    public String getThu() {return thu;}
    public String getFri() {return fri;}
//    public String getLocation() {return location;}


    public void setId(long id) {this.id = id;}
    public void setTitle(String title) {this.title = title;}
    public void setMon(String mon) {this.mon = mon;}
    public void setTue(String tue) {this.tue = tue;}
    public void setWed(String wed) {this.wed = wed;}
    public void setThu(String thu) {this.thu = thu;}
    public void setFri(String fri) {this.fri = fri;}
    //    public void setLocation(String location) {this.location = location;}
}
