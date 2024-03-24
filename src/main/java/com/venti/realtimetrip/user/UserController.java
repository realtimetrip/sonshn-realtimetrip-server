package com.venti.realtimetrip.user;

import com.venti.realtimetrip.auth.AuthEmailDto;
import com.venti.realtimetrip.auth.AuthService;
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
}
