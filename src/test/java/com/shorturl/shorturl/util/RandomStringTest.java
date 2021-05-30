package com.shorturl.shorturl.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

class RandomStringTest {

    public static String BASE62_REGEX = "^[A-Za-z0-9]{5,8}";

    @Autowired
    private final RandomString randomString = new RandomString();

    @DisplayName("nextString을 여러번 호출할 때마다 다른 random값이 생성된다")
    @Test
    void nextStringTest() {
        assertThat(randomString.nextString()).isNotEqualTo(randomString.nextString());
    }

    @DisplayName("영소문자, 영대문자, 숫자로 이루어진 5 ~ 8 사이의 문자가 랜덤으로 생성된다")
    @Test
    void regexTest() {
        String random = randomString.nextString();
        assertThat(random.matches(BASE62_REGEX)).isTrue();
    }

}
