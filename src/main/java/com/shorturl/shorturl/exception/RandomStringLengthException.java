package com.shorturl.shorturl.exception;

public class RandomStringLengthException extends IllegalArgumentException {

    public static final String PLEASE_INPUT_MORE_THAN_TWO_CHARACTERS = "2글자 이상의 길이를 입력해주세요.";
    public static final String PLEASE_INPUT_MORE_THAN_THREE_SYMBOLS = "3글자 이상의 심볼을 입력해주세요.";

    public RandomStringLengthException(final String s) {
        super(s);
    }
}
