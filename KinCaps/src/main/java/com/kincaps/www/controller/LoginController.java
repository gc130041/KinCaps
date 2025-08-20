package com.kincaps.www.controller;

import com.kincaps.www.entity.Cliente;
import com.kincaps.www.entity.Empleado;
import com.kincaps.www.entity.Usuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String showLoginPage(HttpSession session) {

        Usuario usuarioEnSesion = (Usuario) session.getAttribute("usuario");

        if (usuarioEnSesion != null) {
            if (usuarioEnSesion instanceof Cliente) {
                return "redirect:/gorras";
            } else if (usuarioEnSesion instanceof Empleado) {
                return "redirect:/mantenimiento";
            }
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && !"anonymousUser".equals(authentication.getPrincipal())) {
            if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
                return "redirect:/mantenimiento";
            } else {
                return "redirect:/gorras";
            }
        }

        return "login";
    }
}