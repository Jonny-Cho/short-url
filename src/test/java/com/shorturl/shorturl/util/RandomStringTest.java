package com.shorturl.shorturl.util;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RandomStringTest {

    @Test
    void randomTest(){
        final RandomString randomString = new RandomString();
        assertThat(randomString.nextString()).isNotEqualTo(randomString.nextString());
    }

}
