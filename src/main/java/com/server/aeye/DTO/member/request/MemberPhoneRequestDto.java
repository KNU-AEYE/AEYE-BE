package com.server.aeye.DTO.member.request;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class MemberPhoneRequestDto {

    @Pattern(regexp = "^010\\d{8}$", message = "전화번호 형식이 유효하지 않습니다. 010으로 시작하는 11자리 숫자여야 합니다.")
    private String phone;

}
