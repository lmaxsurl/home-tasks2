package com.example.presentation.screens.user.list;

import com.example.presentation.base.BaseRouter;
import com.example.presentation.screens.user.view.AddUserActivity;
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
