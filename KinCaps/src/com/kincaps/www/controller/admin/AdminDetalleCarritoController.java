package com.kincaps.www.controller.admin;

import com.kincaps.www.repository.CarritoRepository;
import com.kincaps.www.repository.DetalleCarritoRepository;
import com.kincaps.www.repository.GorraRepository;
import modelo.Carrito;
import modelo.DetalleCarrito;
import modelo.Gorras;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/mantenimiento/detallecarrito")
public class AdminDetalleCarritoController {

    @Autowired private DetalleCarritoRepository detalleCarritoRepository;
    @Autowired private CarritoRepository carritoRepository;
    @Autowired private GorraRepository gorraRepository;

    private void addCommonAttributes(Model model) {
        model.addAttribute("listaCarritos", carritoRepository.findAll());
        model.addAttribute("listaGorras", gorraRepository.findAll());
    }

    @GetMapping
    public String listarDetalles(Model model) {
        model.addAttribute("listaDetalleCarrito", detalleCarritoRepository.findAll());
        model.addAttribute("pageTitle", "Gestión de Detalles de Carrito");
        return "admin/detallecarrito/lista";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("detalle", new DetalleCarrito());
        addCommonAttributes(model);
        model.addAttribute("pageTitle", "Nuevo Detalle de Carrito");
        return "admin/detallecarrito/formulario";
    }

    @PostMapping("/guardar")
    public String guardarDetalle(@ModelAttribute DetalleCarrito detalle,
                                 @RequestParam Integer idCarrito,
                                 @RequestParam Integer idGorra) {
        Carrito carrito = carritoRepository.findById(idCarrito).orElse(null);
        Gorras gorra = gorraRepository.findById(idGorra).orElse(null);
        detalle.setCarrito(carrito);
        detalle.setGorra(gorra);
        detalleCarritoRepository.save(detalle);
        return "redirect:/mantenimiento/detallecarrito";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Integer id, Model model) {
        DetalleCarrito detalle = detalleCarritoRepository.findById(id).orElse(null);
        model.addAttribute("detalle", detalle);
        addCommonAttributes(model);
        model.addAttribute("pageTitle", "Editar Detalle de Carrito");
        return "admin/detallecarrito/formulario";
    }

    @PostMapping("/actualizar")
    public String actualizarDetalle(@ModelAttribute DetalleCarrito detalle,
                                    @RequestParam Integer idCarrito,
                                    @RequestParam Integer idGorra) {
        Carrito carrito = carritoRepository.findById(idCarrito).orElse(null);
        Gorras gorra = gorraRepository.findById(idGorra).orElse(null);
        detalle.setCarrito(carrito);
        detalle.setGorra(gorra);
        detalleCarritoRepository.save(detalle);
        return "redirect:/mantenimiento/detallecarrito";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarDetalle(@PathVariable Integer id) {
        detalleCarritoRepository.deleteById(id);
        return "redirect:/mantenimiento/detallecarrito";
    }
}