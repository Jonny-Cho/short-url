package com.shorturl.shorturl.util;

import com.shorturl.shorturl.exception.RandomStringLengthException;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Objects;
import java.util.Random;

import static com.shorturl.shorturl.exception.RandomStringLengthException.PLEASE_INPUT_MORE_THAN_THREE_SYMBOLS;
import static com.shorturl.shorturl.exception.RandomStringLengthException.PLEASE_INPUT_MORE_THAN_TWO_CHARACTERS;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RandomString {

    private static final String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String lower = upper.toLowerCase();
    private static final String digits = "0123456789";
    private static final String alphanum = upper + lower + digits;
    private static final int MIN_RANDOM_VALUE = 5;
    private static final int MAX_RANDOM_VALUE = 8;

    private final Random random;
    private final char[] symbols;
    private final char[] buf;
    private RandomString(final int length, final Random random, final String symbols) {
        if (length < 1) throw new RandomStringLengthException(PLEASE_INPUT_MORE_THAN_TWO_CHARACTERS);
        if (symbols.length() < 2) throw new RandomStringLengthException(PLEASE_INPUT_MORE_THAN_THREE_SYMBOLS);
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
        this(randomBetween(MIN_RANDOM_VALUE, MAX_RANDOM_VALUE));
    }

    private static int randomBetween(final int min, final int max) {
        return new Random().nextInt(max - min + 1) + min;
    }

    public String nextString() {
        for (int idx = 0; idx < buf.length; idx++) {
            buf[idx] = symbols[random.nextInt(symbols.length)];
        }
        return new String(buf);
    }

}
