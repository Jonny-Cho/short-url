package com.shorturl.shorturl;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ShorturlApplication {

    public static final String APPLICATION_LOCATIONS = "spring.config.location=classpath:application.yml,/project/real-application.yml";

    public static void main(String[] args) {
        new SpringApplicationBuilder(ShorturlApplication.class)
                .properties(APPLICATION_LOCATIONS)
                .run(args);
    }

}
