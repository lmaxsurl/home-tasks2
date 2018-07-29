package com.example.presentation.screens.user.view;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.android.hometasks2.R;
import com.example.android.hometasks2.databinding.ActivityUserInfoBinding;
import com.example.presentation.base.BaseMvvmActivity;
import com.example.presentation.utils.Extras;

public class UserInfoActivity extends BaseMvvmActivity<
        UserInfoViewModel,
        ActivityUserInfoBinding,
        UserInfoRouter> {


    public static Intent getIntent(Activity activity, String id){
        Intent intent = new Intent(activity, UserInfoActivity.class);
        intent.putExtra(Extras.EXTRA_OBJECT_ID, id);
        return intent;
    }

    @Override
    protected UserInfoViewModel provideViewModel() {
        return ViewModelProviders.of(this).get(UserInfoViewModel.class);
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_user_info;
    }

    @Override
    protected UserInfoRouter provideRouter() {
        return new UserInfoRouter(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getIntent().getExtras() != null){
            viewModel.loadUser(getIntent().getStringExtra(Extras.EXTRA_OBJECT_ID));
        } else
            finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        router.sendCancelled();
    }
}
