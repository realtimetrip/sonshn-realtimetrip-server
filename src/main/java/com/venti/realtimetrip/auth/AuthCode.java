package com.venti.realtimetrip.auth;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "AuthCode")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthCode {

    @Id
    private String email;

    private String code;

    private LocalDateTime createdAt;

}
