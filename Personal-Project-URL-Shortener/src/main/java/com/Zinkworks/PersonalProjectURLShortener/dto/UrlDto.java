package com.Zinkworks.PersonalProjectURLShortener.dto;

import com.google.common.hash.Hashing;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.time.LocalDateTime;


@AllArgsConstructor
@Getter
public class UrlDto {

        @Id
        @GeneratedValue(generator = "UUID", strategy = GenerationType.AUTO)
        @Column(name = "id", updatable = false, nullable = false)
        private UUID id;
        private final String shortUrl;
        private final String url;
        private LocalDateTime created;

        public static UrlDto create(UUID id, final String url){
            final String shortUrl = Hashing.murmur3_32().hashString(url, StandardCharsets.UTF_8).toString();
            return new UrlDto(id, shortUrl, url, LocalDateTime.now());
        }
}
