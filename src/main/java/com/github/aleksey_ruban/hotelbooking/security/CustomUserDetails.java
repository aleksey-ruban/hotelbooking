package com.github.aleksey_ruban.hotelbooking.security;

import com.github.aleksey_ruban.hotelbooking.entity.Client;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@AllArgsConstructor
public class CustomUserDetails implements UserDetails {

    private Client client;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.stream(client.getRoles().split(", "))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    public Long getId() {
        return client.getId();
    }

    @Override
    public String getPassword() {
        return client.getToken().getToken();
    }

    @Override
    public String getUsername() {
        return client.getPhoneNumber();
    }
}
