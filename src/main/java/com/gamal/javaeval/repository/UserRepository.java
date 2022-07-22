package com.gamal.javaeval.repository;

import com.gamal.javaeval.model.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface UserRepository extends CrudRepository<UserEntity, UUID> {
    UserEntity findByEmail(String email);
}
