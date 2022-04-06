package com.example.couponsproject.util;

import java.sql.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputUserValidation {

    private final static String PASSWORD_REGEX = "[a-zA-Z0-9]{4,12}";
    private final static String EMAIL_REGEX = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
    private final static String NAME_REGEX = "[a-zA-Z ,.'-]{2,}";
    private final static String DATE_REGEX = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$";

    // --------------------------------Password validation-----------------------------------

    public static boolean isPasswordValid(String password) {

        return password.matches(PASSWORD_REGEX);
    }

    //----------------------------------------------------------------------------------------

    // --------------------------------Email validation---------------------------------------

    public static boolean isEmailValid(String email) {

        // This line converts the regex code into the desired pattern
        Pattern emailPattern = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);

        //This line checks if the inserted email follows the previously created pattern
        Matcher matcher = emailPattern.matcher(email);

        return matcher.find();
    }

    // --------------------------------Name validation---------------------------------------

    public static boolean isNameValid(String name) {
        return name.matches(NAME_REGEX);
    }

    // --------------------------------Date validation---------------------------------------

    public static boolean isDateValid(Date date) {
        return date.toString().matches(DATE_REGEX);
    }
}
