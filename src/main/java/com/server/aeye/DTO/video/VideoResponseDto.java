package com.server.aeye.DTO.video;

import com.server.aeye.domain.Video;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class VideoResponseDto {

    private Long id;
    private String title;
    private String content;
    private String thumbnailUri;
    private String videoUri;
    private LocalDateTime createdAt;

    public static VideoResponseDto toEntity(Video video) {
        return VideoResponseDto.builder()
            .id(video.getId())
            .title(video.getTitle())
            .content(video.getDescription())
            .thumbnailUri(video.getThumbnailUri())
            .videoUri(video.getVideoUri())
            .createdAt(video.getCreatedAt())
            .build();
    }

}
