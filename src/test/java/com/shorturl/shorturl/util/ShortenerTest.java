package com.shorturl.shorturl.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ShortenerTest {

    private final Shortener shortener = new Shortener();

    //218_340_105_584_895L (약 218조)
    public final long MAXIMUM_ID = (long) Math.pow(shortener.getBASE62Length(), 8) - 1;

    @DisplayName("0을 인코딩한 경우")
    @Test
    void zeroTest() {
        System.out.println((long) Math.pow(shortener.getBASE62Length() + 2, 8) - 1);
        final String shortUrl = shortener.encoding(0);
        assertThat(shortUrl).isEqualTo("A");
    }

    @DisplayName("8자리를 넘지 않는 경우")
    @Test
    void limitTest() {
        final String shortUrl = shortener.encoding(MAXIMUM_ID);
        assertThat(shortUrl.length() <= 8).isTrue();
    }

    @DisplayName("8자리를 넘는 경우")
    @Test
    void overLimitTest() {
        final String shortUrl = shortener.encoding(MAXIMUM_ID + 1);
        assertThat(shortUrl.length() <= 8).isFalse();
        assertThat(shortUrl.length()).isEqualTo(9);
    }

}
