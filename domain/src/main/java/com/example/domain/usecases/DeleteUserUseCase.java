package com.example.domain.usecases;

import com.example.domain.executors.PostExecutionThread;
import com.example.domain.repositories.UserRepository;

import javax.inject.Inject;

import io.reactivex.Completable;

public class DeleteUserUseCase extends BaseUseCase {

    private UserRepository userRepository;

    @Inject
    public DeleteUserUseCase(UserRepository userRepository,
                              PostExecutionThread postExecutionThread) {
        super(postExecutionThread);
        this.userRepository = userRepository;
    }

    public Completable deleteUser(String id){
        return userRepository
                .delete(id)
                .subscribeOn(executionThread)
                .observeOn(postExecutionThread);
    }
}
