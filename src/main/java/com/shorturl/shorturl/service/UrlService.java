package com.shorturl.shorturl.service;

import com.shorturl.shorturl.domain.Url;
import com.shorturl.shorturl.dto.UrlRequestDto;
import com.shorturl.shorturl.exception.EmptyOriginalUrlException;
import com.shorturl.shorturl.repository.UrlRepository;
import com.shorturl.shorturl.util.RandomString;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class UrlService {

    public static final String HTTPS = "https://";
    public static final String HTTP = "http://";
    public static final String EMPTY_STRING = "";
    private final UrlRepository urlRepository;
    private final RandomString randomString;

    @Transactional
    public String generateShortUrl(final UrlRequestDto urlRequest) {
        if(isBlank(urlRequest.getOriginalUrl())) throw new EmptyOriginalUrlException();
        final String replacedUrl = removeHttps(urlRequest.getOriginalUrl());
        final Optional<Url> OptionalUrl = urlRepository.findByOriginalUrl(replacedUrl);
        if (OptionalUrl.isPresent()) {
            final Url url = OptionalUrl.get();
            url.increaseRequestCount();
            return url.getShortenedUrl();
        }
        return newShortenedUrl(replacedUrl);
    }

    public String getOriginalUrlByShortUrl(final String shortUrl) {
        return urlRepository.findByShortenedUrl(shortUrl)
                .map(Url::getOriginalUrl)
                .orElse(null);
    }

    private boolean isBlank(final String originalUrl) {
        final String trimedUrl = originalUrl.trim();
        return trimedUrl.isEmpty();
    }

    private String removeHttps(final String originalUrl) {
        return originalUrl.replace(HTTPS, EMPTY_STRING).replace(HTTP, EMPTY_STRING);
    }

    private String newShortenedUrl(final String replacedUrl) {
        final String shorteningKey = getShorteningKey();
        final Url newUrl = new Url(replacedUrl, shorteningKey);
        final Url savedUrl = urlRepository.save(newUrl);
        return savedUrl.getShortenedUrl();
    }

    private String getShorteningKey() {
        Optional<Url> url;
        String shortenedUrl;
        do {
            shortenedUrl = randomString.nextString();
            url = urlRepository.findByShortenedUrl(shortenedUrl);
        } while (url.isPresent());
        return shortenedUrl;
    }
}
