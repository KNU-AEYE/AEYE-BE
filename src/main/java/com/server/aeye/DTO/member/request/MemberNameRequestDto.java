package com.server.aeye.DTO.member.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class MemberNameRequestDto {

    @NotBlank
    private String name;

}
