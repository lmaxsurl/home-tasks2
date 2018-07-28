package com.example.presentation.screens.user.list;

import android.databinding.ObservableField;

import com.example.domain.entity.User;
import com.example.presentation.recycler.BaseItemViewModel;

public class UserItemViewModel extends BaseItemViewModel<User> {

    public ObservableField<String> firstname = new ObservableField<>("");
    public ObservableField<String> surname = new ObservableField<>("");
    public ObservableField<String> gender = new ObservableField<>("");
    public ObservableField<String> imageUrl = new ObservableField<>("");
    private String userId;

    @Override
    public void setItem(User user, int position) {
        this.firstname.set(user.getFirstname());
        this.surname.set(user.getSurname());
        this.gender.set(user.getGender());
        this.imageUrl.set(user.getImageUrl());
        this.userId = user.getObjectId();
    }

    public String getUserId() {
        return userId;
    }
}
