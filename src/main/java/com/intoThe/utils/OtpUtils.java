package com.intoThe.utils;

import java.security.SecureRandom;

public class OtpUtils {
    private static final SecureRandom secureRandom = new SecureRandom();

    public static String generateOtp(){
//        int otp = secureRandom.nextInt();
        int otp = 100000 + secureRandom.nextInt(900000);
        /*secureRandom.nextInt(); will  generates 0 to 899999 so adding the 100000 will ensure 100000 to 899999*/
        return String.valueOf(otp);
    }


}
