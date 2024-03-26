package com.server.aeye.infrastructure.querydsl.member;

import com.server.aeye.domain.Member;
import java.time.LocalDateTime;
import java.util.List;

public interface MemberRepositoryCustom {

    List<Member> findAdminMemberByLastActive(LocalDateTime activeThreshold);

}
