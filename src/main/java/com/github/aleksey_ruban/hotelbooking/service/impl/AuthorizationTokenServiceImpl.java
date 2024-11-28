package com.github.aleksey_ruban.hotelbooking.service.impl;

import com.github.aleksey_ruban.hotelbooking.repository.AuthorizationTokenRepository;
import com.github.aleksey_ruban.hotelbooking.service.AuthorizationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationTokenServiceImpl implements AuthorizationTokenService {

    private final AuthorizationTokenRepository repository;

    @Autowired
    public AuthorizationTokenServiceImpl(AuthorizationTokenRepository repository) {
        this.repository = repository;
    }


}
