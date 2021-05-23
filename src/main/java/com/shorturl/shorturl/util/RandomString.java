package com.shorturl.shorturl.util;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Objects;
import java.util.Random;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RandomString {

    public String nextString() {
        for (int idx = 0; idx < buf.length; ++idx) {
            buf[idx] = symbols[random.nextInt(symbols.length)];
        }
        return new String(buf);
    }

    private static final String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String lower = upper.toLowerCase();
    private static final String digits = "0123456789";
    private static final String alphanum = upper + lower + digits;
    private final Random random;
    private final char[] symbols;
    private final char[] buf;

    private RandomString(final int length, final Random random, final String symbols) {
        if (length < 1) throw new IllegalArgumentException("2글자 이상의 길이를 입력하세요.");
        if (symbols.length() < 2) throw new IllegalArgumentException("3글자 이상의 심볼을 입력하세요.");
        this.random = Objects.requireNonNull(random);
        this.symbols = symbols.toCharArray();
        this.buf = new char[length];
    }

    private RandomString(final int length, final Random random) {
        this(length, random, alphanum);
    }

    private RandomString(final int length) {
        this(length, new SecureRandom());
    }

    public RandomString() {
        this(randomBetween(5, 8));
    }

    private static int randomBetween(final int min, final int max) {
        return new Random().nextInt(max - min + 1) + min;
    }

}
