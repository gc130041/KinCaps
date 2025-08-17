package com.kincaps.www.service;

import com.kincaps.www.repository.ClienteRepository;
import com.kincaps.www.repository.EmpleadoRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import modelo.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Transactional(readOnly = true)
    public Usuario verificarCredenciales(String email, String password) {
        try {
            StoredProcedureQuery query = em.createStoredProcedureQuery("sp_Login")
                    .registerStoredProcedureParameter("p_email", String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter("p_contrasena", String.class, ParameterMode.IN)
                    .setParameter("p_email", email)
                    .setParameter("p_contrasena", password);

            query.execute();

            Object[] result = (Object[]) query.getSingleResult();
            Integer idUsuario = Integer.parseInt(result[0].toString());
            String tipoUsuario = (String) result[1];

            if ("cliente".equals(tipoUsuario)) {
                return clienteRepository.findById(idUsuario).orElse(null);
            } else if ("empleado".equals(tipoUsuario)) {
                return empleadoRepository.findById(idUsuario).orElse(null);
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}