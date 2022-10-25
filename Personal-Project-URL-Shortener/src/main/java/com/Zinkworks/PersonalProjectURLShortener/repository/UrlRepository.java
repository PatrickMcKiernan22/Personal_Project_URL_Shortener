package com.Zinkworks.PersonalProjectURLShortener.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Zinkworks.PersonalProjectURLShortener.model.Url;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {
}
