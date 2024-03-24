package com.venti.realtimetrip.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    /**
     * 회원 가입
     * @param userDto 회원 가입 Dto
     * @return 가입한 회원의 이메일
     */
    @Transactional
    public String createAccount(UserDto userDto) {

        User user = User.builder()
                .email(userDto.getEmail())
                .nickname(userDto.getNickname())
                .password(userDto.getPassword())
                .build();

        userRepository.save(user);

        return user.getEmail();
    }

    @Transactional(readOnly = true)
    public String login(UserLoginDto userLoginDto) {

        User loginUser = userRepository.findByEmailAndPassword(userLoginDto.getEmail(), userLoginDto.getPassword());

        return loginUser.getEmail();
    }

}
