package com.Zinkworks.PersonalProjectURLShortener.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@EqualsAndHashCode
public class UrlDto {

    private UUID id;
    private String longUrl;
    private String shortUrl;
    private LocalDateTime createdOn;

}
