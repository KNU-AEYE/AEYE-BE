package com.server.aeye.service;

import com.server.aeye.domain.Member;
import com.server.aeye.infrastructure.MemberRepository;
import com.server.aeye.util.SendGridUtil;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final MemberRepository memberRepository;
    private final SendGridUtil sendGridUtil;

    public void sendEmail() throws IOException {
        sendGridUtil.sendEmail();
    }

    public void sendEmail(String username) throws IOException {
        Member member = memberRepository.getMemberByOauth2Id(username);
        sendGridUtil.sendDynamicTemplateEmail(member);
    }
}
