package com.example.presentation.screens.mainActivity;

import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.os.Bundle;

import com.example.android.hometasks2.R;
import com.example.android.hometasks2.databinding.ActivityMainBinding;
import com.example.presentation.base.BaseMvvmActivity;

public class MainActivity extends BaseMvvmActivity<MainActivityViewModel,
        ActivityMainBinding,
        MainActivityRouter>{

    @Override
    protected MainActivityViewModel provideViewModel() {
        return ViewModelProviders.of(this).get(MainActivityViewModel.class);
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected MainActivityRouter provideRouter() {
        return new MainActivityRouter(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


}
