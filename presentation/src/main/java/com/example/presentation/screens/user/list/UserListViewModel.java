package com.example.presentation.screens.user.list;

import com.example.android.hometasks2.R;
import com.example.app.App;
import com.example.domain.entity.User;
import com.example.domain.usecases.GetListUserUseCase;
import com.example.presentation.base.BaseViewModel;
import com.example.presentation.recycler.ClickedItemModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class UserListViewModel extends BaseViewModel<UserListRouter> {

    public UserListAdapter adapter = new UserListAdapter();

    @Inject
    public GetListUserUseCase listUserUseCase;

    public UserListViewModel() {
        showProgressBar();
        getData();
        adapter
                .observeItemClick()
                .subscribe(new Observer<ClickedItemModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        getCompositeDisposable().add(d);
                    }

                    @Override
                    public void onNext(ClickedItemModel clickedItemModel) {
                        if(clickedItemModel.getEntity() instanceof User){
                            router.goToUserDetails(((User)clickedItemModel.getEntity()).getObjectId());
                        } else {
                            router.showToast(R.string.conversion_error);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        router.showError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    protected void runInject() {
        App.getAppComponent().runInject(this);
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
                        adapter.setItems(users);
                        dismissProgressBar();
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

    public void onFabClick() {
        router.goToAddUser();
    }

    public void onChangeText(String text) {

    }


}


