package com.server.aeye.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum SuccessStatus {

    /*
     * 200 OK
     */
    OK(HttpStatus.OK, "OK"),
    GET_MEMBER_SUCCESS(HttpStatus.OK, "회원 정보 조회 성공"),
    GET_MEMBER_DETAIL_SUCCESS(HttpStatus.OK, "회원 정보 상세조회 성공"),
    GET_ONLINE_ADMIN_SUCCESS(HttpStatus.OK, "접속중인 관리자 조회 성공"),
    GET_VIDEO_SUCCESS(HttpStatus.OK, "영상 정보 조회 성공"),
    TEAM_SEARCH_SUCCESS(HttpStatus.OK, "팀 검색 성공"),

    /*
     * 201 CREATED
     */
    SIGNUP_SUCCESS(HttpStatus.CREATED, "소셜 로그인 가입 성공"),
    CREATE_TEAM_SUCCESS(HttpStatus.CREATED, "팀 생성 성공"),
    CREATE_VIDEO_SUCCESS(HttpStatus.CREATED, "영상 생성 성공"),

    /*
     * 204 No Content
     */
    NO_CONTENT(HttpStatus.NO_CONTENT, "업데이트 완료"),
    ;

    private final HttpStatus httpStatus;
    private final String message;
}
