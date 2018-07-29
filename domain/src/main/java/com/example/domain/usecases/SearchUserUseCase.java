package com.example.domain.usecases;

import com.example.domain.entity.User;
import com.example.domain.executors.PostExecutionThread;
import com.example.domain.repositories.UserRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class SearchUserUseCase extends BaseUseCase {

    private UserRepository userRepository;

    @Inject
    public SearchUserUseCase(UserRepository userRepository,
                             PostExecutionThread postExecutionThread) {
        super(postExecutionThread);
        this.userRepository = userRepository;
    }

    public Observable<List<User>> searchUsers(String search){
        return userRepository
                .search(search)
                .subscribeOn(executionThread)
                .observeOn(postExecutionThread);
    }
}
