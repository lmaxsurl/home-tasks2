package com.example.presentation.screens.user.edit;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;

import com.example.android.hometasks2.R;
import com.example.android.hometasks2.databinding.ActivityAddUserBinding;
import com.example.presentation.base.BaseMvvmActivity;
import com.example.presentation.screens.user.view.UserInfoActivity;

public class AddUserActivity extends BaseMvvmActivity<AddUserViewModel,ActivityAddUserBinding,AddUserRouter> {

    public static Intent getIntent(Activity activity){
        return new Intent(activity, AddUserActivity.class);
    }

    @Override
    protected AddUserViewModel provideViewModel() {
        return ViewModelProviders.of(this).get(AddUserViewModel.class);
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_add_user;
    }

    @Override
    protected AddUserRouter provideRouter() {
        return new AddUserRouter(this);
    }
}
