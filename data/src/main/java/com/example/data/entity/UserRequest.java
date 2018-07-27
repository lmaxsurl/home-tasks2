package com.example.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class UserRequest implements DataModel {

    @SerializedName("objectId")
    private String objectId;

    @SerializedName("firstname")
    private String firstname;

    @SerializedName("surname")
    private String surname;

    @SerializedName("age")
    private int age;

    @SerializedName("image_url")
    private String imageUrl;

    @SerializedName("gender")
    private String gender;

    @SerializedName("email")
    private String email;

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getObjectId() {
        return objectId;
    }
}
