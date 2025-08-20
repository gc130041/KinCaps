package com.kincaps.www.service;

import com.kincaps.www.entity.Cliente;
import com.kincaps.www.entity.Empleado;
import com.kincaps.www.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioService.findByEmail(email);

        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado con el email: " + email);
        }

        Set<GrantedAuthority> authorities = new HashSet<>();
        if (usuario instanceof Cliente) {
            authorities.add(new SimpleGrantedAuthority("ROLE_CLIENTE"));
        } else if (usuario instanceof Empleado) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }

        return new User(usuario.getEmail(), usuario.getContrasenaHash(), authorities);
    }
}