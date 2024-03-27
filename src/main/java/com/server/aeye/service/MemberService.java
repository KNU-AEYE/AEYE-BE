package com.server.aeye.service;

import com.server.aeye.DTO.member.request.MemberRequestDto;
import com.server.aeye.DTO.member.response.MemberDetailResponseDto;
import com.server.aeye.DTO.member.response.MemberResponseDto;
import com.server.aeye.domain.Member;
import com.server.aeye.infrastructure.MemberRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberResponseDto getMember(String username) {
        Member member = memberRepository.getMemberByEmail(username);
        updateLastActiveTime(member, LocalDateTime.now());
        return MemberResponseDto.toEntity(member);
    }

    public MemberDetailResponseDto getMemberDetail(String username) {
        Member member = memberRepository.getMemberByEmail(username);
        return MemberDetailResponseDto.toEntity(member);
    }

    public void updateMember(String username, MemberRequestDto memberRequestDto) {
        Member member = memberRepository.getMemberByEmail(username);
        member.updateMember(memberRequestDto);
        memberRepository.save(member);
    }

    public List<MemberResponseDto> getOnlineAdmin(String username) {
        Member member = memberRepository.getMemberByEmail(username);
        // 현재 시간으로부터 5분 이전 시간을 계산
        LocalDateTime activeThreshold = LocalDateTime.now().minusMinutes(5);
        // last_active 가 activeThreshold 보다 이후인 사용자들을 조회
        List<Member> onlineAdminList = memberRepository.findAdminMemberByLastActive(activeThreshold);
        return onlineAdminList.stream()
            .map(MemberResponseDto::toEntity)
            .collect(Collectors.toList());
    }

    private void updateLastActiveTime(Member member, LocalDateTime lastActiveTime) {
        member.updateLastActive(lastActiveTime);
        memberRepository.save(member);
    }

}
