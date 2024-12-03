package com.github.aleksey_ruban.hotelbooking.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/", "home").permitAll()

                        .requestMatchers("/signin", "/signin/").permitAll()
                        .requestMatchers("/signup", "/signup/").permitAll()
                        .requestMatchers("/send-code").permitAll()

                        .requestMatchers("/rooms", "/rooms/").permitAll()
                        .requestMatchers("/room-details", "/room-details/").permitAll()
                        .requestMatchers("/room-details/**").permitAll()
                        .requestMatchers("/booking", "/booking/").permitAll()
                        .requestMatchers("/render-available-rooms").permitAll()
                        .requestMatchers("/render-booking-details").permitAll()

                        .requestMatchers("/account", "/account/").hasAnyAuthority("CLIENT", "ADMIN")

//                        .requestMatchers("/record/**").hasAuthority("CLIENT")
                        .requestMatchers("/admin/**").hasAuthority("ADMIN")

                        .requestMatchers("/static/**").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/signin")
                        .permitAll()
                )
                .logout((logout) -> logout
                        .logoutUrl("/logout").permitAll()
                        .logoutSuccessUrl("/")
                        .permitAll()
                )
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }
}
