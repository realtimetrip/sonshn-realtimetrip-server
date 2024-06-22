package com.venti.realtimetrip.domain.auth.repository;

import com.venti.realtimetrip.domain.auth.entity.AuthCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<AuthCode, Long> {

    boolean existsByEmailAndCode(String email, String code);

    AuthCode findByEmailOrderByCreatedAtDesc(String email);

}
