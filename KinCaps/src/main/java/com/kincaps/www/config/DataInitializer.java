package com.kincaps.www.config;

import com.kincaps.www.entity.Empleado;
import com.kincaps.www.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.time.LocalDate;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        String adminEmail = "admin@kincaps.com";

        if (!empleadoRepository.existsByEmail(adminEmail)) {

            Empleado admin = new Empleado();
            admin.setNombre("Admin");
            admin.setApellido("KinCaps");
            admin.setEmail(adminEmail);
            admin.setTelefono("1234-5678");
            admin.setDireccion("KinCaps Incorporation");
            admin.setPuesto("Administrador General");
            admin.setFechaContratacion(LocalDate.of(2024, 6, 15));

            admin.setContrasenaHash(passwordEncoder.encode("admin123"));

            empleadoRepository.save(admin);

            System.out.println(">>> Creado usuario administrador por defecto: " + adminEmail);
        } else {
            System.out.println(">>> El usuario administrador ya existe. No se realizaron cambios.");
        }
    }
}