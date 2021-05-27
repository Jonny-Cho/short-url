package com.shorturl.shorturl.util;

import java.util.regex.Pattern;

public class Regex {

    private static String urlPattern = "((http)s?)?(:\\/\\/)?(www\\.)?[a-zA-Z0-9@:%._\\+~#=]+\\.[a-zA-Z0-9@:%._\\/+~#=?]+";

    public static boolean isValidUrl(final String target){
        return Pattern.matches(urlPattern, target);
    }

}
