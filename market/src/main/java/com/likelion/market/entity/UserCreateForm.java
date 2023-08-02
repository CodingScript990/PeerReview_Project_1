package com.likelion.market.entity;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateForm {
    @NotEmpty(message = "사용자 ID는 필수항목 입니다.")
    private String username;

    @NotEmpty(message = "비밀번호는 필수항목 입니다.")
    private String password;

    @NotEmpty(message = "비밀번호 확인은 필수항목 입니다.")
    private String passwordCheck;

    @NotEmpty(message = "이메일은 필수항목 입니다.")
    private String email;

    @NotEmpty(message = "전화번호는 필수항목 입니다.")
    private String phone;

    @NotEmpty(message = "주소는 필수항목 입니다.")
    private String address;
}
