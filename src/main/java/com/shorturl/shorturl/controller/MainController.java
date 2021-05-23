package com.shorturl.shorturl.controller;

import com.shorturl.shorturl.dto.UrlRequestDto;
import com.shorturl.shorturl.service.UrlService;
import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@Controller
public class MainController {

    protected final UrlService urlService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/{shorturl}")
    public String redirect(@PathVariable(value = "shorturl") @NotNull final String shorturl) {
        final String originalUrl = urlService.getOriginalUrlByShortUrl(shorturl.replace("http://localhost:8080/", ""));
        if (originalUrl != null) {
            return "redirect:" + "https://" + originalUrl;
        }
        return "wrong_shortening";
    }

}
