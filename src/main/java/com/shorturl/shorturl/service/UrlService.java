package com.shorturl.shorturl.service;

import com.shorturl.shorturl.domain.Url;
import com.shorturl.shorturl.dto.UrlRequestDto;
import com.shorturl.shorturl.repository.UrlRepository;
import com.shorturl.shorturl.util.RandomString;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UrlService {

    private final UrlRepository urlRepository;
    private final RandomString randomString;

    @Transactional
    public String generateShortUrl(final UrlRequestDto urlRequest) {
        // TODO validation

        // https, http 없애기
        final String replacedUrl = urlRequest.getOriginalUrl().replace("https://", "").replace("http://", "");

        final Optional<Url> OptionalUrl = urlRepository.findByOriginalUrl(replacedUrl);
        if (OptionalUrl.isPresent()) {
            final Url url = OptionalUrl.get();
            url.increaseRequestCount();
            return url.getShortenedUrl();
        }
        return newShortenedUrl(replacedUrl);
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
        } while (!url.isPresent());
        return shortenedUrl;
    }

    public String getOriginalUrlByShortUrl(final String shortUrl) {
        return urlRepository.findByShortenedUrl(shortUrl)
                .map(Url::getOriginalUrl)
                .orElse(null);
    }
}
