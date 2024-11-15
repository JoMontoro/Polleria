/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.integrador.Controladores;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LocationController {

    @GetMapping("/location")
    public String getLocation(Model model) {
        model.addAttribute("address", "Av. AYUDA Viva 123, Ciudad X");
        model.addAttribute("phone", "+51 999 888 777");
        model.addAttribute("hours", "Lunes a Domingo: 9:00 AM - 9:00 PM");
        return "location";
    }
}
