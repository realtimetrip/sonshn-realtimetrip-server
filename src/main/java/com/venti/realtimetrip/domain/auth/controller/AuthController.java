package com.venti.realtimetrip.domain.auth.controller;

import com.venti.realtimetrip.domain.auth.dto.AuthEmailDto;
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

    /**
     * 인증번호 검증 API
     * [POST] /auth/verify-email
     *
     * @return String
     */
    @PostMapping("/verify-email")
    public String sendEmail(@RequestBody AuthEmailDto authEmailDto) {

        boolean isVerified = authService.checkEmailAndCode(authEmailDto);

        if(!isVerified) {
            return "인증번호가 일치하지 않습니다!";
        } else {
            return "인증이 완료되었습니다!";
        }
    }

}
