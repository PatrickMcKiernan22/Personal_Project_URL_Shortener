package com.Zinkworks.PersonalProjectURLShortener.controller;

import com.Zinkworks.PersonalProjectURLShortener.dto.UrlDto;
import com.Zinkworks.PersonalProjectURLShortener.exception.UrlError;
import lombok.extern.slf4j.*;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping(value = "/urlShortener")
public class UrlController {

    @Autowired
    private RedisTemplate<String, UrlDto> redisTemplate;

    @Value("${redis.ttl}")
    private long ttl;

    @PostMapping
    public ResponseEntity create(@RequestBody final String url){
        final UrlValidator urlValidator = new UrlValidator(new String[]{"http", "https"});
        if (!urlValidator.isValid(url)){
            return ResponseEntity.badRequest().body(new UrlError("Invalid Url."));
        }

        final UrlDto urlDto = UrlDto.create(UUID.randomUUID(), url);
        log.info("Short URl has been generated = {}", urlDto.getShortUrl());
        redisTemplate.opsForValue().set(urlDto.getShortUrl(), urlDto, ttl, TimeUnit.SECONDS);
        return ResponseEntity.noContent().header("ID", urlDto.getShortUrl()).build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity getSingleUrl(@PathVariable UUID id){
        final UrlDto urlDto = redisTemplate.opsForValue().get(id);
        if(Objects.isNull(urlDto)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new UrlError("No such key exists."));
        } else{
            log.info("Url retrieved = {}", urlDto.getId());
        }

        return  ResponseEntity.ok(urlDto);
    }
}
