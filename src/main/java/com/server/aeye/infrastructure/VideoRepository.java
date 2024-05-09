package com.server.aeye.infrastructure;

import com.server.aeye.domain.Team;
import com.server.aeye.domain.Video;
import com.server.aeye.exception.ErrorStatus;
import com.server.aeye.exception.model.NotFoundException;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {

    List<Video> findByThumbnailUriIsNull();

    Page<Video> findAllByTeam(PageRequest pageRequest, Team team);

    default Video getVideoById(Long id) {
        return this.findById(id).orElseThrow(
            () -> new NotFoundException(ErrorStatus.VIDEO_NOT_FOUND,
                ErrorStatus.VIDEO_NOT_FOUND.getMessage())
        );
    }
}
