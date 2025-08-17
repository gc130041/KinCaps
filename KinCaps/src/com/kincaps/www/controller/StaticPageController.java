package com.kincaps.www.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StaticPageController {

    @GetMapping("/terminos")
    public String mostrarTerminos() {
        return "terminos";
    }

    @GetMapping("/politica")
    public String mostrarPolitica() {
        return "politica";
    }
}