package com.example.fyp;

public class Subject implements java.io.Serializable {
    private long id;
    private String title;

    public Subject(String title) {
        this.id = 0;
        this.title = title;
    }

    public Subject(long id, String title) {
        this.id = id;
        this.title = title;
    }

    public long getId() {return id;}
    public String getTitle() {return title;}

    public void setId(long id) {this.id = id;}
    public void setTitle(String title) {this.title = title;}

}
