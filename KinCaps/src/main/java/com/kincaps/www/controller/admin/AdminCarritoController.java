package com.kincaps.www.controller.admin;

import com.kincaps.www.repository.CarritoRepository;
import com.kincaps.www.repository.ClienteRepository;
import com.kincaps.www.entity.Carrito;
import com.kincaps.www.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/mantenimiento/carrito")
public class AdminCarritoController {

    @Autowired private CarritoRepository carritoRepository;
    @Autowired private ClienteRepository clienteRepository;

    @GetMapping
    public String listarCarritos(Model model) {
        model.addAttribute("listaCarritos", carritoRepository.findAll());
        model.addAttribute("pageTitle", "Gestión de Carritos");
        return "admin/carrito/lista";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("carrito", new Carrito());
        model.addAttribute("listaClientes", clienteRepository.findAll());
        model.addAttribute("pageTitle", "Nuevo Carrito");
        return "admin/carrito/formulario";
    }

    @PostMapping("/guardar")
    public String guardarCarrito(@ModelAttribute Carrito carrito, @RequestParam Integer idCliente) {
        Cliente cliente = clienteRepository.findById(idCliente).orElse(null);
        carrito.setCliente(cliente);
        carrito.setFechaCreacion(LocalDateTime.now());
        carritoRepository.save(carrito);
        return "redirect:/mantenimiento/carrito";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Integer id, Model model) {
        Carrito carrito = carritoRepository.findById(id).orElse(null);
        model.addAttribute("carrito", carrito);
        model.addAttribute("listaClientes", clienteRepository.findAll());
        model.addAttribute("pageTitle", "Editar Carrito");
        return "admin/carrito/formulario";
    }

    @PostMapping("/actualizar")
    public String actualizarCarrito(@ModelAttribute Carrito carrito, @RequestParam Integer idCliente, @RequestParam String fechaCreacion) {
        Cliente cliente = clienteRepository.findById(idCliente).orElse(null);
        carrito.setCliente(cliente);
        carrito.setFechaCreacion(LocalDateTime.parse(fechaCreacion));
        carritoRepository.save(carrito);
        return "redirect:/mantenimiento/carrito";
    }

    @GetMapping("/{id}/activar")
    public String activarCarrito(@PathVariable Integer id) {
        carritoRepository.findById(id).ifPresent(carrito -> {
            carrito.setEstado(Carrito.Estado.ACTIVO);
            carritoRepository.save(carrito);
        });
        return "redirect:/mantenimiento/carrito";
    }

    @GetMapping("/{id}/desactivar")
    public String desactivarCarrito(@PathVariable Integer id) {
        carritoRepository.findById(id).ifPresent(carrito -> {
            carrito.setEstado(Carrito.Estado.INACTIVO);
            carritoRepository.save(carrito);
        });
        return "redirect:/mantenimiento/carrito";
    }
}