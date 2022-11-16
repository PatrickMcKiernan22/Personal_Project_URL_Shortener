package com.Zinkworks.PersonalProjectURLShortener.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Zinkworks.PersonalProjectURLShortener.model.Url;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {

    @Query(value = "SELECT short_Url FROM Url WHERE short_Url = :url", nativeQuery = true)
    String getByShortUrl(@Param("url")String url);

    @Query(value = "SELECT long_Url FROM Url WHERE short_Url = :short_Url", nativeQuery = true)
    String getByLongUrl(@Param("short_Url") String shortUrl);
}
