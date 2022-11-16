package com.Zinkworks.PersonalProjectURLShortener.dto;

import lombok.*;

@Getter
@Setter
@Builder
public class ResponseDto<T> {

    private T response;
    private String status;
}
