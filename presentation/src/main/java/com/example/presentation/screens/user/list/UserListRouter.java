package com.example.presentation.screens.user.list;

import com.example.presentation.base.BaseRouter;
import com.example.presentation.screens.user.view.AddUserActivity;
import com.example.presentation.screens.user.view.UserInfoActivity;

public class UserListRouter extends BaseRouter<UserListActivity> {

    public UserListRouter(UserListActivity activity) {
        super(activity);
    }

    public void goToUserDetails(String id) {
        activity.startActivityForResult(UserInfoActivity.getIntent(activity, id),
                UserListActivity.REQUEST_INFO_TYPE);
    }

    public void goToAddUser(){
        activity.startActivityForResult(AddUserActivity.getIntent(activity),
                UserListActivity.REQUEST_ADD_TYPE);
    }
}
