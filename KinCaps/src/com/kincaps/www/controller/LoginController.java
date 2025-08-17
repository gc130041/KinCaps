package com.kincaps.www.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("/logout")
    public String processLogout() {
        // La lógica real está en SecurityConfig
        return "redirect:/login?logout";
    }
}