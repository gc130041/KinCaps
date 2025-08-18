package com.kincaps.www.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StaticPageController {

    @GetMapping("/terminos")
    public String mostrarTerminos(Model model) {
        model.addAttribute("pageTitle", "Términos y Condiciones");
        return "terminos";
    }

    @GetMapping("/politica")
    public String mostrarPolitica(Model model) {
        model.addAttribute("pageTitle", "Política de Privacidad");
        return "politica";
    }
}