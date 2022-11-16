package com.Zinkworks.PersonalProjectURLShortener.controller;

import com.Zinkworks.PersonalProjectURLShortener.dto.ResponseDto;
import com.Zinkworks.PersonalProjectURLShortener.dto.UrlDto;
import com.Zinkworks.PersonalProjectURLShortener.exception.InvalidUrl;
import com.Zinkworks.PersonalProjectURLShortener.exception.UrlsNotFoundException;
import com.Zinkworks.PersonalProjectURLShortener.service.UrlService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

import com.Zinkworks.PersonalProjectURLShortener.model.Url;

@RestController
@RequestMapping(path = "/url")
public class UrlController {

    UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }


    @PostMapping
    public String create(@RequestBody Url shortenUrl) throws MalformedURLException {
        return urlService.convertToShortUrl(shortenUrl);

    }


    @GetMapping("/{shortUrl}")
    public ResponseEntity<ResponseDto> shortUrlRedirect(@PathVariable String shortUrl){
        try{
            URI redirect = new URI(urlService.findLongUrl(shortUrl));
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(redirect);

            return new ResponseEntity<>(
                    ResponseDto.builder()
                            .status(String.valueOf(HttpStatus.SEE_OTHER))
                            .response("Redirecting to url Link").build(), headers, HttpStatus.SEE_OTHER);
        } catch (Exception e) {
            return new ResponseEntity<>(
                    ResponseDto.builder()
                            .status(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR))
                            .response(e.getMessage()).build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

  @GetMapping("/all")
  public ResponseEntity<ResponseDto> getAllUrls(){
        try{
            return new ResponseEntity<>(
              ResponseDto.builder()
                      .status(String.valueOf(HttpStatus.OK))
                      .response(urlService.getUrls()).build(), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(
                    ResponseDto.builder()
                            .status(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR))
                            .response(e.getMessage()).build(), HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
  }

    @DeleteMapping("/all")
    public void deleteUrl() throws UrlsNotFoundException {
        urlService.deleteUrls();
    }
}
