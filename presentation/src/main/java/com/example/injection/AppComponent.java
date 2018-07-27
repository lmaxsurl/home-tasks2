package com.example.injection;

import com.example.presentation.screens.activities.MainActivityViewModel;
import com.example.presentation.screens.user.edit.AddUserViewModel;
import com.example.presentation.screens.user.list.UserListViewModel;
import com.example.presentation.screens.user.view.UserInfoViewModel;
import com.example.presentation.screens.user.homeTask9User.UserHomeTask9ViewModel;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = AppModule.class)
@Singleton
public interface AppComponent {
    void runInject(UserHomeTask9ViewModel userHomeTask9ViewModel);
    void runInject(UserListViewModel userListViewModel);
    void runInject(MainActivityViewModel mainActivityViewModel);
    void runInject(UserInfoViewModel userInfoViewModel);
    void runInject(AddUserViewModel userAddViewModel);
}