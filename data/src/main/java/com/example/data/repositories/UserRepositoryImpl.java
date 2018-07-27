package com.example.data.repositories;

import android.util.Log;

import com.example.data.entity.UserRequest;
import com.example.data.entity.UserResponse;
import com.example.data.network.RestService;
import com.example.domain.entity.User;
import com.example.domain.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class UserRepositoryImpl implements UserRepository {

    private static final String TAG = "AAA UserReposImpl";
    private RestService restService;

    @Inject
    public UserRepositoryImpl(RestService restService) {
        Log.d(TAG, "UserRepositoryImpl: ");
        this.restService = restService;
    }

    @Override
    public Observable<List<User>> getAll() {
        return restService
                .getAllUsers()
                .map(new Function<List<UserResponse>, List<User>>() {
                    @Override
                    public List<User> apply(List<UserResponse> userResponses) throws Exception {
                        final List<User> list = new ArrayList<>();
                        for (UserResponse userResponse : userResponses) {
                            list.add(new User(userResponse.getFirstname(),
                                    userResponse.getSurname(),
                                    userResponse.getGender(),
                                    userResponse.getImageUrl(),
                                    userResponse.getEmail(),
                                    userResponse.getAge(),
                                    userResponse.getObjectId()));
                        }
                        return list;
                    }
                });
    }

    @Override
    public Observable<User> getUser(String id) {
        return restService.getUser(id)
                .map(new Function<UserResponse, User>() {
                    @Override
                    public User apply(UserResponse userResponse) throws Exception {
                        return new User(userResponse.getFirstname(),
                                userResponse.getSurname(),
                                userResponse.getGender(),
                                userResponse.getImageUrl(),
                                userResponse.getEmail(),
                                userResponse.getAge(),
                                userResponse.getObjectId());
                    }
                });
    }

    @Override
    public Completable update(User user) {
        UserRequest userRequest = new UserRequest();
        userRequest.setFirstname(user.getFirstname());
        userRequest.setSurname(user.getSurname());
        userRequest.setAge(user.getAge());
        userRequest.setEmail(user.getEmail());
        userRequest.setGender(user.getGender());
        userRequest.setImageUrl(user.getImageUrl());
        userRequest.setObjectId(user.getObjectId());
        return restService.updateUser(userRequest);
    }

    @Override
    public Completable delete(String id) {
        return restService.deleteUser(id);
    }

    @Override
    public Completable add(User user) {
        UserRequest userRequest = new UserRequest();
        userRequest.setFirstname(user.getFirstname());
        userRequest.setSurname(user.getSurname());
        userRequest.setAge(user.getAge());
        userRequest.setEmail(user.getEmail());
        userRequest.setGender(user.getGender());
        userRequest.setImageUrl(user.getImageUrl());
        userRequest.setObjectId("");
        return restService.addUser(userRequest);
    }
}
