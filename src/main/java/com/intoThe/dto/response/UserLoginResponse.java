package com.intoThe.dto.response;


import com.intoThe.validation.annoation.AtLeastOneRequired;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AtLeastOneRequired
@Getter
@Setter
@NoArgsConstructor
public class UserLoginResponse {
    private String isLoginSuccess;
    private String loginMessage;
    private String statusCode;
}
