package com.example.presentation.screens.user.view;

import android.databinding.ObservableField;

import com.example.android.hometasks2.R;
import com.example.app.App;
import com.example.domain.entity.User;
import com.example.domain.usecases.AddUserUseCase;
import com.example.presentation.base.BaseViewModel;

import javax.inject.Inject;

import io.reactivex.CompletableObserver;
import io.reactivex.disposables.Disposable;

public class AddUserViewModel extends BaseViewModel<AddUserRouter> {

    @Inject
    public AddUserUseCase addUserUseCase;

    public ObservableField<String> firstname = new ObservableField<>("");
    public ObservableField<String> surname = new ObservableField<>("");
    public ObservableField<String> gender = new ObservableField<>("");
    public ObservableField<String> imageUrl = new ObservableField<>("");
    public ObservableField<String> email = new ObservableField<>("");
    public ObservableField<String> age = new ObservableField<>("");


    @Override
    protected void runInject() {
        App.getAppComponent().runInject(this);
    }

    public void onAddClick() {
        if (isFilled()) {
            addUserUseCase
                    .addUser(new User(
                            firstname.get(),
                            surname.get(),
                            gender.get(),
                            imageUrl.get(),
                            email.get(),
                            Integer.parseInt(age.get()),
                            null))
                    .subscribe(new CompletableObserver() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            getCompositeDisposable().add(d);
                        }

                        @Override
                        public void onComplete() {
                            router.finishActivity();
                        }

                        @Override
                        public void onError(Throwable e) {
                            router.showError(e);
                        }
                    });
        } else
            router.showToast(R.string.input_error);
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
