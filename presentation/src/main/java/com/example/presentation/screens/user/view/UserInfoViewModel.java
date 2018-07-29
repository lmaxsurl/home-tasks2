package com.example.presentation.screens.user.view;

import android.content.Intent;
import android.databinding.ObservableField;
import android.util.Log;
import android.widget.Toast;

import com.example.android.hometasks2.R;
import com.example.app.App;
import com.example.data.entity.UserResponse;
import com.example.domain.entity.User;
import com.example.domain.usecases.DeleteUserUseCase;
import com.example.domain.usecases.GetUserUseCase;
import com.example.domain.usecases.UpdateUserUseCase;
import com.example.presentation.base.BaseViewModel;
import com.example.presentation.utils.Extras;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;

public class UserInfoViewModel extends BaseViewModel<UserInfoRouter> {

    @Inject
    public GetUserUseCase userUseCase;
    @Inject
    public UpdateUserUseCase updateUserUseCase;

    public ObservableField<String> firstname = new ObservableField<>();
    public ObservableField<String> surname = new ObservableField<>();
    public ObservableField<String> gender = new ObservableField<>();
    public ObservableField<String> imageUrl = new ObservableField<>();
    public ObservableField<String> email = new ObservableField<>();
    public ObservableField<String> age = new ObservableField<>();
    private String userId;

    public UserInfoViewModel() {
        showProgressBar();
    }

    @Override
    protected void runInject() {
        App.getAppComponent().runInject(this);
    }

    public void loadUser(String objectId) {
        userUseCase.getUser(objectId)
                .subscribe(new Observer<User>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        getCompositeDisposable().add(d);
                    }

                    @Override
                    public void onNext(User user) {
                        setUserInfo(user);
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

    private void setUserInfo(User user) {
        firstname.set(user.getFirstname());
        surname.set(user.getSurname());
        gender.set(user.getGender());
        email.set(user.getEmail());
        age.set("" + user.getAge());
        imageUrl.set(user.getImageUrl());
        userId = user.getObjectId();
    }

    public void onEditClick() {
        if (isFilled()) {
            showProgressBar();
            updateUserUseCase
                    .updateUser(new User(
                            firstname.get(),
                            surname.get(),
                            gender.get(),
                            imageUrl.get(),
                            email.get(),
                            Integer.parseInt(age.get()),
                            userId))
                    .subscribe(new CompletableObserver() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            getCompositeDisposable().add(d);
                        }

                        @Override
                        public void onComplete() {
                            router.sendChanges(Extras.RESULT_EDIT, userId);
                        }

                        @Override
                        public void onError(Throwable e) {
                            router.showError(e);
                        }
                    });
        } else
            router.showToast(R.string.input_error);
    }

    public void onDeleteClick() {
        showProgressBar();
        router.sendChanges(Extras.RESULT_DELETE, userId);
    }

    private boolean isFilled() {
        return firstname.get().length() > 0 &&
                surname.get().length() > 0 &&
                gender.get().length() > 0 &&
                imageUrl.get().length() > 0 &&
                email.get().length() > 0 &&
                age.get().length() > 0;
    }
}
