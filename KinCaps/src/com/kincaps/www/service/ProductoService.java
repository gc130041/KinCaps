package com.kincaps.www.service;

import com.kincaps.www.repository.CarritoRepository;
import com.kincaps.www.repository.GorraRepository;
import jakarta.servlet.http.HttpSession;
import modelo.Carrito;
import modelo.Cliente;
import modelo.DetalleCarrito;
import modelo.Gorras;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class ProductoService {

    @Autowired private GorraRepository gorraRepository;
    @Autowired private CarritoRepository carritoRepository;

    @Transactional(readOnly = true)
    public Map<String, Object> obtenerDetallesProducto(int idGorra, HttpSession session) {
        Gorras gorra = gorraRepository.findById(idGorra).orElse(null);
        if (gorra == null) {
            return Map.of("error", "Producto no encontrado");
        }

        int cantidadEnCarrito = 0;
        Carrito carritoActivo = null;
        Cliente cliente = (Cliente) session.getAttribute("usuario");

        if (cliente != null) {
            carritoActivo = carritoRepository.findByClienteAndEstado(cliente, Carrito.Estado.ACTIVO).orElse(null);
            if (carritoActivo != null) {
                cantidadEnCarrito = carritoActivo.getDetalles().stream()
                        .filter(detalle -> detalle.getGorra().getIdGorra() == idGorra)
                        .mapToInt(DetalleCarrito::getCantidad)
                        .sum();
            }
        }

        int stockDisponible = gorra.getStock() - cantidadEnCarrito;

        Map<String, Object> model = new HashMap<>();
        model.put("gorra", gorra);
        model.put("stockDisponibleParaAgregar", stockDisponible);
        model.put("listaDetalleCarrito", carritoActivo != null ? carritoActivo.getDetalles() : Collections.emptyList());
        model.put("totalCarrito", calcularTotal(carritoActivo));

        return model;
    }

    private BigDecimal calcularTotal(Carrito carrito) {
        if (carrito == null) return BigDecimal.ZERO;
        return carrito.getDetalles().stream()
                .map(d -> d.getPrecioUnitario().multiply(new BigDecimal(d.getCantidad())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}