package com.shorturl.shorturl.controller;

import com.shorturl.shorturl.service.UrlService;
import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@RequiredArgsConstructor
@Controller
public class MainController {

    protected final UrlService urlService;

    @GetMapping("favicon.ico")
    @ResponseBody
    public void disableFavicon() {
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/{shorturl}")
    public String redirect(@PathVariable(value = "shorturl") @NotNull final String shorturl) {
        final String originalUrl = urlService.getOriginalUrlByShortUrl(shorturl);
        if (originalUrl != null) {
            return "redirect:" + "https://" + originalUrl;
        }
        return "wrong_shortening";
    }

}
