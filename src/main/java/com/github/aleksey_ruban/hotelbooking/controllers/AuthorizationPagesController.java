package com.github.aleksey_ruban.hotelbooking.controllers;

import com.github.aleksey_ruban.hotelbooking.entity.AuthorizationToken;
import com.github.aleksey_ruban.hotelbooking.entity.BookingRecord;
import com.github.aleksey_ruban.hotelbooking.entity.Client;
import com.github.aleksey_ruban.hotelbooking.helpers.PhoneNumberHelper;
import com.github.aleksey_ruban.hotelbooking.security.CustomUserDetails;
import com.github.aleksey_ruban.hotelbooking.security.CustomUserDetailsService;
import com.github.aleksey_ruban.hotelbooking.service.AuthorizationTokenService;
import com.github.aleksey_ruban.hotelbooking.service.BookingRecordService;
import com.github.aleksey_ruban.hotelbooking.service.ClientService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

@Controller
@AllArgsConstructor
public class AuthorizationPagesController {

    private final ClientService clientService;
    private final AuthorizationTokenService authorizationTokenService;
    private final BookingRecordService bookingRecordService;

    private AuthenticationManager authenticationManager;
    private CustomUserDetailsService userDetailsService;

    private final PasswordEncoder passwordEncoder;

    @GetMapping({"/signup", "/signup/"})
    public String signup() {
        if (isClientAuthorized()) {
            return "redirect:/account";
        }
        return "authorization/signup";
    }

    @GetMapping({"/signin", "/signin/"})
    public String signin() {
        if (isClientAuthorized()) {
            return "redirect:/account";
        }
        return "authorization/signin";
    }

    @GetMapping({"/account", "/account/"})
    public String account(Model model) {
        Long userId = null;
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof CustomUserDetails userDetails) {
                userId = userDetails.getId();
            }
        }
        if (userId == null) {
            return "authorization/signin";
        }
        Optional<Client> client = clientService.getById(userId);
        if (client.isEmpty()) {
            return "authorization/signin";
        }
        Client client1 = client.get();
        client1.setPhoneNumber(PhoneNumberHelper.prettyPhoneNumber(client1.getPhoneNumber()));
        model.addAttribute("client", client1);

        List<BookingRecord> bookingRecords = bookingRecordService.findByClientId(userId);
        model.addAttribute("bookingRecords", bookingRecords);

        return "authorization/account";
    }

    @RequestMapping(value = {"/account", "/account/"}, method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteAccount() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof CustomUserDetails userDetails) {
                Long userId = userDetails.getId();
                Optional<Client> client = clientService.getById(userId);
                if (client.isEmpty()) {
                    return new ResponseEntity<>("Account not found", HttpStatus.NOT_FOUND);
                }
                clientService.delete(userId);
            }
        }
        return new ResponseEntity<>("Account deleted", HttpStatus.OK);
    }

    @RequestMapping(value = "/send-code", method = RequestMethod.POST)
    public ResponseEntity<String> create(@RequestParam String phoneNumber, @RequestParam String page) {
        if (!PhoneNumberHelper.isValid(phoneNumber)) {
            return ResponseEntity.badRequest().body("Введён некоррентный номер телефона");
        }
        Optional<Client> client = clientService.getByPhoneNumber(PhoneNumberHelper.normalizePhoneNumber(phoneNumber));
        if (client.isPresent()) {
            if (client.get().getName() != null) {
                if (page.equals("signup")) {
                    return ResponseEntity.badRequest().body("Пользователь с этим номером уже зарегистрирован");
                }
            } else {
                if (page.equals("signin")) {
                    return ResponseEntity.badRequest().body("Пользователя с этим номером не существует");
                }
            }

            client.get().setToken(null);
            clientService.deleteAuthorizationToken(client.get());
            authorizationTokenService.createToken(AuthorizationToken
                    .builder()
                    .client(client.get())
                    .build());

        } else {
            if (page.equals("signin")) {
                return ResponseEntity.badRequest().body("Пользователя с этим номером не существует");
            }
            Client newClient = clientService.create(Client
                    .builder()
                    .phoneNumber(PhoneNumberHelper.normalizePhoneNumber(phoneNumber))
                    .build());
            authorizationTokenService.createToken(AuthorizationToken
                    .builder()
                    .client(newClient)
                    .build());
        }
        return ResponseEntity.ok().body("Код отправлен");
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String create(@RequestParam String name, @RequestParam String phoneNumber,
                         @RequestParam String token, @RequestParam Boolean toBooking,
                         HttpServletRequest req, Model model) {
        Optional<Client> optionalClient = clientService
                .getByPhoneNumber(PhoneNumberHelper.normalizePhoneNumber(phoneNumber));

        if (optionalClient.isPresent()) {
            Client existingClient = optionalClient.get();
            AuthorizationToken authorizationToken = existingClient.getToken();
            if (!passwordEncoder.matches(token, authorizationToken.getToken())) {
//                THROW ERROR
                return "redirect:signup";
            }

            UserDetails user = userDetailsService.loadUserByUsername(phoneNumber);

            UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(PhoneNumberHelper.normalizePhoneNumber(phoneNumber), token);
            Authentication auth = authenticationManager.authenticate(authReq);
            SecurityContext sc = SecurityContextHolder.getContext();
            sc.setAuthentication(auth);
            HttpSession session = req.getSession(true);
            session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, sc);

            clientService.deleteAuthorizationToken(existingClient);
            clientService.updateName(existingClient, name);
        } else {
//                THROW ERROR
            return "redirect:signup";
        }

        if (toBooking) {
            model.addAttribute("loadBooking", true);
            return "redirect:/booking?loadBooking=true";
        }
        return "redirect:/account";
    }

    private boolean isClientAuthorized() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            for (GrantedAuthority auth : authentication.getAuthorities()) {
                if (auth.getAuthority().equals("CLIENT") || auth.getAuthority().equals("ADMIN")) {
                    return true;
                }
            }
        }
        return false;
    }
}
