package com.github.aleksey_ruban.hotelbooking.service;

import com.github.aleksey_ruban.hotelbooking.entity.AuthorizationToken;

public interface AuthorizationTokenService {
    AuthorizationToken createToken(AuthorizationToken token);
    AuthorizationToken getByClientPhoneNumber(String clientPhoneNumber);
    void deleteToken(Long id);
}
