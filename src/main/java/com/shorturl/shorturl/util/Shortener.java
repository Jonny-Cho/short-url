package com.shorturl.shorturl.util;

import org.springframework.stereotype.Component;

@Component
public class Shortener {

    private final char[] BASE62 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray();

    public int getBASE62Length() {
        return BASE62.length;
    }

    public String encoding(long value) {
        final StringBuilder sb = new StringBuilder();
        do {
            final int i = (int)(value % BASE62.length);
            sb.append(BASE62[i]);
            value /= BASE62.length;
        } while (value > 0);
        return sb.toString();
    }

    public int decoding(String value) {
        int result = 0;
        int power = 1;
        for (int i = 0; i < value.length(); i++) {
            int digit = new String(BASE62).indexOf(value.charAt(i));
            result += digit * power;
            power *= BASE62.length;
        }
        return result;
    }

}
