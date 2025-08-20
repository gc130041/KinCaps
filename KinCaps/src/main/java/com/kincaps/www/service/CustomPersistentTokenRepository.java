package com.kincaps.www.service;

import com.kincaps.www.entity.Cliente;
import com.kincaps.www.entity.CookieAuth;
import com.kincaps.www.entity.Empleado;
import com.kincaps.www.entity.Usuario;
import com.kincaps.www.repository.CookieAuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class CustomPersistentTokenRepository implements PersistentTokenRepository {

    @Autowired
    private CookieAuthRepository cookieAuthRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Override
    @Transactional
    public void createNewToken(PersistentRememberMeToken token) {
        Usuario usuario = usuarioService.findByEmail(token.getUsername());
        if (usuario == null) return;

        Cliente cliente = (usuario instanceof Cliente) ? (Cliente) usuario : null;
        Empleado empleado = (usuario instanceof Empleado) ? (Empleado) usuario : null;

        // Usamos el 'series' de Spring como nuestro 'selector' y 'tokenValue' como nuestro 'tokenHash'
        // Spring Security ya se encarga de hashear el tokenValue, por lo que lo guardamos directamente.
        CookieAuth newToken = new CookieAuth(
                token.getSeries(),
                token.getTokenValue(),
                cliente,
                empleado,
                token.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()
        );
        cookieAuthRepository.save(newToken);
    }

    @Override
    @Transactional
    public void updateToken(String series, String tokenValue, Date lastUsed) {
        cookieAuthRepository.findBySelector(series).ifPresent(token -> {
            token.setTokenHash(tokenValue);
            token.setFechaExpiracion(lastUsed.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            cookieAuthRepository.save(token);
        });
    }

    @Override
    @Transactional(readOnly = true)
    public PersistentRememberMeToken getTokenForSeries(String seriesId) {
        return cookieAuthRepository.findBySelector(seriesId)
                .map(token -> new PersistentRememberMeToken(
                        (token.getCliente() != null ? token.getCliente().getEmail() : token.getEmpleado().getEmail()),
                        token.getSelector(),
                        token.getTokenHash(),
                        Date.from(token.getFechaExpiracion().atZone(ZoneId.systemDefault()).toInstant())
                )).orElse(null);
    }

    @Override
    @Transactional
    public void removeUserTokens(String username) {
        Usuario usuario = usuarioService.findByEmail(username);
        if (usuario instanceof Cliente) {
            cookieAuthRepository.deleteByCliente((Cliente) usuario);
        } else if (usuario instanceof Empleado) {
            cookieAuthRepository.deleteByEmpleado((Empleado) usuario);
        }
    }
}