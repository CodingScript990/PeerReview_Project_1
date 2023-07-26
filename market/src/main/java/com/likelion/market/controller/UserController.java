package com.likelion.market.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/users")
public class UserController {

    /*
        날짜 : 7.26.23
        미션 : 1일차
    */

    // TODO 1. 사용자 회원가입을 진행
    /*
        1. 회원가입에 필요한 정보는 ID/PW가 필수
        2. 부수적으로 전화번호, 이메일, 주소정보를 기입
        3. 이에 필요한 사용자 Entity 는 직접 작성하도록 함
    */

    // 회원가입 페이지[GET => Register Html file]

    @GetMapping("/register")
    public String registerForm() {
        return "register";
    }

    // 회원가입 페이지[POST => Register Html file]
    @PostMapping("/register")
    public String registerPost() {

        return "redirect:/users/login";
    }

    // TODO 2. 아이디 비밀번호를 통해 로그인

    // TODO 3. 아이디 비밀번호를 통해 로그인에 성공하면, JWT 가 발급되고, 소유하고 있을 경우 인증이 필요한 서비스에 접근이 가능
    // 1. 인증이 필요한 서비스는 추후 정의함

    // TODO 4. JWT 를 받은 서비스는 사용자가 누구인지 사용자 Entity 를 기준으로 정확하게 판단할 수 있어야함


}
