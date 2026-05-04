package com.intoThe.controller;

import com.intoThe.dto.request.UserLoginRequest;
import com.intoThe.dto.response.UserLoginResponse;
import com.intoThe.service.AuthService;
import com.intoThe.utils.JWTUtils;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class RetailerAuthentication {

    //@Autowired
    private final AuthService authService;
    private final AuthenticationManager authManager;
    private final JWTUtils jwtUtils;

    public RetailerAuthentication(AuthService authService, AuthenticationManager authManager,
                                  JWTUtils jwtUtils) {
        this.authService = authService;
        this.authManager = authManager;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponse> loginToSystem(@Valid @RequestBody UserLoginRequest
                                                                       userLoginRequest){
        UserLoginResponse response = new UserLoginResponse();
        authService.userLogin(userLoginRequest);
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLoginRequest.getUserName(),
                        userLoginRequest.getPassword())
        );

        if(authentication.isAuthenticated()){
            response.setStatusCode(String.valueOf(HttpStatus.OK));
            response.setIsLoginSuccess("Y");
            response.setLoginMessage(jwtUtils.generateJwtToken(userLoginRequest.getUserName()));
        }else{
            response.setStatusCode(String.valueOf(HttpStatus.UNAUTHORIZED));
            response.setLoginMessage("Login unsuccessful!.");
            response.setIsLoginSuccess("N");
        }
        return ResponseEntity.ok(response);
     }
}
