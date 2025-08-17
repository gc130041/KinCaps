package com.kincaps.www.controller.admin;

import com.kincaps.www.repository.ClienteRepository;
import com.kincaps.www.repository.EmpleadoRepository;
import com.kincaps.www.repository.FacturaRepository;
import modelo.Cliente;
import modelo.Empleado;
import modelo.Factura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/mantenimiento/facturas")
public class AdminFacturaController {

    @Autowired private FacturaRepository facturaRepository;
    @Autowired private ClienteRepository clienteRepository;
    @Autowired private EmpleadoRepository empleadoRepository;

    private void addCommonAttributes(Model model) {
        model.addAttribute("listaClientes", clienteRepository.findAll());
        model.addAttribute("listaEmpleados", empleadoRepository.findAll());
    }

    @GetMapping
    public String listarFacturas(Model model) {
        model.addAttribute("listaFacturas", facturaRepository.findAll());
        model.addAttribute("pageTitle", "Gestión de Facturas");
        return "admin/facturas/lista";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("factura", new Factura());
        addCommonAttributes(model);
        model.addAttribute("pageTitle", "Nueva Factura");
        return "admin/facturas/formulario";
    }

    @PostMapping("/guardar")
    public String guardarFactura(@ModelAttribute Factura factura, @RequestParam Integer idCliente, @RequestParam Integer idEmpleado) {
        Cliente cliente = clienteRepository.findById(idCliente).orElse(null);
        Empleado empleado = empleadoRepository.findById(idEmpleado).orElse(null);
        factura.setCliente(cliente);
        factura.setEmpleado(empleado);
        factura.setFechaEmision(LocalDateTime.now());
        facturaRepository.save(factura);
        return "redirect:/mantenimiento/facturas";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Integer id, Model model) {
        Factura factura = facturaRepository.findById(id).orElse(null);
        model.addAttribute("factura", factura);
        addCommonAttributes(model);
        model.addAttribute("pageTitle", "Editar Factura");
        return "admin/facturas/formulario";
    }

    @PostMapping("/actualizar")
    public String actualizarFactura(@ModelAttribute Factura factura, @RequestParam Integer idCliente, @RequestParam Integer idEmpleado, @RequestParam String fechaEmision) {
        Cliente cliente = clienteRepository.findById(idCliente).orElse(null);
        Empleado empleado = empleadoRepository.findById(idEmpleado).orElse(null);
        factura.setCliente(cliente);
        factura.setEmpleado(empleado);
        factura.setFechaEmision(LocalDateTime.parse(fechaEmision));
        facturaRepository.save(factura);
        return "redirect:/mantenimiento/facturas";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarFactura(@PathVariable Integer id) {
        facturaRepository.deleteById(id);
        return "redirect:/mantenimiento/facturas";
    }
}