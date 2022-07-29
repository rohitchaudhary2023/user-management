package com.cg.user.management.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtility {
    public static boolean isValidPassword(String password){
        if (password == null) return false;

        // Regex to check valid password.
        String regex = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%*&])"
                + "(?=\\S+$).{8,20}$";

        // Compile the regex
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(password);
        return m.matches();
    }
}
