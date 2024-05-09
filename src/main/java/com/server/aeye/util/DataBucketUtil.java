package com.server.aeye.util;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import java.io.IOException;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Component
public class DataBucketUtil {

    @Value("${spring.cloud.gcp.storage.bucket}")
    private String bucketName;

    private final Storage storage;

    @Autowired
    public DataBucketUtil(Storage storage) {
        this.storage = storage;
    }

    public String uploadThumbnailImage(MultipartFile file) throws IOException {
        String uuid = UUID.randomUUID().toString();
        String ext = file.getContentType();
        log.info("uuid: {}, ext: {}", uuid, ext);
        BlobInfo blobInfo = BlobInfo.newBuilder(bucketName, uuid)
            .setContentType(ext)
            .build();
        log.info("blobInfo: {}", blobInfo);
        Blob blob = storage.create(blobInfo, file.getBytes());
        log.info("blob: {}", blob);
        return bucketName + "/thumbnail/" + uuid;
    }
}
