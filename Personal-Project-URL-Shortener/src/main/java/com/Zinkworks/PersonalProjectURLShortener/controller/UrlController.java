package com.Zinkworks.PersonalProjectURLShortener.controller;

import com.Zinkworks.PersonalProjectURLShortener.service.UrlService;
import com.Zinkworks.PersonalProjectURLShortener.repository.UrlRepository;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.MalformedURLException;

import com.Zinkworks.PersonalProjectURLShortener.model.Url;

@RestController
public class UrlController {

    UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @RequestMapping(value = "/shortenurl", method = RequestMethod.POST)
    public String create(@RequestBody Url shortenUrl) throws MalformedURLException {
        return urlService.convertToShortUrl(shortenUrl);

    }

    @RequestMapping(value = "/s/{id}", method = RequestMethod.GET)
    public void getSingleUrl(HttpServletResponse response, @PathVariable("id") String id) throws IOException {
       response.sendRedirect(urlService.getLongUrl(Long.valueOf(id)));
    }
}
