package com.github.aleksey_ruban.hotelbooking.service.impl;

import com.github.aleksey_ruban.hotelbooking.entity.AuthorizationToken;
import com.github.aleksey_ruban.hotelbooking.helpers.RandomGenerator;
import com.github.aleksey_ruban.hotelbooking.repository.AuthorizationTokenRepository;
import com.github.aleksey_ruban.hotelbooking.service.AuthorizationTokenService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationTokenServiceImpl implements AuthorizationTokenService {

    private final AuthorizationTokenRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthorizationTokenServiceImpl(AuthorizationTokenRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AuthorizationToken createToken(AuthorizationToken token) {
        String tokenValue = RandomGenerator.generateClientToken();
        AuthorizationToken newToken = AuthorizationToken.builder()
                .client(token.getClient())
                .token(tokenValue)
                .build();
        newToken.setToken(passwordEncoder.encode(newToken.getToken()));
        return repository.save(newToken);
    }

    @Override
    public AuthorizationToken getByClientPhoneNumber(String clientPhoneNumber) {
        return repository.findByClientPhoneNumber(clientPhoneNumber)
                .orElseThrow(() -> new EntityNotFoundException("AuthorizationToken with ClientPhoneNumber " + clientPhoneNumber + " not found"));
    }

    @Override
    public void deleteToken(Long id) {
        repository.deleteById(id);
    }
}
