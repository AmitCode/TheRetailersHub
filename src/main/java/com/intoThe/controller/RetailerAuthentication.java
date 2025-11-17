package com.intoThe.controller;

import com.intoThe.dto.request.UserLoginRequest;
import com.intoThe.dto.response.UserLoginResponse;
import com.intoThe.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class RetailerAuthentication {

    @Autowired
    private final AuthService authService;
    public RetailerAuthentication(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponse> loginToSystem(@Valid @RequestBody UserLoginRequest
                                                                       userLoginRequest){
         return ResponseEntity.ok(authService.userLogin(userLoginRequest));
     }
}
