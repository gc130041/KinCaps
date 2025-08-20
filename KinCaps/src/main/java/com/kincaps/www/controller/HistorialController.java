package com.kincaps.www.controller;

import com.kincaps.www.repository.CarritoRepository;
import jakarta.servlet.http.HttpSession;
import com.kincaps.www.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HistorialController {

    @Autowired
    private CarritoRepository carritoRepository;

    @GetMapping("/gorras/historial")
    public String mostrarHistorial(Model model, HttpSession session) {
        Cliente cliente = (Cliente) session.getAttribute("usuario");
        if (cliente == null) {
            return "redirect:/login";
        }

        model.addAttribute("listaPedidos", carritoRepository.findPedidosByClienteIdWithDetalles(cliente.getIdUsuario()));

        return "historialdecompras";
    }
}