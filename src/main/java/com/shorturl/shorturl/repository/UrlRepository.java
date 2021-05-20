package com.shorturl.shorturl.repository;

import com.shorturl.shorturl.domain.Url;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UrlRepository extends JpaRepository<Url, Long> {
    Optional<Url> findByOriginalUrl(final String originalUrl);

    Optional<Url> findByShortenedUrl(final String shortenedUrl);
}
