package com.venti.realtimetrip.domain.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthEmailDto {
    private String email;
    private String code;

    public AuthCode toEntity() {
        return AuthCode.builder()
                .email(this.email)
                .code(this.code)
                .build();
    }
}
