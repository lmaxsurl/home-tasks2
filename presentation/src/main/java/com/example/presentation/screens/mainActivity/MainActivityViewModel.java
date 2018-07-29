package com.example.presentation.screens.mainActivity;

import com.example.app.App;
import com.example.presentation.base.BaseViewModel;

public class MainActivityViewModel extends BaseViewModel<MainActivityRouter> {

    @Override
    protected void runInject() {
        App.getAppComponent().runInject(this);
    }


    public void onHomeTask9Click(){
        router.showHomeTask9();
    }

    public void onHomeTask11Click(){
        router.showHomeTask11();
    }
}
