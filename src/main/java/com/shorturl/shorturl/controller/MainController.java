package com.shorturl.shorturl.controller;

import com.shorturl.shorturl.service.UrlService;
import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URI;

@Slf4j
@RequiredArgsConstructor
@Controller
public class MainController {

    protected final UrlService urlService;

    @GetMapping("favicon.ico")
    @ResponseBody
    public void disableFavicon() {}

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/{shorturl}")
    public ResponseEntity redirect(@PathVariable(value = "shorturl") @NotNull final String shorturl) {
        final String originalUrl = urlService.getOriginalUrlByShortUrl(shorturl);
        if (originalUrl != null) {
            final HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create("https://" + originalUrl));
            return new ResponseEntity(headers, HttpStatus.MOVED_PERMANENTLY);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

}
