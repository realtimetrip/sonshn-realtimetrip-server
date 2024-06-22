package com.venti.realtimetrip.domain.auth;

import com.venti.realtimetrip.domain.user.UserDto;
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
     * 본인인증 메일 전송 API
     * [POST] /users/auth/send-email
     *
     * @return String
     */
    @PostMapping("/send-email")
    public String sendEmail(@RequestBody UserDto userDto) throws MessagingException, UnsupportedEncodingException {

        String verificationCode = authService.sendEmail(userDto.getEmail());

        return "입력하신 이메일로 인증 코드" + verificationCode + "가 전송되었습니다.";
    }

}
