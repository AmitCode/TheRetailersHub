package com.intoThe.dto.response;


public class UserLoginResponse {
    private String isLoginSuccess;
    private String loginMessage;
    private String statusCode;

    public String getStatusCode() {
        return statusCode;
    }

    public UserLoginResponse setStatusCode(String statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    public String getIsLoginSuccess() {
        return isLoginSuccess;
    }

    public UserLoginResponse setIsLoginSuccess(String isLoginSuccess) {
        this.isLoginSuccess = isLoginSuccess;
        return this;
    }

    public String getLoginMessage() {
        return loginMessage;
    }

    public UserLoginResponse setLoginMessage(String loginMessage) {
        this.loginMessage = loginMessage;
        return this;
    }
}
