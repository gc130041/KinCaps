package com.kincaps.www.service;

import com.kincaps.www.repository.*;
import jakarta.servlet.http.HttpSession;
import modelo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class PagoService {

    @Autowired private CarritoRepository carritoRepository;
    @Autowired private EmpleadoRepository empleadoRepository;
    @Autowired private FacturaRepository facturaRepository;
    @Autowired private DetalleFacturaRepository detalleFacturaRepository;
    @Autowired private GorraRepository gorraRepository;

    @Transactional
    public Factura procesarCompra(HttpSession session, String metodoPagoStr) throws Exception {
        Cliente cliente = (Cliente) session.getAttribute("usuario");
        if (cliente == null) throw new IllegalStateException("Cliente no autenticado.");

        Empleado empleadoAsignado = empleadoRepository.findById(1)
                .orElseThrow(() -> new IllegalStateException("Empleado con ID 1 no encontrado."));

        Carrito carritoActivo = carritoRepository.findByClienteAndEstado(cliente, Carrito.Estado.ACTIVO)
                .orElseThrow(() -> new IllegalStateException("No se encontró un carrito activo."));

        if (carritoActivo.getDetalles().isEmpty()) {
            throw new IllegalStateException("El carrito está vacío.");
        }

        BigDecimal totalReal = carritoActivo.getDetalles().stream()
                .map(item -> item.getPrecioUnitario().multiply(new BigDecimal(item.getCantidad())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Factura factura = new Factura();
        factura.setCliente(cliente);
        factura.setEmpleado(empleadoAsignado);
        factura.setTotal(totalReal);
        factura.setMetodoPago(Factura.MetodoPago.valueOf(metodoPagoStr));
        facturaRepository.save(factura);

        for (DetalleCarrito item : carritoActivo.getDetalles()) {
            DetalleFactura detalleFactura = new DetalleFactura(factura, item.getGorra(), item.getCantidad(), item.getPrecioUnitario());
            detalleFacturaRepository.save(detalleFactura);

            Gorras gorra = item.getGorra();
            int nuevoStock = gorra.getStock() - item.getCantidad();
            if (nuevoStock < 0) {
                throw new IllegalStateException("Stock insuficiente para la gorra: " + gorra.getNombreGorra());
            }
            gorra.setStock(nuevoStock);
            gorraRepository.save(gorra);
        }

        carritoActivo.setEstado(Carrito.Estado.PAGADO);
        carritoRepository.save(carritoActivo);

        return factura;
    }
}