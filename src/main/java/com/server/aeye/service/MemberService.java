package com.server.aeye.service;

import com.server.aeye.DTO.member.request.MemberNameRequestDto;
import com.server.aeye.DTO.member.request.MemberPhoneRequestDto;
import com.server.aeye.DTO.member.response.MemberDetailResponseDto;
import com.server.aeye.DTO.member.response.MemberResponseDto;
import com.server.aeye.domain.Member;
import com.server.aeye.infrastructure.MemberRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public MemberResponseDto getMember(String username) {
        Member member = memberRepository.getMemberByOauth2Id(username);
        updateLastActiveTime(member, LocalDateTime.now());
        return MemberResponseDto.toDto(member);
    }

    @Transactional(readOnly = true)
    public MemberDetailResponseDto getMemberDetail(String username) {
        Member member = memberRepository.getMemberByOauth2Id(username);
        updateLastActiveTime(member, LocalDateTime.now());
        return MemberDetailResponseDto.toDto(member);
    }

    @Transactional
    public void updateName(String username, MemberNameRequestDto nameRequestDto) {
        Member member = memberRepository.getMemberByOauth2Id(username);
        member.updateName(nameRequestDto.getName());
        memberRepository.save(member);
    }

    @Transactional
    public void updatePhone(String username, MemberPhoneRequestDto phoneRequestDto) {
        Member member = memberRepository.getMemberByOauth2Id(username);
        member.updatePhone(phoneRequestDto.getPhone());
        memberRepository.save(member);
    }

    @Transactional
    public boolean subscribeDailyReport(String username) {
        Member member = memberRepository.getMemberByOauth2Id(username);
        boolean res = member.subscribeDailyReport();
        memberRepository.save(member);
        return res;
    }

    @Transactional(readOnly = true)
    public List<MemberResponseDto> getOnlineAdmin(String username) {
        Member member = memberRepository.getMemberByOauth2Id(username);
        // 현재 시간으로부터 5분 이전 시간을 계산
        LocalDateTime activeThreshold = LocalDateTime.now().minusMinutes(5);
        // last_active 가 activeThreshold 보다 이후인 사용자들을 조회
        List<Member> onlineAdminList = memberRepository.findAdminMemberByLastActive(activeThreshold);
        return onlineAdminList.stream()
            .map(MemberResponseDto::toDto)
            .collect(Collectors.toList());
    }

    private void updateLastActiveTime(Member member, LocalDateTime lastActiveTime) {
        member.updateLastActive(lastActiveTime);
        memberRepository.save(member);
    }

}
