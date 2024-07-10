package com.venti.realtimetrip.domain.user.service;

import com.venti.realtimetrip.domain.user.dto.UserDto;
import com.venti.realtimetrip.domain.user.dto.UserLoginDto;
import com.venti.realtimetrip.domain.user.dto.UserProfileDto;
import com.venti.realtimetrip.domain.user.entity.User;
import com.venti.realtimetrip.domain.user.repository.UserRepository;
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

    /**
     * 유저 프로필 조회
     * @param userId 유저 entity 의 PK
     * @return 유저의 이메일
     */
    @Transactional(readOnly = true)
    public UserProfileDto getProfile(Long userId) {

        User user = userRepository.findByUserId(userId);

        UserProfileDto userProfileDto = UserProfileDto.builder()
                .userId(user.getUserId())
                .email(user.getEmail())
                .nickname(user.getNickname())
                .profile(user.getProfile())
                .build();

        return userProfileDto;
    }

}
