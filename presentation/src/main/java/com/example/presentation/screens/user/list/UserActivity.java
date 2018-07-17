package com.example.presentation.screens.user.list;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.android.hometasks2.R;
import com.example.android.hometasks2.databinding.ActivityUserBinding;
import com.example.presentation.base.BaseMvvmActivity;

public class UserActivity extends BaseMvvmActivity<UserViewModel, ActivityUserBinding> {

    public static void start(Activity activity){
        Intent intent = new Intent(activity, UserActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected UserViewModel provideViewModel() {
        return ViewModelProviders.of(this).get(UserViewModel.class);
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_user;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
