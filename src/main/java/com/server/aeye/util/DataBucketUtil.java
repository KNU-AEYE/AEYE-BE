package com.server.aeye.util;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataBucketUtil {

    @Value("${spring.cloud.gcp.storage.bucket}")
    private String bucketName;

    private final Storage storage;

    public String uploadThumbnailImage(byte[] fileData, String contentType) throws IOException {
        String uuid = UUID.randomUUID().toString();
        log.info("uuid: {}", uuid);
        BlobInfo blobInfo = BlobInfo.newBuilder(bucketName, "thumbnail/" + uuid + ".png")
            .setContentType(contentType)
            .build();
        log.info("blobInfo: {}", blobInfo);
        Blob blob = storage.create(blobInfo, fileData);
        log.info("blob: {}", blob);
        return bucketName + "/thumbnail/" + uuid + ".png";
    }

    public String uploadDailyReport(byte[] fileData, String contentType) throws IOException {
        String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String fileName = "report/" + date + "_report.pdf";
        log.info("fileName: {}", fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(bucketName, fileName)
            .setContentType(contentType)
            .build();
        log.info("blobInfo: {}", blobInfo);
        Blob blob = storage.create(blobInfo, fileData);
        return "https://storage.googleapis.com/" + bucketName + "/" + fileName;
    }
}
