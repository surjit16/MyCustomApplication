package com.example.surjit.mycustomapplication;

/**
 * Created by surjit on 29-01-2018.
 */

class Mobile {
    String mobileName;
    int imageID;

    public Mobile(String mobileName, int imageID) {
        this.mobileName = mobileName;
        this.imageID = imageID;
    }

    public String getMobileName() {
        return mobileName;
    }

    public void setMobileName(String mobileName) {
        this.mobileName = mobileName;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }
}
