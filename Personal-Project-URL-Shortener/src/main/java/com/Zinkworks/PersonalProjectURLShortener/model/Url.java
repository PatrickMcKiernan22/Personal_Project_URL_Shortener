package com.Zinkworks.PersonalProjectURLShortener.model;


import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "Url")
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
public class Url {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "urlId", updatable = false, nullable = false)
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID urlId;

    @Column(name = "short_Url")
    private String shortUrl;

    @Column(name = "long_Url", updatable = true, nullable = false)
    private String longUrl;


    @CreationTimestamp
    @Column(name = "createdOn", updatable = false, nullable = false)
    private LocalDateTime createdOn;

    public Url() {
    }

}
