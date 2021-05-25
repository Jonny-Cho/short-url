package com.shorturl.shorturl.exception;

public class EmptyOriginalUrlException extends IllegalArgumentException {

    public static final String PLEASE_INPUT_ORIGINAL_URL = "originalUrl을 입력해주세요";

    public EmptyOriginalUrlException() {
        super(PLEASE_INPUT_ORIGINAL_URL);
    }

    public EmptyOriginalUrlException(final String s) {
        super(s);
    }
}
