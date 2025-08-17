package com.kincaps.www.service;

import com.kincaps.www.repository.*;
import jakarta.servlet.http.HttpSession;
import modelo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class CarritoService {

    @Autowired
    private CarritoRepository carritoRepository;
    @Autowired
    private DetalleCarritoRepository detalleCarritoRepository;
    @Autowired
    private GorraRepository gorraRepository;

    @Transactional
    public Map<String, Object> agregarProducto(int idGorra, int cantidad, HttpSession session) {
        Cliente cliente = (Cliente) session.getAttribute("usuario");
        if (cliente == null) throw new IllegalStateException("No has iniciado sesión.");
        if (cantidad <= 0) throw new IllegalArgumentException("La cantidad debe ser un número positivo.");

        Gorras gorra = gorraRepository.findById(idGorra)
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado."));

        Carrito carrito = carritoRepository.findByClienteAndEstado(cliente, Carrito.Estado.ACTIVO)
                .orElseGet(() -> {
                    Carrito nuevoCarrito = new Carrito(cliente);
                    return carritoRepository.save(nuevoCarrito);
                });

        Optional<DetalleCarrito> detalleExistenteOpt = detalleCarritoRepository.findByCarritoAndGorra(carrito, gorra);
        int cantidadEnCarrito = detalleExistenteOpt.map(DetalleCarrito::getCantidad).orElse(0);

        if (gorra.getStock() < cantidadEnCarrito + cantidad) {
            throw new IllegalArgumentException("No hay stock suficiente para la cantidad solicitada.");
        }

        DetalleCarrito detalle;
        if (detalleExistenteOpt.isPresent()) {
            detalle = detalleExistenteOpt.get();
            detalle.setCantidad(detalle.getCantidad() + cantidad);
        } else {
            detalle = new DetalleCarrito(carrito, gorra, cantidad, gorra.getPrecio());
        }
        detalleCarritoRepository.save(detalle);

        int stockRestante = gorra.getStock() - detalle.getCantidad();

        Map<String, Object> productoMap = new HashMap<>();
        productoMap.put("idGorra", gorra.getIdGorra());
        productoMap.put("nombre", gorra.getNombreGorra());
        productoMap.put("precio", gorra.getPrecio().doubleValue());
        productoMap.put("imagen", obtenerUrlImagen(gorra));
        productoMap.put("cantidadAgregada", cantidad);
        productoMap.put("cantidadTotalEnCarrito", detalle.getCantidad());
        productoMap.put("stockRestante", stockRestante);

        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "Producto agregado correctamente.");
        response.put("producto", productoMap);

        return response;
    }

    @Transactional
    public Map<String, Object> eliminarProducto(int idGorra, int cantidadAEliminar, HttpSession session) {
        Cliente cliente = (Cliente) session.getAttribute("usuario");
        if (cliente == null) throw new IllegalStateException("No has iniciado sesión.");

        Carrito carrito = carritoRepository.findByClienteAndEstado(cliente, Carrito.Estado.ACTIVO)
                .orElseThrow(() -> new IllegalArgumentException("No se encontró un carrito activo."));

        Gorras gorra = gorraRepository.findById(idGorra)
                .orElseThrow(() -> new IllegalArgumentException("El producto a eliminar no existe."));

        DetalleCarrito detalle = detalleCarritoRepository.findByCarritoAndGorra(carrito, gorra)
                .orElseThrow(() -> new IllegalArgumentException("El item a eliminar no está en el carrito."));

        int cantidadActual = detalle.getCantidad();
        int nuevaCantidad = cantidadActual - cantidadAEliminar;

        Map<String, Object> response = new HashMap<>();

        if (nuevaCantidad <= 0) {
            detalleCarritoRepository.delete(detalle);
            response.put("action", "deleted");
        } else {
            detalle.setCantidad(nuevaCantidad);
            detalleCarritoRepository.save(detalle);
            response.put("action", "updated");
        }

        response.put("status", "success");
        response.put("message", "Item(s) eliminado(s) correctamente.");
        response.put("idGorra", idGorra);
        response.put("nuevaCantidad", Math.max(0, nuevaCantidad));
        response.put("precioUnitario", detalle.getPrecioUnitario().doubleValue());
        response.put("cantidadEliminada", cantidadAEliminar);

        return response;
    }

    private String obtenerUrlImagen(Gorras g) {
        String folderPath = "otros";
        if (g.getTipo() != null) {
            switch (g.getTipo()) {
                case URBANA:
                    folderPath = "urbano";
                    break;
                case DEPORTIVA:
                    folderPath = "deportivo";
                    break;
                case FORMULA_1:
                    folderPath = "f1";
                    break;
            }
        }
        return String.format("/img/Gorras/%s/%s", folderPath, g.getImagen() != null ? g.getImagen() : "default.png");
    }
}