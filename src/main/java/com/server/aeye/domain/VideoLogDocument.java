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

@Document(indexName = "video_log")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VideoLogDocument {

    @Id
    @Field(type = FieldType.Long)
    private Long id;

    @Field(type = FieldType.Text)
    private String time;

    @Field(type = FieldType.Text)
    private String content;

    private Long video_id;

    public static VideoLogDocument of (VideoLog videoLog) {
        return VideoLogDocument.builder()
            .id(videoLog.getId())
            .time(videoLog.getTime().format(DateTimeFormatter.ofPattern("HH:mm:ss")))
            .content(videoLog.getContent())
            .video_id(videoLog.getVideo().getId())
            .build();
    }

}
