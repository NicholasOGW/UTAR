package com.example.fyp;

public class Course implements java.io.Serializable {
    private long id;
    private String title;
    private String mon;
    private String tues;
    private String wed;
    private String thur;
    private String fri;
    private String location;

    public Course(){

    }
    public Course(String title, String mon, String tues, String wed, String thur, String fri) {
        this.id = 0;
        this.title = title;
        this.mon = mon;
        this.tues = tues;
        this.wed = wed;
        this.thur = thur;
        this.fri = fri;
//        this.location = location;
    }

    public Course(long id, String title, String mon, String tues, String wed, String thur, String fri) {
        this.id = id;
        this.title = title;
        this.mon = mon;
        this.tues = tues;
        this.wed = wed;
        this.thur = thur;
        this.fri = fri;
//        this.location = location;
    }

    public long getId() {return id;}
    public String getTitle() {return title;}
    public String getMon() {return mon;}
    public String getTues() {return tues;}
    public String getWed() {return wed;}
    public String getThur() {return thur;}
    public String getFri() {return fri;}
//    public String getLocation() {return location;}


    public void setId(long id) {this.id = id;}
    public void setTitle(String title) {this.title = title;}
    public void setMon(String mon) {this.mon = mon;}
    public void setTues(String tues) {this.tues = tues;}
    public void setWed(String wed) {this.wed = wed;}
    public void setThur(String thur) {this.thur = thur;}
    public void setFri(String fri) {this.fri = fri;}
    //    public void setLocation(String location) {this.location = location;}
}
