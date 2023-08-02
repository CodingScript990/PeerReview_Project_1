package com.likelion.market.controller;

import com.likelion.market.entity.UserCreateForm;
import com.likelion.market.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class HomeController {

    private final UserService userService;

    @GetMapping
    public String index() {
        return "index";
    }

    @GetMapping("/auth/register")
    public String register(UserCreateForm userCreateForm) {
        return "register";
    }

    @PostMapping("/auth/register")
    public String createUser(@Valid UserCreateForm userCreateForm, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", userCreateForm);
            return "register";
        }

        // 패스워드가 일치하지 않을때
        if (!userCreateForm.getPassword().equals(userCreateForm.getPasswordCheck())) {
            bindingResult.rejectValue("password", "userCreateForm.password", "2개의 패스워드가 일치하지 않습니다.");
            model.addAttribute("formreg", userCreateForm);
            return "register";
        }

        try {
            // 회원가입 시도 시
            userService.create(userCreateForm.getUsername(), userCreateForm.getPassword(), userCreateForm.getEmail(), userCreateForm.getPhone(),  userCreateForm.getAddress());
        } catch (DataIntegrityViolationException err) {
            err.printStackTrace();
            // 이미 사용중인 ID Check
            bindingResult.reject("registerFailed", "이미 등록된 사용자입니다.");
            return "register";
        } catch (Exception err) {
            err.printStackTrace();
            // 에러난다면 에러 메세지를 표시
            bindingResult.reject("registerFailed", err.getMessage());
            return "register";
        }

        // 성공적이면 로그인 페이지로 이동
        return "redirect:/user/login";
    }
}
