package com.example.domain.usecases;

import com.example.domain.entity.User;
import com.example.domain.executors.PostExecutionThread;
import com.example.domain.repositories.UserRepository;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;

public class AddUserUseCase extends BaseUseCase {

    private UserRepository userRepository;

    @Inject
    public AddUserUseCase(UserRepository userRepository,
                             PostExecutionThread postExecutionThread) {
        super(postExecutionThread);
        this.userRepository = userRepository;
    }

    public Observable<User> addUser(User user) {
        return userRepository
                .add(user)
                .subscribeOn(executionThread)
                .observeOn(postExecutionThread);
    }
}
