package com.example.presentation.screens.user.list;

import android.util.Log;

import com.example.app.App;
import com.example.domain.entity.User;
import com.example.domain.usecases.GetListUserUseCase;
import com.example.presentation.base.BaseViewModel;
import com.example.presentation.screens.recycleView.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class UserListViewModel extends BaseViewModel<UserListRouter> {

    private static final String TAG = "AAA UserListViewModel";

    private ArrayList<User> userList;
    public boolean isDownloading = true;
    private RecyclerViewAdapter adapter;

    @Inject
    public GetListUserUseCase listUserUseCase;

    public UserListViewModel() {
        getData();
    }

    @Override
    protected void runInject() {
        App.getAppComponent().runInject(this);
        Log.d(TAG, "runInject: ");
    }

    private void getData() {
        listUserUseCase
                .getUsers()
                .subscribe(new Observer<List<User>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        getCompositeDisposable().add(d);
                    }

                    @Override
                    public void onNext(List<User> users) {
                        userList = new ArrayList<>(users);
                        isDownloading = false;
                    }

                    @Override
                    public void onError(Throwable e) {
                        router.showError(e);
                        router.finishActivity();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public ArrayList<User> getUserList() {
        return userList;
    }

    public RecyclerViewAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(RecyclerViewAdapter adapter) {
        this.adapter = adapter;
    }

    public void onFabClick() {
        router.goToAddUser();
    }

    public void onChangeText(String text) {
        ArrayList<User> list = new ArrayList<>();
        if (text.equals("")) {
            adapter.setData(userList);
            return;
        }
        for (User user : userList)
            if (user.getFirstname().toLowerCase().contains(text) ||
                    user.getSurname().toLowerCase().contains(text))
                list.add(user);

        Collections.sort(list);
        adapter.setData(list);
    }
}


