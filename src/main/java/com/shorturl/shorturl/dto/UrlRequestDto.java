package com.shorturl.shorturl.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UrlRequestDto {

    @NotBlank(message = "url을 입력해주세요.")
    @Pattern(
            regexp = "((http)s?)?(:\\/\\/)?(www\\.)?[a-zA-Z0-9@:%._\\+~#=]+\\.[a-zA-Z0-9@:%._\\/+~#=?]+",
            message = "url 형식이 아닙니다."
    )
    private String originalUrl;

}
