package com.example.myapplication.ObjectManager;

public class manHinhChinh {
    private int image;
    private String name;

    public manHinhChinh() {
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public manHinhChinh(int image, String name) {
        this.image = image;
        this.name = name;
    }
}
