package com.example.wishlistmanagement.controller;

import com.example.wishlistmanagement.model.AuthenticationResponse;
import com.example.wishlistmanagement.dao.LoginUser;
import com.example.wishlistmanagement.dao.RegisterUser;
import com.example.wishlistmanagement.model.User;
import com.example.wishlistmanagement.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    @Autowired
    AuthenticationService authenticationService;


    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterUser request) {
        User user = new User(request.getName(),request.getUsername(),request.getEmail(),request.getPassword());
        return ResponseEntity.ok(authenticationService.register(user));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginUser loginUser) {
        return ResponseEntity.ok(authenticationService.authenticate(loginUser.getUsername(), loginUser.getPassword()));
    }
}
