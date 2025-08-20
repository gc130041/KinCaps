package com.kincaps.www.controller;

import com.kincaps.www.service.CheckoutService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class CheckoutController {

    @Autowired
    private CheckoutService checkoutService;

    @GetMapping("/checkout")
    public String showCheckoutPage(HttpSession session, Model model) {
        try {
            Map<String, Object> checkoutData = checkoutService.prepararCheckout(session);
            if (checkoutData == null) {
                return "redirect:/gorras";
            }
            model.addAllAttributes(checkoutData);
            return "checkout";
        } catch (IllegalStateException e) {
            return "redirect:/login";
        }
    }
}