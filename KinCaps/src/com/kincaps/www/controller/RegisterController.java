package com.kincaps.www.controller;

import com.kincaps.www.repository.ClienteRepository;
import com.kincaps.www.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegisterController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }

    @PostMapping("/register")
    public String processRegistration(@RequestParam String name,
                                      @RequestParam String lastname,
                                      @RequestParam String email,
                                      @RequestParam String telefono,
                                      @RequestParam String ubicacion,
                                      @RequestParam String password,
                                      @RequestParam String confirmarpassword,
                                      RedirectAttributes redirectAttributes) {

        if (isCampoVacio(name) || isCampoVacio(lastname) || isCampoVacio(email) || isCampoVacio(telefono) ||
                isCampoVacio(ubicacion) || isCampoVacio(password) || isCampoVacio(confirmarpassword)) {
            return handleRegistrationError("Todos los campos son obligatorios.", name, lastname, email, telefono, ubicacion, redirectAttributes);
        }

        if (!password.equals(confirmarpassword)) {
            return handleRegistrationError("Las contraseñas no coinciden.", name, lastname, email, telefono, ubicacion, redirectAttributes);
        }

        if (clienteRepository.existsByEmail(email)) {
            return handleRegistrationError("El correo electrónico ya está registrado.", name, lastname, "", telefono, ubicacion, redirectAttributes);
        }

        String hashPassword = passwordEncoder.encode(password);
        Cliente nuevoCliente = new Cliente(name, lastname, email, telefono, ubicacion, hashPassword, Cliente.Estado.ACTIVO);
        clienteRepository.save(nuevoCliente);

        return "redirect:/login?registro=exito";
    }

    private boolean isCampoVacio(String campo) {
        return campo == null || campo.trim().isEmpty();
    }

    private String handleRegistrationError(String errorMessage, String name, String lastname, String email, String telefono,
                                           String ubicacion, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("error", errorMessage);
        redirectAttributes.addFlashAttribute("nameValue", name);
        redirectAttributes.addFlashAttribute("lastnameValue", lastname);
        redirectAttributes.addFlashAttribute("emailValue", email);
        redirectAttributes.addFlashAttribute("telefonoValue", telefono);
        redirectAttributes.addFlashAttribute("ubicacionValue", ubicacion);
        return "redirect:/register";
    }
}