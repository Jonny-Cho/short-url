package com.shorturl.shorturl.controller;

import com.shorturl.shorturl.dto.UrlRequestDto;
import com.shorturl.shorturl.dto.UrlResponseDto;
import com.shorturl.shorturl.service.UrlService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Slf4j
@RequiredArgsConstructor
@Controller
public class UrlController {

    private final UrlService urlService;

    @PostMapping("api/url")
    @ResponseBody
    public ResponseEntity<UrlResponseDto> shortening(@RequestBody @Valid final UrlRequestDto urlRequest) {
        log.info(urlRequest.getOriginalUrl());
        invalidOriginalUrl(urlRequest.getOriginalUrl());
        return new ResponseEntity<>(new UrlResponseDto(urlService.getShortUrl(urlRequest)), HttpStatus.OK);
    }

    private void invalidOriginalUrl(final String originalUrl) {
        if (originalUrl.contains("15.165.88.81:8080")) throw new IllegalArgumentException("서비스 url은 사용할 수 없습니다.");
        canAccessUrl(originalUrl);
    }

    private void canAccessUrl(final String originalUrl) {
        try {
            URL url = new URL("https://" + originalUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            br.close();
        } catch (Exception e) {
            throw new IllegalStateException("접근 불가능한 url입니다.");
        }
    }

}
