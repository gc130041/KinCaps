package com.kincaps.www.controller.admin;

import com.kincaps.www.repository.EmpleadoRepository;
import modelo.Empleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/mantenimiento/empleados")
public class AdminEmpleadoController {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @GetMapping
    public String listarEmpleados(Model model) {
        model.addAttribute("listaEmpleados", empleadoRepository.findAll());
        model.addAttribute("pageTitle", "Gestión de Empleados");
        return "admin/empleados/lista";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("empleado", new Empleado());
        model.addAttribute("pageTitle", "Nuevo Empleado");
        return "admin/empleados/formulario";
    }

    @PostMapping("/guardar")
    public String guardarEmpleado(@ModelAttribute Empleado empleado) {
        empleadoRepository.save(empleado);
        return "redirect:/mantenimiento/empleados";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Integer id, Model model) {
        Empleado empleado = empleadoRepository.findById(id).orElse(null);
        model.addAttribute("empleado", empleado);
        model.addAttribute("pageTitle", "Editar Empleado");
        return "admin/empleados/formulario";
    }

    @PostMapping("/actualizar")
    public String actualizarEmpleado(@ModelAttribute Empleado empleado) {
        empleadoRepository.save(empleado);
        return "redirect:/mantenimiento/empleados";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarEmpleado(@PathVariable Integer id) {
        empleadoRepository.deleteById(id);
        return "redirect:/mantenimiento/empleados";
    }
}