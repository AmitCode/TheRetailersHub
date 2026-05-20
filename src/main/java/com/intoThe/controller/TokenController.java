package com.intoThe.controller;

import com.intoThe.service.impl.TokenVerificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/verify")
public class TokenController {

    private final TokenVerificationService verificationService;
    public TokenController(TokenVerificationService verificationService){
        this.verificationService = verificationService;
    }

    @PostMapping("/verifyUserAccount")
    public ResponseEntity<?> verifyUser(@RequestParam String verificationToken){
        return new ResponseEntity<>(verificationService.verifyToken(verificationToken), HttpStatus.OK);
    }
}
