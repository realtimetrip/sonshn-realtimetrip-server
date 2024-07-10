package com.venti.realtimetrip.domain.user.controller;

import com.venti.realtimetrip.domain.auth.dto.AuthEmailDto;
import com.venti.realtimetrip.domain.auth.service.AuthService;
import com.venti.realtimetrip.domain.user.dto.UserDto;
import com.venti.realtimetrip.domain.user.dto.UserLoginDto;
import com.venti.realtimetrip.domain.user.dto.UserProfileDto;
import com.venti.realtimetrip.domain.user.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final AuthService authService;

    /**
     * 회원가입
     * @param userDto 회원 가입 Dto
     * @return 가입한 회원의 이메일
     */
    @PostMapping("/create-account")
    public String createAccount(@RequestBody UserDto userDto, @RequestParam("code") String code) {

        AuthEmailDto authEmailDto = new AuthEmailDto(userDto.getEmail(), code);

        if(!authService.checkEmailAndCode(authEmailDto)) {
            return"[본인인증 메일]: 오류가 발생하였습니다.";
        } else {
            String email = userService.createAccount(userDto);

            return email;
        }
    }

    /**
     * 로그인
     * @param userLoginDto 로그인 Dto
     * @return
     */
    @PostMapping("/login")
    public String login(@RequestBody UserLoginDto userLoginDto, HttpServletResponse response) {

        String loginUser = userService.login(userLoginDto);

        if(loginUser == null){
            return "일치하는 회원 정보가 존재하지 않습니다.";
        }

        Cookie cookieCode = new Cookie("userCookie", loginUser);
        cookieCode.setMaxAge(7*24*60*60);
        response.addCookie(cookieCode);

        return loginUser;
    }

    /**
     * 유저 프로필 조회
     * @param userId 유저 entity 의 PK
     * @return 유저의 이메일
     */
    @GetMapping("/get-profile/{userId}")
    public String getProfile(@PathVariable(name = "userId") Long userId) {

        UserProfileDto userProfileDto = userService.getProfile(userId);

        return "조회하는 유저의 이메일은 " + userProfileDto.getEmail() + " 입니다.";
    }

}
