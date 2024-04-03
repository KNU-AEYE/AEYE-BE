package com.server.aeye.domain;

import jakarta.persistence.Id;
import java.time.format.DateTimeFormatter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "video_summary")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VideoSummaryDocument {

    @Id
    @Field(type = FieldType.Long)
    private Long id;

    @Field(type = FieldType.Text)
    private String time;

    @Field(type = FieldType.Text)
    private String content;

    private Long videoId;

    public static VideoSummaryDocument from (VideoSummary videoSummary) {
        return VideoSummaryDocument.builder()
            .id(videoSummary.getId())
            .time(videoSummary.getTime().format(DateTimeFormatter.ofPattern("HH:mm:ss")))
            .content(videoSummary.getContent())
            .videoId(videoSummary.getVideo().getId())
            .build();
    }

}
