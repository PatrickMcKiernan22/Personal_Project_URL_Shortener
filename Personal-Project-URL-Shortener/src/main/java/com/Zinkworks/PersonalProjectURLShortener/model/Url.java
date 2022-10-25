package com.Zinkworks.PersonalProjectURLShortener.model;

import javax.persistence.*;

@Entity
@Table(name = "Url")
public class Url {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long urlId;

    @Column(name = "shortUrl")
    private String shortUrl;

    @Column(name = "longUrl")
    private String longUrl;

    public Url(long urlId, String shortUrl, String longUrl) {
        this.urlId = urlId;
        this.shortUrl = shortUrl;
        this.longUrl = longUrl;
    }

    public Url(){}

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    public long getUrlId() {
        return urlId;
    }

    @Override
    public String toString() {
        return "Url [shortUrl=" + shortUrl + ", longUrl=" + longUrl + "]";
    }
}
