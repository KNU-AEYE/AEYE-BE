package com.server.aeye.service;

import com.server.aeye.DTO.member.request.MemberRequestDto;
import com.server.aeye.DTO.member.response.MemberDetailResponseDto;
import com.server.aeye.DTO.member.response.MemberResponseDto;
import com.server.aeye.domain.Member;
import com.server.aeye.infrastructure.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberResponseDto getMember(String username) {
        Member member = memberRepository.getMemberByEmail(username);
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

}
