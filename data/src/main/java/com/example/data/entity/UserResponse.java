package com.example.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class UserResponse implements  DataModel {

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

    public String getObjectId() {
        return objectId;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }
}
