package com.example.presentation.screens.user.list;

import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.view.View;

import com.example.android.hometasks2.R;
import com.example.android.hometasks2.databinding.ActivityMainBinding;
import com.example.presentation.base.BaseMvvmActivity;

public class MainActivity extends BaseMvvmActivity<MainActivityViewModel, ActivityMainBinding> implements View.OnClickListener {


    @Override
    protected MainActivityViewModel provideViewModel() {
        return ViewModelProviders.of(this).get(MainActivityViewModel.class);
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        findViewById(R.id.ht9_button).setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        UserActivity.start(this);
    }
}
