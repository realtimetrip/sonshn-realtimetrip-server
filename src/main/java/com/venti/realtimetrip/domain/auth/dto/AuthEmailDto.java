package com.venti.realtimetrip.domain.auth.dto;

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
    private String authnum;

    public AuthEmailDto toAuthEmailDto() {
        return AuthEmailDto.builder()
                .email(this.email)
                .authnum(this.authnum)
                .build();
    }
}
