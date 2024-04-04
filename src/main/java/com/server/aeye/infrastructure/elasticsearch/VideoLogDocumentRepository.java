package com.server.aeye.infrastructure.elasticsearch;

import com.server.aeye.domain.VideoLogDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface VideoLogDocumentRepository extends ElasticsearchRepository<VideoLogDocument, Long> {

    Page<VideoLogDocument> findByContent(PageRequest pageRequest, String content);

}
