package com.github.aleksey_ruban.hotelbooking.repository;

import com.github.aleksey_ruban.hotelbooking.entity.AuthorizationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorizationTokenRepository extends JpaRepository<AuthorizationToken, Long> {
    @Query("SELECT t FROM AuthorizationToken t WHERE t.client.phoneNumber = :clientPhoneNumber")
    AuthorizationToken findByClientPhoneNumber(@Param("clientPhoneNumber") String clientPhoneNumber);
}
