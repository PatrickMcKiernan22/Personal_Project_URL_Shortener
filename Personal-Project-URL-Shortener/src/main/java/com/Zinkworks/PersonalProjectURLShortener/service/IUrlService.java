package com.Zinkworks.PersonalProjectURLShortener.service;

import com.Zinkworks.PersonalProjectURLShortener.dto.UrlDto;
import com.Zinkworks.PersonalProjectURLShortener.exception.InvalidUrl;
import com.Zinkworks.PersonalProjectURLShortener.exception.UrlsNotFoundException;
import com.Zinkworks.PersonalProjectURLShortener.model.Url;

import java.util.List;

public interface IUrlService {

    //List<Url> getByShortUrl(String url) throws InvalidUrl;

    List<Url> getUrls();

    void deleteUrls() throws UrlsNotFoundException;
    String findShortUrl(String url);
}
