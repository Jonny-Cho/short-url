package com.shorturl.shorturl.service;

import com.shorturl.shorturl.dto.UrlRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.shorturl.shorturl.service.UrlService.REAL_PREFIX;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class UrlServiceTest {

    @Autowired
    private UrlService urlService;

    @DisplayName("getShortUrl을 호출하면 REAL_PREFIX가 붙은 url이 리턴된다")
    @Test
    public void getShortUrlTest() {
        final String shortUrl = urlService.getShortUrl(new UrlRequestDto("naver.com"));
        assertThat(shortUrl).startsWith(REAL_PREFIX);
    }

    @DisplayName("같은 url을 여러번 shortening해도 같은 shorteningKey가 리턴된다")
    @Test
    public void multipleShortUrlTest() {
        final String shortUrl1 = urlService.getShortUrl(new UrlRequestDto("google.com"));
        final String shortUrl2 = urlService.getShortUrl(new UrlRequestDto("google.com"));
        assertThat(shortUrl1).isEqualTo(shortUrl2);
    }

    @DisplayName("getOriginalUrlByShortUrl을 호출하면 http(s):// 부분을 제외한 url을 리턴한다")
    @Test
    public void getOriginalUrlByShortUrlTest() {
        //given
        final String originalUrl = "https://www.musinsa.com/";
        final String shortUrl = urlService.getShortUrl(new UrlRequestDto(originalUrl));

        //when
        final String originalUrlByShortUrl = urlService.getOriginalUrlByShortUrl(shortUrl.replace(REAL_PREFIX, ""));

        //then
        assertThat(originalUrl.replace("https://", "")).isEqualTo(originalUrlByShortUrl);
    }

}
