package com.intoThe.utils;

import com.intoThe.dto.request.EmailRequest;
import com.intoThe.entities.Users;

public class AuthServiceUtils {
    public static EmailRequest prepareEmailRequest(Users user, String verificationToken){
        return EmailRequest.create()
                .setEmailSubject("User Registration Conformation")
                .setEmailType("REG")
                .setUserName(user.getUserName())
                .setEmailId(user.getUserEmail())
                .setVerificationUrl("http://localhost:8089/authService/verify/verifyUserAccount?verificationToken="+verificationToken);
    }
}
