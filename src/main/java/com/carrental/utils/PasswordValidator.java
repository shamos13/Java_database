package com.carrental.utils;

import org.mindrot.jbcrypt.BCrypt;

import java.util.Scanner;


public class PasswordValidator {

    // hash the password
    public static String hashPassword(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean checkPassword(String password, String hashedPassword){
        return BCrypt.checkpw(password, hashedPassword);
    }


}
