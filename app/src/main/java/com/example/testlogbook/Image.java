package com.example.testlogbook;

import androidx.annotation.NonNull;

public class Image {

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    protected int imageId;
    protected String imageURL;

    @NonNull
    @Override
    public String toString(){return imageId + "-" + imageURL;}
}
