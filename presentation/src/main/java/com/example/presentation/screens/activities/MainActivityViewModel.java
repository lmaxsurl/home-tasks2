package com.example.presentation.screens.activities;

import android.databinding.BindingAdapter;
import android.view.View;

import com.example.app.App;
import com.example.presentation.base.BaseViewModel;

public class MainActivityViewModel extends BaseViewModel<MainActivityRouter> {

    @Override
    protected void runInject() {
        App.getAppComponent().runInject(this);
    }


    public void onHomeTask9Click(View view){
        router.showHomeTask9();
    }

    public void onHomeTask11Click(View view){
        router.showHomeTask11();
    }
}
