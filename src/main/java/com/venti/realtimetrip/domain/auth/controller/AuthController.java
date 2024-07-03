package com.venti.realtimetrip.domain.auth.controller;

import com.venti.realtimetrip.domain.auth.dto.SignUpEmailDto;
import com.venti.realtimetrip.domain.auth.service.AuthService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    /**
     * 인증번호 전송 API
     * [POST] /auth/send-verification-code
     *
     * @return String
     */
    @PostMapping("/send-verification-code")
    public String sendEmail(@RequestBody SignUpEmailDto signUpEmailDto) throws MessagingException, UnsupportedEncodingException {

        String verificationCode = authService.sendEmail(signUpEmailDto.getEmail());

        return "입력하신 이메일로 인증 코드 " + verificationCode + " 가 전송되었습니다.";
    }

}
