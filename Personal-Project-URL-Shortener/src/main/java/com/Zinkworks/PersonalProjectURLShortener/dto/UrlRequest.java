package com.Zinkworks.PersonalProjectURLShortener.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel(description = "POST method")
public class UrlRequest {

    @ApiModelProperty(required = true, notes = "Original Url to convert")
    private String longUrl;

    @ApiModelProperty(notes = "Expiry of url")
    private Date expiryDate;

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }
}
