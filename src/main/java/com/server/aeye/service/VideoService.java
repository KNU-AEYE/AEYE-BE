package com.server.aeye.service;

import com.server.aeye.DTO.video.request.VideoRequestDto;
import com.server.aeye.DTO.video.request.VideoSearchRequestDto;
import com.server.aeye.DTO.video.response.VideoDocumentDto;
import com.server.aeye.DTO.video.response.VideoDocumentListResponseDto;
import com.server.aeye.DTO.video.response.VideoListResponseDto;
import com.server.aeye.DTO.video.response.VideoResponseDto;
import com.server.aeye.domain.Member;
import com.server.aeye.domain.Team;
import com.server.aeye.domain.Video;
import com.server.aeye.domain.VideoLogDocument;
import com.server.aeye.infrastructure.MemberRepository;
import com.server.aeye.infrastructure.TeamRepository;
import com.server.aeye.infrastructure.VideoLogRepository;
import com.server.aeye.infrastructure.VideoRepository;
import com.server.aeye.infrastructure.elasticsearch.VideoLogDocumentRepository;
import com.server.aeye.infrastructure.elasticsearch.VideoSummaryDocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class VideoService {

    private final MemberRepository memberRepository;
    private final VideoRepository videoRepository;
    private final VideoLogRepository videoLogRepository;
    private final VideoLogDocumentRepository videoLogDocumentRepository;
    private final VideoSummaryDocumentRepository videoSummaryDocumentRepository;
    private final TeamRepository teamRepository;

    @Transactional(readOnly = true)
    public VideoListResponseDto getVideoList(String username, PageRequest pageRequest) {
        Member member = memberRepository.getMemberByOauth2Id(username);
        Page<VideoResponseDto> videoList = videoRepository.findAllByTeam(pageRequest, member.getTeam()).map(VideoResponseDto::toDto);
        return new VideoListResponseDto(videoList.getContent(), videoList.getTotalPages());
    }

    @Transactional(readOnly = true)
    public VideoResponseDto getVideo(String username, Long videoId) {
        return VideoResponseDto.toDto(videoRepository.getVideoById(videoId));
    }

    @Transactional(readOnly = true)
    public VideoDocumentListResponseDto searchVideo(String username, String keyword, PageRequest pageRequest) {
        Member member = memberRepository.getMemberByOauth2Id(username);
        Page<VideoDocumentDto> videoList = videoLogDocumentRepository.findByContent(pageRequest, keyword).map(
            VideoDocumentDto::toDto);
        return new VideoDocumentListResponseDto(videoList.getContent(), videoList.getTotalPages());
    }

    // v1
    @Transactional(readOnly = true)
    public VideoDocumentListResponseDto searchVideoLog1(String keyword, PageRequest pageRequest) {
        Page<VideoDocumentDto> videoList = videoLogRepository.searchVideoLog1(keyword, pageRequest).map(VideoDocumentDto::toDto);
        return new VideoDocumentListResponseDto(videoList.getContent(), videoList.getTotalPages());
    }

    // v2
    @Transactional(readOnly = true)
    public VideoDocumentListResponseDto searchVideoLog2(VideoSearchRequestDto videoSearchRequestDto) {
        PageRequest pageRequest = PageRequest.of(videoSearchRequestDto.getPage(), videoSearchRequestDto.getSize());
        Page<VideoDocumentDto> videoList = videoLogRepository.searchVideoLog2(videoSearchRequestDto, pageRequest).map(VideoDocumentDto::toDto);
        return new VideoDocumentListResponseDto(videoList.getContent(), videoList.getTotalPages());
    }

    @Transactional(readOnly = true)
    public VideoDocumentListResponseDto searchVideoSummary(String keyword, PageRequest pageRequest) {
        Page<VideoDocumentDto> videoList = videoLogRepository.searchVideoSummary(keyword, pageRequest).map(VideoDocumentDto::toDto);
        return new VideoDocumentListResponseDto(videoList.getContent(), videoList.getTotalPages());
    }

    @Transactional
    public void uploadVideo(VideoRequestDto videoRequestDto) {
        Team team = teamRepository.findById(1L).orElseThrow();
        Video video = Video.builder()
            .title(videoRequestDto.getTitle())
            .description(videoRequestDto.getDescription())
            .videoUri(videoRequestDto.getVideoUri())
            .thumbnailUri(videoRequestDto.getThumbnailUri())
            .team(team)
            .city(videoRequestDto.getCity())
            .district(videoRequestDto.getDistrict())
            .build();
        videoRepository.save(video);
    }
}
