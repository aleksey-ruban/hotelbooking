package com.github.aleksey_ruban.hotelbooking.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthorizationPagesController {
    @GetMapping({"/signup", "/signup/"})
    public String signup() {
//        if (isClientAuthorized()) {
//            return "redirect:/authorization/account";
//        }
        return "authorization/signup";
    }

    @GetMapping({"/signin", "/signin/"})
    public String signin() {
//        if (isClientAuthorized()) {
//            return "redirect:/authorization/account";
//        }
        return "authorization/signin";
    }

    @GetMapping({"/account", "/account/"})
    public String account(Model model) {
        return "authorization/account";
    }

//    private boolean isClientAuthorized() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication != null && authentication.isAuthenticated()) {
//            for (GrantedAuthority auth : authentication.getAuthorities()) {
//                if (auth.getAuthority().equals("CLIENT") || auth.getAuthority().equals("ADMIN")) {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }

}
