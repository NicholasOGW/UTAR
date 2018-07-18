package com.example.fyp;

public class Task implements java.io.Serializable {
    private long id;
    private String type;
    private String title;
    private String date;
    private String time;

    public Task() {}

    public Task(String type, String title, String date, String time) {
        this.id = 0;
        this.type = type;
        this.title = title;
        this.date = date;
        this.time = time;
    }

    public Task(long id, String type, String title, String date, String time) {
        this.id = id;
        this.type = type;
        this.title = title;
        this.date = date;
        this.time = time;
    }

    public long getId() {return id;}
    public String getType() {return type;}
    public String getTitle() {return title;}
    public String getDate() {return date;}
    public String getTime() {return time;}

    public void setId(long id) {this.id = id;}
    public void setType(String type) {this.type = type;}
    public void setTitle(String title) {this.title = title;}
    public void setDate(String date) {this.date = date;}
    public void setTime(String time) {this.time = time;}
}
