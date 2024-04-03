package com.server.aeye.service;

import com.server.aeye.DTO.video.VideoListResponseDto;
import com.server.aeye.DTO.video.VideoResponseDto;
import com.server.aeye.domain.Member;
import com.server.aeye.infrastructure.MemberRepository;
import com.server.aeye.infrastructure.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VideoService {

    private final MemberRepository memberRepository;
    private final VideoRepository videoRepository;

    public VideoListResponseDto getVideoList(String username, PageRequest pageRequest) {
        Member member = memberRepository.getMemberByOauth2Id(username);
        Page<VideoResponseDto> videoList = videoRepository.findAllByTeam(pageRequest, member.getTeam()).map(VideoResponseDto::toEntity);
        return new VideoListResponseDto(videoList.getContent(), videoList.getTotalPages());
    }

    public VideoResponseDto getVideo(String username, Long videoId) {
        return VideoResponseDto.toEntity(videoRepository.getVideoById(videoId));
    }

}
