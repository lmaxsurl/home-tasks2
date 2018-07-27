package com.example.presentation.screens.user.list;

import android.app.Activity;
import android.app.ListFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.example.presentation.base.BaseActivity;
import com.example.presentation.base.BaseRouter;
import com.example.presentation.screens.user.edit.AddUserActivity;
import com.example.presentation.screens.user.view.UserInfoActivity;

public class UserListRouter extends BaseRouter<UserListActivity> {

    public UserListRouter(UserListActivity activity) {
        super(activity);
    }

    public void goToUserDetails(String id) {
        activity.startActivity(UserInfoActivity.getIntent(activity, id));
    }

    public void goToAddUser(){
        activity.startActivity(AddUserActivity.getIntent(activity));
    }

}
