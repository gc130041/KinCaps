package com.kincaps.www.controller.admin;

import com.kincaps.www.repository.ClienteRepository;
import modelo.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/mantenimiento/clientes")
public class AdminClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public String listarClientes(Model model) {
        model.addAttribute("listaClientes", clienteRepository.findAll());
        model.addAttribute("pageTitle", "Gestión de Clientes");
        return "admin/clientes/lista";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("cliente", new Cliente());
        model.addAttribute("pageTitle", "Nuevo Cliente");
        return "admin/clientes/formulario";
    }

    @PostMapping("/guardar")
    public String guardarCliente(@ModelAttribute Cliente cliente) {
        clienteRepository.save(cliente);
        return "redirect:/mantenimiento/clientes";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Integer id, Model model) {
        Cliente cliente = clienteRepository.findById(id).orElse(null);
        model.addAttribute("cliente", cliente);
        model.addAttribute("pageTitle", "Editar Cliente");
        return "admin/clientes/formulario";
    }

    @PostMapping("/actualizar")
    public String actualizarCliente(@ModelAttribute Cliente cliente) {
        clienteRepository.save(cliente);
        return "redirect:/mantenimiento/clientes";
    }

    @GetMapping("/{id}/activar")
    public String activarCliente(@PathVariable Integer id) {
        clienteRepository.findById(id).ifPresent(cliente -> {
            cliente.setEstado(Cliente.Estado.ACTIVO);
            clienteRepository.save(cliente);
        });
        return "redirect:/mantenimiento/clientes";
    }

    @GetMapping("/{id}/desactivar")
    public String desactivarCliente(@PathVariable Integer id) {
        clienteRepository.findById(id).ifPresent(cliente -> {
            cliente.setEstado(Cliente.Estado.INACTIVO);
            clienteRepository.save(cliente);
        });
        return "redirect:/mantenimiento/clientes";
    }
}