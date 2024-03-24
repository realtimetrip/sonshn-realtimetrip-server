package com.venti.realtimetrip.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthRepository extends JpaRepository<AuthCode, Long> {

    boolean existsByEmailAndCode(String email, String code);

    AuthCode findByEmailOrderByCreatedAtDesc(String email);

}
