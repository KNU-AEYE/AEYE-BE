package com.server.aeye.infrastructure.querydsl.member;

import static com.server.aeye.domain.QMember.member;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.server.aeye.domain.Member;
import com.server.aeye.enums.Authority;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Member> findAdminMemberByLastActive(LocalDateTime activeThreshold) {
        return queryFactory
            .select(member)
            .from(member)
            .where(member.lastActive.after(activeThreshold))
            .where(member.authority.eq(Authority.ROLE_ADMIN))
            .fetch();
    }
}
