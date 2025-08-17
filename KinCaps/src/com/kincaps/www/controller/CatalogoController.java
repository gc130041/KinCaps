package com.kincaps.www.controller;

import com.kincaps.www.repository.CarritoRepository;
import com.kincaps.www.repository.GorraRepository;
import jakarta.servlet.http.HttpSession;
import modelo.Carrito;
import modelo.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.Collections;

@Controller
public class CatalogoController {

    @Autowired
    private GorraRepository gorraRepository;

    @Autowired
    private CarritoRepository carritoRepository;

    @GetMapping("/gorras/catalogo")
    public String mostrarCatalogo(Model model, HttpSession session) {
        model.addAttribute("listaGorras", gorraRepository.findAll());

        Cliente cliente = (Cliente) session.getAttribute("usuario");
        if (cliente != null) {
            Carrito carritoActivo = carritoRepository.findByClienteAndEstado(cliente, Carrito.Estado.ACTIVO).orElse(null);
            model.addAttribute("listaDetalleCarrito", carritoActivo != null ? carritoActivo.getDetalles() : Collections.emptyList());
        } else {
            model.addAttribute("listaDetalleCarrito", Collections.emptyList());
        }

        return "catalogo";
    }
}