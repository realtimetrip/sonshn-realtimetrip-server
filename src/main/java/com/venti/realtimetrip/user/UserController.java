package com.venti.realtimetrip.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    /**
     * 회원가입
     * @param userDto 회원 가입 Dto
     * @return 가입한 회원의 이메일
     */
    @PostMapping("/createAccount")
    public String createAccount(@RequestBody UserDto userDto) {
        String email = userService.createAccount(userDto);

        return email;
    }
}
