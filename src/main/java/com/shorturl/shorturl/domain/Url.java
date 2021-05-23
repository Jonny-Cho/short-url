package com.shorturl.shorturl.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class Url {

    @Id
    @GeneratedValue
    private Long id;

    private String originalUrl;
    private String shortenedUrl;
    private long requesetCount;

    public Url(final String replacedUrl) {
        this.originalUrl = replacedUrl;
        this.requesetCount = 1L;
    }

    public void setShortenedUrl(final String shortenedUrl) {
        this.shortenedUrl = shortenedUrl;
    }

    public void increaseRequestCount() {
        requesetCount++;
    }

}
