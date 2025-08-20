package com.kincaps.www.controller;

import jakarta.servlet.http.HttpSession;
import com.kincaps.www.entity.Empleado;
import com.kincaps.www.entity.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/mantenimiento")
    public String showAdminMenu(HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario instanceof Empleado) {
            return "mainmenuadmin";
        } else {
            return "redirect:/gorras";
        }
    }
}