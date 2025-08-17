package com.kincaps.www.service;

import com.kincaps.www.repository.CookieAuthRepository;
import com.kincaps.www.security.TokenUtil;
import com.kincaps.www.entity.Cliente;
import com.kincaps.www.entity.CookieAuth;
import com.kincaps.www.entity.Empleado;
import com.kincaps.www.entity.Usuario;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class AuthService {

    @Autowired
    private CookieAuthRepository cookieAuthRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public Usuario validarCookieRecuerdame(String cookieValue, HttpServletResponse response) {
        String[] parts = cookieValue.split(":");
        if (parts.length != 2) {
            borrarCookie(response);
            return null;
        }

        String selector = parts[0];
        String validador = parts[1];

        CookieAuth token = cookieAuthRepository.findBySelector(selector).orElse(null);

        if (token == null || token.getFechaExpiracion().isBefore(LocalDateTime.now())) {
            if (token != null) cookieAuthRepository.delete(token);
            borrarCookie(response);
            return null;
        }

        if (!passwordEncoder.matches(validador, token.getTokenHash())) {
            cookieAuthRepository.delete(token);
            borrarCookie(response);
            return null;
        }

        Usuario usuario = (token.getCliente() != null) ? token.getCliente() : token.getEmpleado();
        if (usuario == null) {
            cookieAuthRepository.delete(token);
            borrarCookie(response);
            return null;
        }

        cookieAuthRepository.delete(token);
        crearCookieRecuerdame(usuario, response);
        return usuario;
    }

    @Transactional
    public void crearCookieRecuerdame(Usuario usuario, HttpServletResponse response) {
        String selector = TokenUtil.generarNuevoToken();
        String validador = TokenUtil.generarNuevoToken();
        String validadorHash = passwordEncoder.encode(validador);

        Cliente cliente = (usuario instanceof Cliente) ? (Cliente) usuario : null;
        Empleado empleado = (usuario instanceof Empleado) ? (Empleado) usuario : null;

        if (cliente == null && empleado == null) return;

        LocalDateTime expiracion = LocalDateTime.now().plusDays(30);
        CookieAuth token = new CookieAuth(selector, validadorHash, cliente, empleado, expiracion);
        cookieAuthRepository.save(token);

        Cookie cookie = new Cookie("recuerdame", selector + ":" + validador);
        cookie.setMaxAge(30 * 24 * 60 * 60);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
    }

    @Transactional
    public void borrarCookieRecuerdame(String cookieValue, HttpServletResponse response) {
        String selector = cookieValue.split(":")[0];
        cookieAuthRepository.deleteBySelector(selector);
        borrarCookie(response);
    }

    private void borrarCookie(HttpServletResponse response) {
        Cookie cookie = new Cookie("recuerdame", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}