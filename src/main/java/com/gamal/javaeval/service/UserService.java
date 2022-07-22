package com.gamal.javaeval.service;

import com.gamal.javaeval.model.UserEntity;
import com.gamal.javaeval.model.UserRequest;

public interface UserService {
    public UserEntity signUp(UserRequest request);
    public UserEntity login(String token);

    public String token(String token);
}
