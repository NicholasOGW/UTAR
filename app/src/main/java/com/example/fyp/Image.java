package com.example.fyp;

import java.sql.Blob;

public class Image implements java.io.Serializable {
    private long id;
    private String type;
    private byte[] image;

    public Image(String type, byte[] image) {
        this.id = 0;
        this.type = type;
        this.image = image;
    }

    public Image(long id, String type, byte[] image) {
        this.id = id;
        this.type = type;
        this.image = image;
    }

    public long getId() {return id;}
    public String getType() {return type;}
    public byte[] getImage() {return image;}


    public void setId(long id) {this.id = id;}
    public void setType(String type) {this.type = type;}
    public void setImage(byte[] image) {this.image = image;}
}
