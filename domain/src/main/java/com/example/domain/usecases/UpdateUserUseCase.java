package com.example.domain.usecases;

import com.example.domain.entity.User;
import com.example.domain.executors.PostExecutionThread;
import com.example.domain.repositories.UserRepository;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class UpdateUserUseCase extends BaseUseCase {

    private UserRepository userRepository;

    @Inject
    public UpdateUserUseCase(UserRepository userRepository,
                             PostExecutionThread postExecutionThread) {
        super(postExecutionThread);
        this.userRepository = userRepository;
    }

    public Completable updateUser(User user) {
        return userRepository
                .update(user)
                .subscribeOn(executionThread)
                .observeOn(postExecutionThread);
    }
}
