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

    public Url(final String replacedUrl) {
        this.originalUrl = replacedUrl;
    }

    public void setShortenedUrl(final String shortenedUrl) {
        this.shortenedUrl = shortenedUrl;
    }

}
