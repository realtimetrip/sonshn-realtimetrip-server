package com.venti.realtimetrip.domain.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<AuthCode, Long> {

    boolean existsByEmailAndCode(String email, String code);

    AuthCode findByEmailOrderByCreatedAtDesc(String email);

}
