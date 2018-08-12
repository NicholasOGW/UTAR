package com.example.fyp;

public class CourseInfo {
    private String name = null;
    private String[] times = null;
    private String loaction = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getTimes() {
        return times;
    }

    public void setCourseTime(String[] courseTimes) {
        this.times = courseTimes;
    }

    public void setCourseTime(String monday, String tuesday, String wednesday, String thursday, String friday, String saturday, String sunday) {
        this.times = new String[]{monday, tuesday, wednesday, thursday, friday, saturday, sunday};
    }

//    public String getLoaction() {
//        return loaction;
//    }
//
//    public void setLoaction(String loaction) {
//        this.loaction = loaction;
//    }
}