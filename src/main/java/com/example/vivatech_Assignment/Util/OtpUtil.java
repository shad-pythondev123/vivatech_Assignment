package com.example.vivatech_Assignment.Util;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class OtpUtil {
    public String generatedOTP(){

        Random random = new Random();

        int randomOTP = random.nextInt(99999);
        String otp = Integer.toString(randomOTP);
        while(otp.length() < 6 ){
            otp = "0"+otp;
        }
        return otp;
    }
}
