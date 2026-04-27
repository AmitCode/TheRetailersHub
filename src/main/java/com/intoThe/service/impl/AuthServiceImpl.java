package com.intoThe.service.impl;

import com.intoThe.dto.request.UserLoginRequest;
import com.intoThe.dto.response.UserLoginResponse;
import com.intoThe.entities.Users;
import com.intoThe.repository.UserRepository;
import com.intoThe.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    //@Autowired ----> No @Autowired needed — ONLY ONE constructor
    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserLoginResponse userLogin(UserLoginRequest loginRequest) {

        UserLoginResponse userLoginResponse = new UserLoginResponse();
        System.out.println(loginRequest.getUserName());
        Optional<Users> user = userRepository.findByUserName(loginRequest.getUserName());

        if(user.isEmpty()){
            return userLoginResponse.setIsLoginSuccess("False")
                    .setLoginMessage("User not found!...")
                    .setStatusCode(String.valueOf(HttpStatus.NOT_FOUND));
        }
        Users users = user.get();
        boolean isPasswordMatched = passwordEncoder.matches(
                loginRequest.getPassword(),
                users.getPassword()
        );
        System.out.println(!users.getPassword().equalsIgnoreCase(loginRequest.getPassword()));

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
