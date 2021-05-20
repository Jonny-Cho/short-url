package com.shorturl.shorturl.controller;

import com.shorturl.shorturl.dto.UrlRequestDto;
import com.shorturl.shorturl.service.UrlService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RequiredArgsConstructor
@Controller
public class MainController {

    private final UrlService urlService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("api/url")
    @ResponseBody
    public String Shortening(@RequestBody final UrlRequestDto urlRequest) {
        log.info(urlRequest.getOriginalUrl());
        return "localhost:8080/" + urlService.generateShortUrl(urlRequest);
    }

    @GetMapping("/{shorturl}")
    public String redirect(final HttpServletResponse response, @PathVariable final String shorturl) {
        log.info(shorturl);
        final String originalUrl = urlService.getOriginalUrlByShortUrl(shorturl.replace("http://localhost:8080/", ""));
        if (originalUrl != null) {
            return "redirect:" + "https://" + originalUrl;
        }
        return "wrong_shortening";
    }

}
