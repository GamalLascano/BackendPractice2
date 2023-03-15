package com.gamal.javaeval.service.impl;

import com.gamal.javaeval.util.TokenHelper;
import com.gamal.javaeval.model.UserEntity;
import com.gamal.javaeval.model.UserException;
import com.gamal.javaeval.model.UserRequest;
import com.gamal.javaeval.repository.UserRepository;
import com.gamal.javaeval.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final BCryptPasswordEncoder bcryptEncoder;
    private final TokenHelper tokenHelper;

    @Autowired
    public UserServiceImpl(UserRepository repository, BCryptPasswordEncoder bcryptEncoder, TokenHelper tokenHelper) {
        this.repository = repository;
        this.bcryptEncoder = bcryptEncoder;
        this.tokenHelper = tokenHelper;
    }

    @Override
    public UserEntity signUp(UserRequest request) {
        UserEntity entity = new UserEntity(request);
        entity.setPassword(bcryptEncoder.encode(entity.getPassword()));
        entity.setToken(tokenHelper.generateToken(entity));
        if (repository.findByEmail(entity.getEmail()).isPresent())
            throw new UserException(HttpStatus.BAD_REQUEST, "User already exists en the database");
        return repository.save(entity);
    }

    @Override
    public UserEntity login(String token) {
        try{
            Optional<UserEntity> entity = tokenHelper.validateUser(token);
            if (entity.isPresent()) {
                UserEntity modifiedEntity = entity.get();
                modifiedEntity.setToken(tokenHelper.generateToken(modifiedEntity));
                modifiedEntity.setIsActive(true);
                modifiedEntity.setLastLogin(Timestamp.from(Instant.now()));
                repository.save(modifiedEntity);
                return modifiedEntity;
            } else throw new UserException(HttpStatus.NOT_FOUND, "User does not exist in the database");
        }catch(UserException e){
            throw new UserException(e.getStatus(),e.getMessage());
        }
    }

}
