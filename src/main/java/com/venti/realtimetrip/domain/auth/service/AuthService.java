package com.venti.realtimetrip.domain.auth.service;

import com.venti.realtimetrip.domain.auth.dto.AuthEmailDto;
import com.venti.realtimetrip.utils.RedisUtils;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthService {

    private final RedisUtils redisUtils;
    private final JavaMailSender javaMailSender;

    private String randomVerificationCode;

    /**
     * createRandomCode
     */
    public void createRandomCode(String email){

        randomVerificationCode = String.valueOf((int)(Math.random() * 8999) + 1000);

        int CODE_EXPIRATION_TIME = 3 * 60 * 1000; // 인증 코드 유효 시간: 3분

        redisUtils.setAuthCodeData("VC(" + email + "):", randomVerificationCode, CODE_EXPIRATION_TIME);

    }

    /**
     * createEmailForm
     *
     * @return MimeMessage -> 인증 메일
     */
    public MimeMessage createEmailForm(String email) throws MessagingException, UnsupportedEncodingException {

        createRandomCode(email);

        String emailReceiver = email; //받는 사람
        String title = "Real Time Trip 본인 인증 번호";
        String messageContext = "회원가입을 위해 이메일 인증을 진행합니다.\n" +
                "인증번호 :" + randomVerificationCode + "\n";

        MimeMessage message = javaMailSender.createMimeMessage();
        message.setFrom(new InternetAddress("sonshumc75@gmail.com", "Real Time Trip")); //발신자 설정
        message.addRecipients(MimeMessage.RecipientType.TO, emailReceiver); //수신자 설정
        message.setSubject(title); //제목 설정
        message.setText(messageContext, "utf-8", "html"); //내용 설정

        return message;
    }

    /**
     * createEmailForm
     * 이메일 전송
     * @return String randomVerificationCode
     */
    public String sendEmail(String emailReceiver) throws MessagingException, UnsupportedEncodingException {

        MimeMessage emailForm = createEmailForm(emailReceiver);
        javaMailSender.send(emailForm);

        return randomVerificationCode;
    }

    /**
     * 회원 가입 -> 이메일 인증 코드가 일치하는 지 확인
     * [GET]
     * @return 일치하는 인증 코드 정보가 존재하면 true, 그렇지 않으면 false
     */
    public boolean checkEmailAndCode(AuthEmailDto authEmailDto) {

        randomVerificationCode = redisUtils.getData("VC("+ authEmailDto.getEmail() + "):");

        return Objects.equals(authEmailDto.getAuthnum(), randomVerificationCode);
    }

}
