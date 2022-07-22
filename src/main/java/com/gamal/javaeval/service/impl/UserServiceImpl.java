package com.gamal.javaeval.service.impl;

import com.gamal.javaeval.model.UserEntity;
import com.gamal.javaeval.model.UserNotFoundException;
import com.gamal.javaeval.model.UserRequest;
import com.gamal.javaeval.repository.UserRepository;
import com.gamal.javaeval.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository repository;
    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;
    @Override
    public UserEntity signUp(UserRequest request) {
        UserEntity entity = new UserEntity(request);
        entity.setPassword(bcryptEncoder.encode(entity.getPassword()));
        if(repository.findByEmail(entity.getEmail())!=null) throw new UserNotFoundException(HttpStatus.BAD_REQUEST,"User already exists en the database");
        UserEntity savedEntity = repository.save(entity);
        return savedEntity;
    }
}
