package com.gamal.javaeval.service;

import com.gamal.javaeval.model.UserEntity;
import com.gamal.javaeval.model.UserRequest;

public interface UserService {
    UserEntity signUp(UserRequest request);
    UserEntity login(String token);

}
