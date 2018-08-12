package com.example.fyp;

public class Image implements java.io.Serializable {
    private long id;
    private String type;
    private String image;

    public Image(String type, String image) {
        this.id = 0;
        this.type = type;
        this.image = image;
    }

    public Image(long id, String type, String image) {
        this.id = id;
        this.type = type;
        this.image = image;
    }

    public long getId() {return id;}
    public String getType() {return type;}
    public String getImage() {return image;}


    public void setId(long id) {this.id = id;}
    public void setType(String type) {this.type = type;}
    public void setImage(String image) {this.image = image;}
}
