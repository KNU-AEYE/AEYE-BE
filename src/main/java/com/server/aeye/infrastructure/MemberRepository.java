package com.server.aeye.infrastructure;

import com.server.aeye.domain.Member;
import com.server.aeye.exception.ErrorStatus;
import com.server.aeye.exception.model.NotFoundException;
import com.server.aeye.infrastructure.querydsl.member.MemberRepositoryCustom;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom {

    Optional<Member> findByEmail(String email);
    Optional<Member> findByOauth2Id(String oauth2Id);

    default Member getMemberByEmail(String email) {
        return this.findByEmail(email).orElseThrow(
            () -> new NotFoundException(ErrorStatus.MEMBER_NOT_FOUND,
                ErrorStatus.MEMBER_NOT_FOUND.getMessage())
        );
    }

    default Member getMemberByOauth2Id(String oauth2Id) {
        return this.findByOauth2Id(oauth2Id).orElseThrow(
            () -> new NotFoundException(ErrorStatus.MEMBER_NOT_FOUND,
                ErrorStatus.MEMBER_NOT_FOUND.getMessage())
        );
    }
}
