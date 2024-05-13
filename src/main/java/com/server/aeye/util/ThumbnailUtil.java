package com.server.aeye.util;

import com.server.aeye.domain.VideoLog;
import com.server.aeye.infrastructure.VideoLogRepository;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.builder.FFmpegBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ThumbnailUtil {

    private final FFmpeg ffmpeg;
    private final FFprobe ffprobe;
    private final VideoLogRepository videoLogRepository;
    private final DataBucketUtil dataBucketUtil;

    @Value("${spring.cloud.gcp.storage.default-thumbnail-uri}")
    private String defaultThumbnailPath;

    private static final String THUMBNAIL_EXTENSION = ".png";

    public void generateThumbnail(VideoLog videoLog) {
        log.info("generate thumbnail: {}", videoLog);

        String outputFilePath = generateOutputFilePath(videoLog.getId());
        log.info("outputFilePath: {}", outputFilePath);
        long time = convertTimeToMilliSeconds(videoLog.getTime().toString());
        try {
            FFmpegBuilder builder = new FFmpegBuilder()
                .setInput(videoLog.getVideo().getVideoUri())
                .overrideOutputFiles(true)
                .addOutput(outputFilePath)
                .setFrames(1)
                .setStartOffset(time, TimeUnit.MILLISECONDS)
                .setVideoCodec("png")
                .setFormat("image2")
                .done();

            ffmpeg.run(builder);
            uploadThumbnail(outputFilePath, videoLog);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("generate thumbnail error: ", e);
            videoLog.updateThumbnailUri(defaultThumbnailPath);
            videoLogRepository.save(videoLog);
        }
    }

    private long convertTimeToMilliSeconds(String time) {
        String[] timeArray = time.split(":");
        long hours = Long.parseLong(timeArray[0]);
        long minutes = Long.parseLong(timeArray[1]);
        long seconds = Long.parseLong(timeArray[2]);
        return (hours * 3600 + minutes * 60 + seconds) * 1000;
    }

    private void uploadThumbnail(String outputFilePath, VideoLog videoLog) throws IOException {
        log.info("uploadThumbnail: {}", videoLog.getId());
        Path path = Paths.get(outputFilePath);
        byte[] fileData = Files.readAllBytes(path);
        String contentType = "image/png";
        String thumbnailUri = dataBucketUtil.uploadThumbnailImage(fileData, contentType);
        videoLog.updateThumbnailUri(thumbnailUri);
        videoLogRepository.save(videoLog);
    }

    private String generateOutputFilePath(Long videoLogId) {
        return "thumbnails/" + videoLogId + "_" + UUID.randomUUID().toString() + THUMBNAIL_EXTENSION;
    }

}
