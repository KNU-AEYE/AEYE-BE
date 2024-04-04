package com.server.aeye.DTO.video.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class VideoListResponseDto {

    private List<VideoResponseDto> videos;
    private Integer totalPage;
}
