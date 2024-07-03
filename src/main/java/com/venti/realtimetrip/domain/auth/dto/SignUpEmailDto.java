package com.venti.realtimetrip.domain.auth.dto;

import com.venti.realtimetrip.domain.auth.entity.AuthCode;
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

    public AuthCode toEntity() {
        return AuthCode.builder()
                .email(this.email)
                .build();
    }
}
