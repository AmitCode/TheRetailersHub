package com.intoThe.controller;

import com.intoThe.dto.request.UserLoginRequest;
import com.intoThe.dto.response.UserLoginResponse;
import com.intoThe.service.AuthService;
import com.intoThe.service.OtpService;
import com.intoThe.utils.JWTUtils;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class RetailerAuthentication {

    //@Autowired
    private final AuthService authService;
    private final AuthenticationManager authManager;
    private final JWTUtils jwtUtils;
    private final OtpService otpService;

    public RetailerAuthentication(AuthService authService, AuthenticationManager authManager,
                                  JWTUtils jwtUtils, OtpService otpService) {
        this.authService = authService;
        this.authManager = authManager;
        this.jwtUtils = jwtUtils;
        this.otpService = otpService;
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponse> loginToSystem(@Valid @RequestBody UserLoginRequest
                                                                       userLoginRequest){
        UserLoginResponse response = new UserLoginResponse();

        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLoginRequest.getUserName(),
                        userLoginRequest.getPassword())
        );
        response.setStatusCode(String.valueOf(HttpStatus.OK));
        response.setIsLoginSuccess("Y");
        response.setLoginMessage(jwtUtils.generateJwtToken(userLoginRequest.getUserName()));
        return ResponseEntity.ok(response);
     }

    @PostMapping("/loginUsingOtp")
    public ResponseEntity<UserLoginResponse> loginUsingOtp(@RequestHeader String otp){

        UserLoginResponse response = new UserLoginResponse();
        String userName = otpService.validateOtpAuth(otp);

        response.setStatusCode(String.valueOf(HttpStatus.OK));
        response.setIsLoginSuccess("Y");
        response.setLoginMessage(jwtUtils.generateJwtToken(userName));
        return ResponseEntity.ok(response);
    }

}
