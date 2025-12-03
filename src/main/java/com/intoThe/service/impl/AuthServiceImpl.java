package com.intoThe.service.impl;

import com.intoThe.dto.request.UserLoginRequest;
import com.intoThe.dto.response.UserLoginResponse;
import com.intoThe.entities.Users;
import com.intoThe.repository.UserRepository;
import com.intoThe.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    //@Autowired ----> No @Autowired needed — ONLY ONE constructor
    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserLoginResponse userLogin(UserLoginRequest loginRequest) {

        UserLoginResponse userLoginResponse = new UserLoginResponse();
        System.out.println(loginRequest.getUserEmail());
        Users users = userRepository.findByUserEmailIdOrUserContactNumber(loginRequest.getUserEmail()
                ,loginRequest.getMobileNumber());

        if(users == null){
            return userLoginResponse.setIsLoginSuccess("False")
                    .setLoginMessage("User does not exist.Please register.")
                    .setStatusCode(HttpStatus.NOT_FOUND + "");
        }

        boolean isPasswordMatched = passwordEncoder.matches(
                loginRequest.getPassword(),
                users.getPassword()
        );
        System.out.println(!users.getConfirmPassword().equalsIgnoreCase(loginRequest.getPassword()));

        if(!isPasswordMatched){
            return userLoginResponse.setIsLoginSuccess("False")
                    .setLoginMessage("Wrong password.")
                    .setStatusCode(HttpStatus.UNAUTHORIZED + "");
        }

        userLoginResponse.setIsLoginSuccess("True")
                .setLoginMessage("Login successful.")
                .setStatusCode(HttpStatus.OK + "");
        return userLoginResponse;
    }
}
