package com.shorturl.shorturl.util;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

public class RegexTest {

    @ParameterizedTest
    @ValueSource(strings = {
            "https://www.naver.com",
            "https://naver.com",
            "http://www.naver.com",
            "http://naver.com",
            "www.naver.com",
            "naver.com",
            "naver.org",
            "naver.co.kr?param=1",
            "naver.co.kr/board/1"})
    void urlSuccessTest(final String target) {
        assertThat(Regex.isValidUrl(target)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "favicon",
            "naver",
            "https:/www.naver.com",
            "https://naver",
            "http//www.naver"})
    void urlFailTest(final String target) {
        assertThat(Regex.isValidUrl(target)).isFalse();
    }

}
