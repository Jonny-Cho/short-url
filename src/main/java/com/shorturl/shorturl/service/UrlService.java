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
        final Url newUrl = new Url(replacedUrl, randomString.nextString());
        final Url savedUrl = urlRepository.save(newUrl);
        return savedUrl.getShortenedUrl();
    }

    public String getOriginalUrlByShortUrl(final String shortUrl) {
        final Url url = urlRepository.findByShortenedUrl(shortUrl).orElseThrow(() -> new IllegalStateException("DB에서 Shortening Key를 찾을 수 없습니다."));
        return url.getOriginalUrl();
    }
}
