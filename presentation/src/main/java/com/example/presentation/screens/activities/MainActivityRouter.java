package com.example.presentation.screens.activities;

import com.example.presentation.base.BaseRouter;
import com.example.presentation.screens.user.list.UserListActivity;
import com.example.presentation.screens.user.homeTask9User.UserActivityHomeTask9;

public class MainActivityRouter extends BaseRouter<MainActivity> {

    public MainActivityRouter(MainActivity activity) {
        super(activity);
    }

    public void showHomeTask9(){
        activity.startActivity(UserActivityHomeTask9.getIntent(activity));
    }

    public void showHomeTask11() {
        activity.startActivity(UserListActivity.getIntent(activity));
    }
}
