package com.Zinkworks.PersonalProjectURLShortener.service;

import com.Zinkworks.PersonalProjectURLShortener.dto.UrlDto;
import com.Zinkworks.PersonalProjectURLShortener.exception.UrlsNotFoundException;
import com.Zinkworks.PersonalProjectURLShortener.mapper.UrlMapper;
import com.Zinkworks.PersonalProjectURLShortener.model.Url;
import com.Zinkworks.PersonalProjectURLShortener.repository.UrlRepository;
import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static java.util.Objects.isNull;

@Service
public class UrlService implements IUrlService{

    @Autowired
    private UrlRepository urlRepository;


    private String getRandomChars() {
        String random ="";
        String usableChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        for (int i = 0; i < 5; i++)
            random += usableChars.charAt((int) Math.floor(Math.random() * usableChars.length()));
        return random;
    }

    public String convertToShortUrl(Url url){
        var entity = new Url();
        String randomChar = getRandomChars();
        entity.setShortUrl("http://localhost:8040/"+randomChar);
        entity.setLongUrl(url.getLongUrl());
        urlRepository.save(entity);

        return entity.getShortUrl();
    }

    @Override
    public String findShortUrl(String url) {
        return urlRepository.getByShortUrl(url);
    }

    public List<Url> getUrls() {
       return urlRepository.findAll();
    }

    public void deleteUrls() throws UrlsNotFoundException {
        if(urlRepository.findAll().isEmpty()){
            throw new UrlsNotFoundException("Cannot Delete urls - Empty set");
        }
        urlRepository.deleteAll();
    }

    public String findLongUrl(String shortUrl) {
        return urlRepository.getByLongUrl(shortUrl);
    }
}
