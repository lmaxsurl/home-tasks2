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

    private RestService restService;

    @Inject
    public UserRepositoryImpl(RestService restService) {
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
                            list.add(mapUser(userResponse));
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
                        return mapUser(userResponse);
                    }
                });
    }

    @Override
    public Completable update(User user) {
        return restService.updateUser(mapUserRequest(user, true));
    }

    @Override
    public Completable delete(String id) {
        return restService.deleteUser(id);
    }

    @Override
    public Observable<User> add(User user) {
        return restService
                .addUser(mapUserRequest(user, false))
                .map(new Function<UserResponse, User>() {
                    @Override
                    public User apply(UserResponse userResponse) throws Exception {
                        return mapUser(userResponse);
                    }
                });
    }

    private User mapUser(UserResponse userResponse){
        return new User(userResponse.getFirstname(),
                userResponse.getSurname(),
                userResponse.getGender(),
                userResponse.getImageUrl(),
                userResponse.getEmail(),
                userResponse.getAge(),
                userResponse.getObjectId());
    }

    private UserRequest mapUserRequest(User user, boolean setObjectId){
        UserRequest userRequest = new UserRequest();
        userRequest.setFirstname(user.getFirstname());
        userRequest.setSurname(user.getSurname());
        userRequest.setAge(user.getAge());
        userRequest.setEmail(user.getEmail());
        userRequest.setGender(user.getGender());
        userRequest.setImageUrl(user.getImageUrl());
        userRequest.setObjectId(setObjectId? user.getObjectId() : "");
        return userRequest;
    }

    @Override
    public Observable<List<User>> search(String search) {
        return restService
                .search(search)
                .map(new Function<List<UserResponse>, List<User>>() {
                    @Override
                    public List<User> apply(List<UserResponse> userResponses) throws Exception {
                        final List<User> list = new ArrayList<>();
                        for (UserResponse userResponse : userResponses) {
                            list.add(mapUser(userResponse));
                        }
                        return list;
                    }
                });
    }
}
