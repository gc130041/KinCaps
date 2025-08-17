package com.kincaps.www.service;

import com.kincaps.www.repository.CookieAuthRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Cliente;
import modelo.CookieAuth;
import modelo.Empleado;
import modelo.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.PasswordHash;
import util.TokenUtil;

import java.time.LocalDateTime;

@Service
public class AuthService {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private CookieAuthRepository cookieAuthRepository;

    @Transactional(readOnly = true)
    public Usuario verificarCredenciales(String email, String password) {
        return usuarioService.verificarCredenciales(email, password);
    }

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
            if (token != null) {
                cookieAuthRepository.delete(token);
            }
            borrarCookie(response);
            return null;
        }

        String validadorHashDB = token.getTokenHash();
        String validadorHashRecibido = PasswordHash.contrasenaHash(validador);

        if (!validadorHashDB.equals(validadorHashRecibido)) {
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
        String validadorHash = PasswordHash.contrasenaHash(validador);

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
    public void borrarCookieRecuerdame(String cookieValue, HttpServletResponse response){
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