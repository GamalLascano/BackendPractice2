package com.gamal.javaeval.controller;


import com.gamal.javaeval.model.UserEntity;
import com.gamal.javaeval.model.UserRequest;
import com.gamal.javaeval.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController("/")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("sign-up")
    public ResponseEntity<UserEntity> signUp(@RequestBody @Validated UserRequest request){
        return ResponseEntity.ok(userService.signUp(request));
    }
    @GetMapping("login")
    public ResponseEntity<UserEntity> login(@RequestParam(name = "token")String token){
        return ResponseEntity.ok(userService.login(token));
    }
}
