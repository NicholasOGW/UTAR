package com.example.fyp;

public class Expenditure implements java.io.Serializable {
    private long id;
    private String type;
    private String amount;
    private String title;
    private String date;

    public Expenditure() {}

    public Expenditure(String type, String amount, String title, String date) {
        this.id = 0;
        this.type = type;
        this.amount = amount;
        this.title = title;
        this.date = date;
    }

    public Expenditure(long id, String type, String amount, String title, String date) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.title = title;
        this.date = date;
    }

    public long getId() {return id;}
    public String getType() {return type;}
    public String getAmount() {return amount;}
    public String getTitle() {return title;}
    public String getDate() {return date;}

    public void setId(long id) {this.id = id;}
    public void setType(String type) {this.type = type;}
    public void setAmount(String amount) {this.amount = amount;}
    public void setTitle(String title) {this.title = title;}
    public void setDate(String date) {this.date = date;}
}
