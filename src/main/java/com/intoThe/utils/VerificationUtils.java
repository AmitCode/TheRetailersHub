package com.intoThe.utils;

import java.security.SecureRandom;
import java.util.Base64;

public class VerificationUtils {
    private static final SecureRandom secureRandomNumber = new SecureRandom();

    public static String generateVerificationToken(){
        byte[] tokenBytes = new byte[32];
        secureRandomNumber.nextBytes(tokenBytes);

        return Base64.getUrlEncoder()
                .withoutPadding()
                .encodeToString(tokenBytes);
    }

}
