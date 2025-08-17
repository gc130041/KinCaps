package com.kincaps.www.controller;

import com.kincaps.www.repository.ClienteRepository;
import com.kincaps.www.repository.GorraRepository;
import jakarta.servlet.http.HttpSession;
import modelo.Cliente;
import modelo.Gorras;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class PageController {

    @Autowired
    private GorraRepository gorraRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @ModelAttribute
    public void addCommonAttributes(Model model, HttpSession session) {
        Cliente clienteEnSesion = (Cliente) session.getAttribute("usuario");
        if (clienteEnSesion != null) {
            clienteRepository.findById(clienteEnSesion.getIdCliente())
                    .ifPresent(clienteCompleto -> model.addAttribute("usuarioCompleto", clienteCompleto));
        }
    }

    @GetMapping(value = {"/gorras", "/"})
    public String showMainMenu(Model model) {
        List<Gorras> gorrasDestacadas = gorraRepository.findMasVendidas(4);
        if (gorrasDestacadas.size() < 4) {
            gorrasDestacadas = gorraRepository.findAleatorias(4);
        }
        model.addAttribute("gorrasDestacadas", gorrasDestacadas);
        return "mainmenu";
    }

    @GetMapping("/gorras/mision")
    public String showMisionPage() {
        return "misionvision";
    }

    @GetMapping("/gorras/contactanos")
    public String showContactanosPage() {
        return "contactanos";
    }
}