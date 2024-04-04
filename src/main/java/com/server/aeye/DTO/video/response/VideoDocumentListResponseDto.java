package com.server.aeye.DTO.video.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class VideoDocumentListResponseDto {

    private List<VideoDocumentDto> videoDocuments;
    private Integer totalPage;
}
