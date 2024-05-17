package com.server.aeye.DTO.video.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VideoSearchRequestDto {

    private String keyword;
    private String city;
    private String district;
    private String tag;
    private int page = 0;
    private int size = 10;
}
