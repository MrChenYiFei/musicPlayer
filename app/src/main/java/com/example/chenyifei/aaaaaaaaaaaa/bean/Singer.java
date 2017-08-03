package com.example.chenyifei.aaaaaaaaaaaa.bean;

/**
 * Created by chenyifei on 2017/5/9.
 */
public class Singer {
   private int image;
   private String name;

    public Singer(int image, String name) {
        this.image = image;
        this.name = name;
    }
    public Singer( String name) {

        this.name = name;
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

    @Override
    public String toString() {
        return "Singer{" +
                "image=" + image +
                ", name='" + name + '\'' +
                '}';
    }
}
