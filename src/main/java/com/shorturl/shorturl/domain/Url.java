package com.shorturl.shorturl.domain;

import com.shorturl.shorturl.util.RandomString;
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

    public Url(final String replacedUrl, final String shortenedUrl) {
        this.originalUrl = replacedUrl;
        this.shortenedUrl = shortenedUrl;
        this.requesetCount = 1L;
    }

    public void increaseRequestCount() {
        requesetCount++;
    }

}
