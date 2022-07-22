package com.gamal.javaeval.controller;


import com.gamal.javaeval.model.UserEntity;
import com.gamal.javaeval.model.UserRequest;
import com.gamal.javaeval.service.UserService;
import com.gamal.javaeval.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController("/")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("sign-up")
    public ResponseEntity<UserEntity> signUp(@RequestBody @Valid UserRequest request){
        return ResponseEntity.ok(userService.signUp(request));
    }
}
