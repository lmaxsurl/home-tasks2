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

public class UserInfoActivity extends BaseMvvmActivity<
        UserInfoViewModel,
        ActivityUserInfoBinding,
        UserInfoRouter> {

    public static final String EXTRA_OBJECT_ID = "EXTRA_OBJECT_ID";

    public static Intent getIntent(Activity activity, String id){
        Intent intent = new Intent(activity, UserInfoActivity.class);
        intent.putExtra(EXTRA_OBJECT_ID, id);
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
            new Thread(new Runnable() {
                @Override
                public void run() {
                    viewModel.loadUser(getIntent().getStringExtra(EXTRA_OBJECT_ID));
                    while(viewModel.isDownloading);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            binding.progressBar.setVisibility(View.GONE);
                            binding.userContainer.setVisibility(View.VISIBLE);
                        }
                    });
                }
            }).start();
        } else
            finish();
    }
}