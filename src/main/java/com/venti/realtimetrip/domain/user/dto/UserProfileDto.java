package com.venti.realtimetrip.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileDto {
    private Long userId;
    private String email;
    private String nickname;
    private String profile;

    public UserProfileDto toUserProfileDto() {
        return UserProfileDto.builder()
                .email(this.email)
                .nickname(this.nickname)
                .profile(this.profile)
                .build();
    }
}
