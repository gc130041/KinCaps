package com.kincaps.www.service;

import com.kincaps.www.repository.ClienteRepository;
import com.kincaps.www.repository.EmpleadoRepository;
import com.kincaps.www.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EmpleadoRepository empleadoRepository;

    public Usuario findByEmail(String email) {
        return clienteRepository.findByEmail(email)
                .map(Usuario.class::cast)
                .orElseGet(() -> empleadoRepository.findByEmail(email).orElse(null));
    }
}