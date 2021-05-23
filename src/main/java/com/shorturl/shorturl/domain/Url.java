package com.shorturl.shorturl.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Url {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String originalUrl;

    @Column(nullable = false, unique = true)
    private String shortenedUrl;

    @Column(nullable = false)
    private long requesetCount;

    public Url(final String replacedUrl, final String shortenedUrl) {
        this.originalUrl = replacedUrl;
        this.shortenedUrl = shortenedUrl;
        this.requesetCount = 1L;
    }

    public void increaseRequestCount() {
        requesetCount++;
    }

}
