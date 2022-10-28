package com.Zinkworks.PersonalProjectURLShortener.service;

import com.Zinkworks.PersonalProjectURLShortener.model.Url;
import com.Zinkworks.PersonalProjectURLShortener.repository.UrlRepository;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class UrlService {

    private UrlRepository urlRepository;

    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

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
            entity.setShortUrl("http://localhost:8040/s/"+randomChar);
            entity.setLongUrl(url.getLongUrl());
            urlRepository.save(entity);

            return entity.getShortUrl();
    }

    public String getLongUrl(Long id) {
        Url result = urlRepository.getReferenceById(id);
        return result.getLongUrl();
    }

    public void getUrls() {
    }
}
