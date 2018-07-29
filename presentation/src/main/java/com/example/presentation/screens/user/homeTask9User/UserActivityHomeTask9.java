package com.example.presentation.screens.user.homeTask9User;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.android.hometasks2.R;
import com.example.android.hometasks2.databinding.ActivityUserListBinding;
import com.example.presentation.base.BaseMvvmActivity;

public class UserActivityHomeTask9 extends BaseMvvmActivity<UserHomeTask9ViewModel,
        ActivityUserListBinding,
        UserActivityHomeTask9Router> {


    public static Intent getIntent(Activity activity){
        return new Intent(activity, UserActivityHomeTask9.class);
    }

    @Override
    protected UserHomeTask9ViewModel provideViewModel() {
        return ViewModelProviders.of(this).get(UserHomeTask9ViewModel.class);
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_user;
    }

    @Override
    protected UserActivityHomeTask9Router provideRouter() {
        return new UserActivityHomeTask9Router(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
