package com.server.aeye.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorStatus {

    /*
     * 400 Bad Request
     */

    BAD_REQUEST(HttpStatus.BAD_REQUEST, "Bad Request"),


    /*
     * 401 Unauthorized
     */

    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "Invalid JWT Token"),
    OAUTH_UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "OAuth2 Unauthorized"),

    /*
     * 403 Forbidden
     */

    /*
     * 404 Not Found
     */
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 회원입니다."),
    ;
    private final HttpStatus httpStatus;
    private final String message;

    public int getHttpStatusCode() {
        return httpStatus.value();
    }
}
