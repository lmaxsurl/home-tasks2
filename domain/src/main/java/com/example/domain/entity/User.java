package com.example.domain.entity;

import android.util.Log;

public class User implements DomainModel {

    private String firstname;
    private String surname;
    private String gender;
    private String imageUrl;
    private String email;
    private int age;
    private String objectId;

    public User(String firstname, String surname, String gender,
                String imageUrl, String email, int age, String objectId) {
        this.firstname = firstname;
        this.surname = surname;
        this.gender = gender;
        this.imageUrl = imageUrl;
        this.email = email;
        this.age = age;
        this.objectId = objectId;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getSurname() {
        return surname;
    }

    public String getGender() {
        return gender;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getObjectId() {
        return objectId;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof User && this.objectId.equals(((User) obj).getObjectId());
    }
}
