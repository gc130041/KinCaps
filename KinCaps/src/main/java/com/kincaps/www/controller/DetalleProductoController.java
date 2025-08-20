package com.kincaps.www.controller;

import com.kincaps.www.service.ProductoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class DetalleProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping("/gorras/detalle")
    public String mostrarDetalleProducto(@RequestParam int id, Model model, HttpSession session) {
        Map<String, Object> detalleData = productoService.obtenerDetallesProducto(id, session);
        if (detalleData.containsKey("error")) {
            return "producto-no-encontrado";
        }
        model.addAllAttributes(detalleData);
        return "detalle-producto";
    }
}