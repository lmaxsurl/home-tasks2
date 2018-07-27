package com.example.domain.usecases;

import android.util.Log;

import com.example.domain.entity.User;
import com.example.domain.executors.PostExecutionThread;
import com.example.domain.repositories.UserRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetListUserUseCase extends BaseUseCase {

    private static final String TAG = "AAA GetListUserUseCase";
    private UserRepository userRepository;

    @Inject
    public GetListUserUseCase(UserRepository userRepository,
                              PostExecutionThread postExecutionThread) {
        super(postExecutionThread);
        Log.d(TAG, "GetListUserUseCase: constructor");
        this.userRepository = userRepository;
    }

    public Observable<List<User>> getUsers() {
        Log.d(TAG, "getUsers: ");
        return userRepository
                .getAll()
                .subscribeOn(executionThread)
                .observeOn(postExecutionThread);
    }
}
