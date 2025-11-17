package com.intoThe.service;

import com.intoThe.dto.request.UserLoginRequest;
import com.intoThe.dto.response.UserLoginResponse;
import org.springframework.stereotype.Service;

public interface AuthService {
    public UserLoginResponse userLogin(UserLoginRequest loginRequest);
}
