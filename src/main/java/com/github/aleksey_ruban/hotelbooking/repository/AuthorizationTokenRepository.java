package com.github.aleksey_ruban.hotelbooking.repository;

import com.github.aleksey_ruban.hotelbooking.entity.AuthorizationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorizationTokenRepository extends JpaRepository<AuthorizationToken, Long> {
}
