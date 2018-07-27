package com.example.presentation.screens.user.view;

import com.example.presentation.base.BaseRouter;

import static com.example.presentation.screens.user.view.UserInfoActivity.EXTRA_OBJECT_ID;

public class UserInfoRouter extends BaseRouter<UserInfoActivity> {

    public UserInfoRouter(UserInfoActivity activity) {
        super(activity);
    }

    public String getUserId(){
        return activity.getIntent().getStringExtra(EXTRA_OBJECT_ID);
    }

}
