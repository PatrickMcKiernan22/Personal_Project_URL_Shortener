package com.Zinkworks.PersonalProjectURLShortener.controller;

import com.Zinkworks.PersonalProjectURLShortener.dto.UrlRequest;
import com.Zinkworks.PersonalProjectURLShortener.service.UrlService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/url")
public class UrlController {

    private final UrlService urlService;

    public UrlController(UrlService urlService){
        this.urlService = urlService;
    }

    @ApiOperation(value = "Converts a new Url that is entered", notes = "Shortens Url")
    @PostMapping("convert-to-short")
    public String shortenUrl(@RequestBody UrlRequest request){
        return urlService.shortenUrl(request);
    }

    @ApiOperation(value = "Redirect", notes = "finds original Url")
    @GetMapping(value = "{shortUrl}")
    @Cacheable(value = "urls", key = "#shorturl", sync = true)
    public ResponseEntity<Void> getandRedirect(@PathVariable String shortUrl){
        var url = urlService.getOriginalUrl(shortUrl);
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(url))
                .build();
    }

}
