package com.shorturl.shorturl.service;

import com.shorturl.shorturl.domain.Url;
import com.shorturl.shorturl.dto.UrlRequestDto;
import com.shorturl.shorturl.repository.UrlRepository;
import com.shorturl.shorturl.util.Shortener;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UrlService {

    private final Shortener base62Util;
    private final UrlRepository urlRepository;

    @Transactional
    public String generateShortUrl(final UrlRequestDto urlRequest) {
        // TODO validation

        // https, http 없애기
        final String replacedUrl = urlRequest.getOriginalUrl().replace("https://", "").replace("http://", "");

        final Optional<Url> url = urlRepository.findByOriginalUrl(replacedUrl);
        if (url.isPresent()) return url.get().getShortenedUrl();
        return newShortenedUrl(replacedUrl);
    }

    private String newShortenedUrl(final String replacedUrl) {
        final Url newUrl = new Url(replacedUrl);
        final Url savedUrl = urlRepository.save(newUrl);
        final String shortenedUrl = base62Util.encoding(savedUrl.getId());
        savedUrl.setShortenedUrl(shortenedUrl);
        return shortenedUrl;
    }

    public String getOriginalUrlByShortUrl(final String shortUrl) {
        final Url url = urlRepository.findByShortenedUrl(shortUrl).orElseThrow(() -> new IllegalStateException("ㅇㄴㄴㅇ"));
        return url.getOriginalUrl();
    }
}
