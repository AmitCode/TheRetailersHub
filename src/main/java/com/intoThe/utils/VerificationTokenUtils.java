package com.intoThe.utils;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Base64;

public class VerificationTokenUtils {
    private static final SecureRandom secureRandomNumber = new SecureRandom();

    public static String generateVerificationToken(){
        byte[] tokenBytes = new byte[32];
        secureRandomNumber.nextBytes(tokenBytes);

        return Base64.getUrlEncoder()
                .withoutPadding()
                .encodeToString(tokenBytes);
    }

    public static boolean isTokenExpired(LocalDateTime currentTokenDate, String tokenExpiryTime) throws Exception {
        long durationInMinutes = CustomDateTime.findTheDifferenceInMinutes(currentTokenDate);
        return (Long.parseLong(tokenExpiryTime) < durationInMinutes);
    }

}
