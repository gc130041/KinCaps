package com.kincaps.www.controller;

import com.kincaps.www.service.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.Cliente;
import modelo.Empleado;
import modelo.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private AuthService authService;

    @GetMapping("/login")
    public String showLoginPage(@CookieValue(name = "recuerdame", required = false) String rememberMeCookie,
                                HttpSession session, HttpServletResponse response) {
        if (rememberMeCookie != null) {
            Usuario usuario = authService.validarCookieRecuerdame(rememberMeCookie, response);
            if (usuario != null) {
                session.setAttribute("usuario", usuario);
                return (usuario instanceof Cliente) ? "redirect:/gorras" : "redirect:/mantenimiento";
            }
        }
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam String email,
                               @RequestParam String password,
                               @RequestParam(required = false) String rememberMe,
                               HttpSession session,
                               HttpServletResponse response,
                               Model model) {
        Usuario usuario = authService.verificarCredenciales(email, password);

        if (usuario != null) {
            session.setAttribute("usuario", usuario);
            if ("on".equals(rememberMe)) {
                authService.crearCookieRecuerdame(usuario, response);
            }
            return (usuario instanceof Cliente) ? "redirect:/gorras" : "redirect:/mantenimiento";
        } else {
            model.addAttribute("error", "Correo o contraseña inválidos");
            return "login";
        }
    }

    @GetMapping("/logout")
    public String processLogout(@CookieValue(name = "recuerdame", required = false) String rememberMeCookie,
                                HttpServletResponse response,
                                HttpSession session) {
        if (rememberMeCookie != null) {
            authService.borrarCookieRecuerdame(rememberMeCookie, response);
        }
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/login?logout";
    }
}