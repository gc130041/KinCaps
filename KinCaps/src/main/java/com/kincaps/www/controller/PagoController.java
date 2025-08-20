package com.kincaps.www.controller;

import com.kincaps.www.service.PagoService;
import jakarta.servlet.http.HttpSession;
import com.kincaps.www.entity.Factura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PagoController {

    @Autowired
    private PagoService pagoService;

    @PostMapping("/procesar-pago")
    public String procesarPago(@RequestParam String metodoPago, HttpSession session, Model model) {
        try {
            Factura factura = pagoService.procesarCompra(session, metodoPago);
            model.addAttribute("facturaId", factura.getIdFactura());
            return "compra-exitosa";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/checkout?error=proceso_fallido";
        }
    }
}