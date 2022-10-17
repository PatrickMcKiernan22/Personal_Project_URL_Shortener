package com.Zinkworks.PersonalProjectURLShortener.service;

import com.Zinkworks.PersonalProjectURLShortener.dto.UrlRequest;
import com.Zinkworks.PersonalProjectURLShortener.entity.Url;
import com.Zinkworks.PersonalProjectURLShortener.repository.UrlRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Date;

@Service
public class UrlService {

    private final UrlRepository urlRepository;
    private final BaseConversion conversion;

    public UrlService(UrlRepository urlRepository, BaseConversion baseConversion){
        this.urlRepository = urlRepository;
        this.conversion = baseConversion;
    }

    public String shortenUrl(UrlRequest request) {
        var url = new Url();
        url.setLongUrl(request.getLongUrl());
        url.setExpiryDate(request.getExpiryDate());
        url.setCreatedDate(new Date());
    }
}
