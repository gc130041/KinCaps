package com.kincaps.www.controller.admin;

import com.kincaps.www.repository.ProveedorRepository;
import modelo.Proveedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/mantenimiento/proveedores")
public class AdminProveedorController {

    @Autowired
    private ProveedorRepository proveedorRepository;

    @GetMapping
    public String listarProveedores(Model model) {
        model.addAttribute("listaProveedores", proveedorRepository.findAll());
        model.addAttribute("pageTitle", "Gestión de Proveedores");
        return "admin/proveedores/lista";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("proveedor", new Proveedor());
        model.addAttribute("pageTitle", "Nuevo Proveedor");
        return "admin/proveedores/formulario";
    }

    @PostMapping("/guardar")
    public String guardarProveedor(@ModelAttribute Proveedor proveedor) {
        proveedorRepository.save(proveedor);
        return "redirect:/mantenimiento/proveedores";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Integer id, Model model) {
        Proveedor proveedor = proveedorRepository.findById(id).orElse(null);
        model.addAttribute("proveedor", proveedor);
        model.addAttribute("pageTitle", "Editar Proveedor");
        return "admin/proveedores/formulario";
    }

    @PostMapping("/actualizar")
    public String actualizarProveedor(@ModelAttribute Proveedor proveedor) {
        proveedorRepository.save(proveedor);
        return "redirect:/mantenimiento/proveedores";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarProveedor(@PathVariable Integer id) {
        proveedorRepository.deleteById(id);
        return "redirect:/mantenimiento/proveedores";
    }
}