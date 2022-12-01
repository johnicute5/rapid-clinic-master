package com.example.utils;

import org.springframework.stereotype.Service;

@Service
public class GenerateResetToken {

    public String generateResetPasswordToken(){
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";
        StringBuilder token = new StringBuilder(30);
        for (int i = 0; i < 30; i++) {
            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());
            token.append(AlphaNumericString
                    .charAt(index));
        }
        return token.toString();
    }
}
