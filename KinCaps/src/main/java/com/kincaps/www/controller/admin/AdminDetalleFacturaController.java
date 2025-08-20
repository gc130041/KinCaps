package com.kincaps.www.controller.admin;

import com.kincaps.www.repository.DetalleFacturaRepository;
import com.kincaps.www.repository.FacturaRepository;
import com.kincaps.www.repository.GorraRepository;
import com.kincaps.www.entity.DetalleFactura;
import com.kincaps.www.entity.Factura;
import com.kincaps.www.entity.Gorras;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/mantenimiento/detallefactura")
public class AdminDetalleFacturaController {

    @Autowired private DetalleFacturaRepository detalleFacturaRepository;
    @Autowired private FacturaRepository facturaRepository;
    @Autowired private GorraRepository gorraRepository;

    private void addCommonAttributes(Model model) {
        model.addAttribute("listaFacturas", facturaRepository.findAll());
        model.addAttribute("listaGorras", gorraRepository.findAll());
    }

    @GetMapping
    public String listarDetalles(Model model) {
        model.addAttribute("listaDetalleFactura", detalleFacturaRepository.findAll());
        model.addAttribute("pageTitle", "Gestión de Detalles de Factura");
        return "admin/detallefactura/lista";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("detalle", new DetalleFactura());
        addCommonAttributes(model);
        model.addAttribute("pageTitle", "Nuevo Detalle de Factura");
        return "admin/detallefactura/formulario";
    }

    @PostMapping("/guardar")
    public String guardarDetalle(@ModelAttribute DetalleFactura detalle,
                                 @RequestParam Integer idFactura,
                                 @RequestParam Integer idGorra) {
        Factura factura = facturaRepository.findById(idFactura).orElse(null);
        Gorras gorra = gorraRepository.findById(idGorra).orElse(null);
        detalle.setFactura(factura);
        detalle.setGorra(gorra);
        detalleFacturaRepository.save(detalle);
        return "redirect:/mantenimiento/detallefactura";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Integer id, Model model) {
        DetalleFactura detalle = detalleFacturaRepository.findById(id).orElse(null);
        model.addAttribute("detalle", detalle);
        addCommonAttributes(model);
        model.addAttribute("pageTitle", "Editar Detalle de Factura");
        return "admin/detallefactura/formulario";
    }

    @PostMapping("/actualizar")
    public String actualizarDetalle(@ModelAttribute DetalleFactura detalle,
                                    @RequestParam Integer idFactura,
                                    @RequestParam Integer idGorra) {
        Factura factura = facturaRepository.findById(idFactura).orElse(null);
        Gorras gorra = gorraRepository.findById(idGorra).orElse(null);
        detalle.setFactura(factura);
        detalle.setGorra(gorra);
        detalleFacturaRepository.save(detalle);
        return "redirect:/mantenimiento/detallefactura";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarDetalle(@PathVariable Integer id) {
        detalleFacturaRepository.deleteById(id);
        return "redirect:/mantenimiento/detallefactura";
    }
}