package com.server.aeye.DTO.video.response;

import com.server.aeye.domain.VideoLogDocument;
import com.server.aeye.domain.VideoSummaryDocument;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class VideoDocumentDto {

    private Long id;
    private String content;
    private String time;
    private Long videoId;

    public static VideoDocumentDto toDto(VideoLogDocument videoDocument) {
        return VideoDocumentDto.builder()
            .id(videoDocument.getId())
            .content(videoDocument.getContent())
            .time(videoDocument.getTime())
            .videoId(videoDocument.getVideo_id())
            .build();
    }

    public static VideoDocumentDto toDto(VideoSummaryDocument videoDocument) {
        return VideoDocumentDto.builder()
            .id(videoDocument.getId())
            .content(videoDocument.getContent())
            .time(videoDocument.getTime())
            .videoId(videoDocument.getVideo_id())
            .build();
    }
}
