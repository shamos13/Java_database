package com.carrental.utils;

import java.util.regex.Pattern;

public class InputValidator {
    private static final String EMAIL_REGEX = "^[A-za-z0-9_.]+@[A-za-z0-9-.]+$";

    // Checks the email if it valid and not null
    public static boolean isValidEmail(String email){
        return email != null && Pattern.matches(EMAIL_REGEX, email);
    }

    // Re-prompt the user for correct email

}
