package com.kincaps.www.controller;

import com.kincaps.www.service.CarritoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

    @PostMapping("/carrito/agregar")
    public ResponseEntity<Map<String, Object>> agregarAlCarrito(@RequestParam int idGorra,
                                                                @RequestParam int cantidad,
                                                                HttpSession session) {
        try {
            Map<String, Object> response = carritoService.agregarProducto(idGorra, cantidad, session);
            return ResponseEntity.ok(response);
        } catch (IllegalStateException | IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("status", "error", "message", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("status", "error", "message", "Error interno del servidor."));
        }
    }

    @PostMapping("/carrito/eliminar")
    public ResponseEntity<Map<String, Object>> eliminarDelCarrito(@RequestParam int idGorra,
                                                                  @RequestParam int cantidad,
                                                                  HttpSession session) {
        try {
            Map<String, Object> response = carritoService.eliminarProducto(idGorra, cantidad, session);
            return ResponseEntity.ok(response);
        } catch (IllegalStateException | IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("status", "error", "message", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("status", "error", "message", "Error interno del servidor."));
        }
    }
}