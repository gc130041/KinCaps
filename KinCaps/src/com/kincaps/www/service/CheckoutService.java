package com.kincaps.www.service;

import com.kincaps.www.repository.CarritoRepository;
import com.kincaps.www.repository.ClienteRepository;
import jakarta.servlet.http.HttpSession;
import com.kincaps.www.entity.Carrito;
import com.kincaps.www.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class CheckoutService {

    @Autowired private CarritoRepository carritoRepository;
    @Autowired private ClienteRepository clienteRepository;

    @Transactional(readOnly = true)
    public Map<String, Object> prepararCheckout(HttpSession session) {
        Cliente clienteEnSesion = (Cliente) session.getAttribute("usuario");
        if (clienteEnSesion == null) throw new IllegalStateException("Cliente no autenticado.");

        Cliente clienteCompleto = clienteRepository.findById(clienteEnSesion.getIdUsuario())
                .orElseThrow(() -> new IllegalStateException("Cliente no encontrado en la base de datos."));

        Carrito carritoActivo = carritoRepository.findByClienteAndEstado(clienteCompleto, Carrito.Estado.ACTIVO).orElse(null);
        if (carritoActivo == null || carritoActivo.getDetalles().isEmpty()) {
            return null;
        }

        BigDecimal totalCarrito = carritoActivo.getDetalles().stream()
                .map(detalle -> detalle.getPrecioUnitario().multiply(new BigDecimal(detalle.getCantidad())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Map<String, Object> checkoutData = new HashMap<>();
        checkoutData.put("cliente", clienteCompleto);
        checkoutData.put("listaDetalleCarrito", carritoActivo.getDetalles());
        checkoutData.put("totalCarrito", totalCarrito);

        return checkoutData;
    }
}