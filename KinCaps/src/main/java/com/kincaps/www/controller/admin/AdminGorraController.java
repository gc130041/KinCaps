package com.kincaps.www.controller.admin;

import com.kincaps.www.repository.GorraRepository;
import com.kincaps.www.repository.ProveedorRepository;
import com.kincaps.www.entity.Gorras;
import com.kincaps.www.entity.Proveedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/mantenimiento/gorras")
public class AdminGorraController {

    @Autowired private GorraRepository gorraRepository;
    @Autowired private ProveedorRepository proveedorRepository;

    private void addCommonAttributes(Model model) {
        model.addAttribute("listaProveedores", proveedorRepository.findAll());
    }

    @GetMapping
    public String listarGorras(Model model) {
        model.addAttribute("listaGorras", gorraRepository.findAll());
        model.addAttribute("pageTitle", "Gestión de Productos");
        return "admin/gorras/lista";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("gorra", new Gorras());
        addCommonAttributes(model);
        model.addAttribute("pageTitle", "Nuevo Producto");
        return "admin/gorras/formulario";
    }

    @PostMapping("/guardar")
    public String guardarGorra(@ModelAttribute Gorras gorra, @RequestParam Integer idProveedor) {
        Proveedor proveedor = proveedorRepository.findById(idProveedor).orElse(null);
        gorra.setProveedor(proveedor);
        gorraRepository.save(gorra);
        return "redirect:/mantenimiento/gorras";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Integer id, Model model) {
        Gorras gorra = gorraRepository.findById(id).orElse(null);
        model.addAttribute("gorra", gorra);
        addCommonAttributes(model);
        model.addAttribute("pageTitle", "Editar Producto");
        return "admin/gorras/formulario";
    }

    @PostMapping("/actualizar")
    public String actualizarGorra(@ModelAttribute Gorras gorra, @RequestParam Integer idProveedor) {
        Proveedor proveedor = proveedorRepository.findById(idProveedor).orElse(null);
        gorra.setProveedor(proveedor);
        gorraRepository.save(gorra);
        return "redirect:/mantenimiento/gorras";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarGorra(@PathVariable Integer id) {
        gorraRepository.deleteById(id);
        return "redirect:/mantenimiento/gorras";
    }
}