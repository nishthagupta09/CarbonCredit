package com.nishtha.CarbonCredit.Util;


import java.security.SecureRandom;

public class OTPUtil {
    private static final SecureRandom RNG = new SecureRandom();
    public static String generateOtp() {
        int code = 100000 + RNG.nextInt(900000);
        return String.valueOf(code);
    }
}

