package com.shorturl.shorturl.dto;

import com.shorturl.shorturl.ErrorCode;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ErrorResponseDto {

    private String message;
    private int status;
    private String code;

    public static ErrorResponseDto of(final ErrorCode errorCode, final String errorMessage) {
        return new ErrorResponseDto(errorMessage, errorCode.getStatus(), errorCode.getCode());
    }
}
