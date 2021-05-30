package com.shorturl.shorturl.dto;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class UrlRequestDtoTest {

    private static ValidatorFactory factory;
    private static Validator validator;

    @BeforeAll
    public static void init() {
        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @DisplayName("blank값으로는 UrlRequestDto를 생성할 수 없다")
    @Test
    void blankTest() {
        UrlRequestDto urlRequestDto = new UrlRequestDto("   ");
        Set<ConstraintViolation<UrlRequestDto>> violations = validator.validate(urlRequestDto);
        assertThat(violations).isNotEmpty();
    }

    @DisplayName("유효하지 않은 url값으로는 UrlRequestDto를 생성할 수 없다")
    @ParameterizedTest
    @ValueSource(strings = {
            "favicon",
            "naver",
            "https:/www.naver.com",
            "https://naver",
            "http//www.naver"})
    void urlFailTest(final String target) {
        UrlRequestDto urlRequestDto = new UrlRequestDto(target);
        Set<ConstraintViolation<UrlRequestDto>> violations = validator.validate(urlRequestDto);
        assertThat(violations).isNotEmpty();
    }

    @DisplayName("유효한 url만 UrlRequestDto를 생성할 수 있다")
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
        UrlRequestDto urlRequestDto = new UrlRequestDto(target);
        Set<ConstraintViolation<UrlRequestDto>> violations = validator.validate(urlRequestDto);
        assertThat(violations).isEmpty();
    }

}
