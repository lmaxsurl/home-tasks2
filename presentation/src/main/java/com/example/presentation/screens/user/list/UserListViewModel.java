package com.example.presentation.screens.user.list;

import com.example.android.hometasks2.R;
import com.example.app.App;
import com.example.domain.entity.User;
import com.example.domain.usecases.DeleteUserUseCase;
import com.example.domain.usecases.GetListUserUseCase;
import com.example.domain.usecases.GetUserUseCase;
import com.example.domain.usecases.SearchUserUseCase;
import com.example.presentation.base.BaseViewModel;
import com.example.presentation.recycler.ClickedItemModel;
import com.example.presentation.utils.Extras;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.CompletableObserver;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class UserListViewModel extends BaseViewModel<UserListRouter> {

    public UserListAdapter adapter = new UserListAdapter();

    @Inject
    public GetListUserUseCase listUserUseCase;

    @Inject
    public GetUserUseCase getUserUseCase;

    @Inject
    public DeleteUserUseCase deleteUserUseCase;

    @Inject
    public SearchUserUseCase searchUserUseCase;

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
                        if (clickedItemModel.getEntity() instanceof User) {
                            router.goToUserDetails(((User) clickedItemModel.getEntity()).getObjectId());
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
        showProgressBar();
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

    public void onAddClick() {
        router.goToAddUser();
    }

    public void onChangeText(String text) {
        if(text.equals("")){
            getData();
        } else {
            showProgressBar();
            searchUserUseCase
                    .searchUsers(text)
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
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }
    }

    public void onAddUser(String userId) {
        changeAdapterList(userId, Extras.EXTRA_ADD_METHOD);
    }

    public void onEditUser(String userId) {
        changeAdapterList(userId, Extras.EXTRA_EDIT_METHOD);
    }

    public void onDeleteUser(String userId) {
        changeAdapterList(userId, Extras.EXTRA_DELETE_METHOD);
    }

    private void changeAdapterList(final String userId, final String requiredMethod) {
        showProgressBar();
        getUserUseCase
                .getUser(userId)
                .subscribe(new Observer<User>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        getCompositeDisposable().add(d);
                    }

                    @Override
                    public void onNext(User user) {
                        if (requiredMethod.equals(Extras.EXTRA_ADD_METHOD)) {
                            adapter.addItem(user);
                            dismissProgressBar();
                        } else if (requiredMethod.equals(Extras.EXTRA_EDIT_METHOD)) {
                            adapter.editItem(user);
                            dismissProgressBar();
                        } else if (requiredMethod.equals(Extras.EXTRA_DELETE_METHOD))
                            deleteUser(user);
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

    private void deleteUser(User user) {
        adapter.removeItem(user);
        deleteUserUseCase
                .deleteUser(user.getObjectId())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        getCompositeDisposable().add(d);
                    }

                    @Override
                    public void onComplete() {
                        dismissProgressBar();
                    }

                    @Override
                    public void onError(Throwable e) {
                        router.showError(e);
                    }
                });
    }
}


