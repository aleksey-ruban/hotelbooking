package com.github.aleksey_ruban.hotelbooking.security;

import com.github.aleksey_ruban.hotelbooking.entity.Client;
import com.github.aleksey_ruban.hotelbooking.helpers.PhoneNumberHelper;
import com.github.aleksey_ruban.hotelbooking.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        Optional<Client> client = clientRepository.findByPhoneNumber(PhoneNumberHelper.normalizePhoneNumber(phoneNumber));
        if (client.isPresent() && client.get().getToken() != null) {
            CustomUserDetails customUserDetails = client.map(CustomUserDetails::new).orElse(null);
            System.out.println(customUserDetails.getAuthorities());
        }
        return client.map(CustomUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
    }
}
