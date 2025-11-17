package com.intoThe.dto.request;

import jakarta.validation.constraints.NotBlank;

public class UserLoginRequest {
    private String userEmail;

    @NotBlank(message = "Password can't be blank.")
    private String password;

    private String mobileNumber;

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
}
