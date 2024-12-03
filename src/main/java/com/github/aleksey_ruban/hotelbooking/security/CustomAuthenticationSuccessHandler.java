package com.github.aleksey_ruban.hotelbooking.security;

import com.github.aleksey_ruban.hotelbooking.entity.Client;
import com.github.aleksey_ruban.hotelbooking.helpers.PhoneNumberHelper;
import com.github.aleksey_ruban.hotelbooking.service.ClientService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.io.IOException;
import java.util.Optional;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private ClientService clientService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        String username = authentication.getName();

        Optional<Client> optionalClient = clientService.getByPhoneNumber(PhoneNumberHelper.normalizePhoneNumber(username));
        if (optionalClient.isPresent()) {
            Client client = optionalClient.get();
            clientService.deleteAuthorizationToken(client);
        }

        boolean toBooking = Boolean.parseBoolean(request.getParameter("toBooking"));

        if (toBooking) {
            response.sendRedirect("/booking?loadBooking=true");
        } else {
            response.sendRedirect("/account");
        }
    }
}