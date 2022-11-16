package com.Zinkworks.PersonalProjectURLShortener.mapper;

import com.Zinkworks.PersonalProjectURLShortener.model.Url;
import com.Zinkworks.PersonalProjectURLShortener.dto.UrlDto;

public class UrlMapper {

    public static Url toUrl(UrlDto urlDto){
        return Url.builder()
                .urlId(urlDto.getId())
                .longUrl(urlDto.getLongUrl())
                .shortUrl(urlDto.getShortUrl())
                .createdOn(urlDto.getCreatedOn())
                .build();
    }

    public static UrlDto toUrlDto(Url url){
        return UrlDto.builder()
                .id(url.getUrlId())
                .longUrl(url.getLongUrl())
                .shortUrl(url.getShortUrl())
                .createdOn(url.getCreatedOn())
                .build();
    }
}
