package com.shorturl.shorturl.controller;

import com.shorturl.shorturl.dto.UrlRequestDto;
import com.shorturl.shorturl.service.UrlService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@RequiredArgsConstructor
@Controller
public class UrlController {

    private final UrlService urlService;

    @PostMapping("api/url")
    @ResponseBody
    public String Shortening(@RequestBody final UrlRequestDto urlRequest) {
        log.info(urlRequest.getOriginalUrl());
        return "localhost:8080/" + urlService.generateShortUrl(urlRequest);
    }

}
