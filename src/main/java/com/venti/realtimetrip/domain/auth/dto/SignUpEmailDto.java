package com.venti.realtimetrip.domain.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpEmailDto {
    private String email;

    public SignUpEmailDto toSignUpEmailDto() {
        return SignUpEmailDto.builder()
                .email(this.email)
                .build();
    }
}
